/*
 * Copyright 2010-2019 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.resolve.checkers

import com.intellij.psi.PsiElement
import org.jetbrains.kotlin.descriptors.*
import org.jetbrains.kotlin.diagnostics.Errors
import org.jetbrains.kotlin.psi.KtDeclaration
import org.jetbrains.kotlin.psi.KtTypeParameterListOwner
import org.jetbrains.kotlin.resolve.BindingContext
import org.jetbrains.kotlin.resolve.BindingTrace
import org.jetbrains.kotlin.resolve.calls.checkers.CallChecker
import org.jetbrains.kotlin.resolve.calls.checkers.CallCheckerContext
import org.jetbrains.kotlin.resolve.calls.model.ResolvedCall
import org.jetbrains.kotlin.resolve.descriptorUtil.classId
import org.jetbrains.kotlin.resolve.descriptorUtil.fqNameSafe
import org.jetbrains.kotlin.types.KotlinType
import org.jetbrains.kotlin.types.typeUtil.supertypes

object MissingDependencySupertypeChecker {
    val ForDeclarations = object : DeclarationChecker {
        override fun check(declaration: KtDeclaration, descriptor: DeclarationDescriptor, context: DeclarationCheckerContext) {
            val trace = context.trace
            val module = context.moduleDescriptor

            if (descriptor is ClassDescriptor) {
                checkSupertypes(descriptor.defaultType, declaration, trace, module)
            }

            if (declaration is KtTypeParameterListOwner) {
                for (ktTypeParameter in declaration.typeParameters) {
                    trace.bindingContext.get(BindingContext.TYPE_PARAMETER, ktTypeParameter)
                        ?.let { typeParameterDescriptor ->
                            for (upperBound in typeParameterDescriptor.upperBounds) {
                                checkSupertypes(upperBound, ktTypeParameter, trace, module)
                            }
                        }
                }
            }
        }
    }

    val ForCalls = object : CallChecker {
        override fun check(resolvedCall: ResolvedCall<*>, reportOn: PsiElement, context: CallCheckerContext) {
            val descriptor = resolvedCall.resultingDescriptor
            if (descriptor is ConstructorDescriptor)
                return // constructor call doesn't trigger supertypes resolution

            checkHierarchy(descriptor.containingDeclaration, reportOn, context)
            checkHierarchy(descriptor.dispatchReceiverParameter?.declaration, reportOn, context)
            checkHierarchy(descriptor.extensionReceiverParameter?.declaration, reportOn, context)
        }

        private val ReceiverParameterDescriptor.declaration
            get() = value.type.constructor.declarationDescriptor

        private fun checkHierarchy(declaration: DeclarationDescriptor?, reportOn: PsiElement, context: CallCheckerContext) {
            if (declaration !is ClassifierDescriptor) return

            val classifierType = when (declaration) {
                is TypeAliasDescriptor -> declaration.expandedType
                else -> declaration.defaultType
            }

            checkSupertypes(classifierType, reportOn, context.trace, context.moduleDescriptor)
        }
    }

    fun checkSupertypes(
        classifierType: KotlinType,
        reportOn: PsiElement,
        trace: BindingTrace,
        moduleDescriptor: ModuleDescriptor
    ) {
        val classifierDescriptor = classifierType.constructor.declarationDescriptor
            ?: return

        for (supertype in classifierType.supertypes()) {
            val supertypeDeclaration = supertype.constructor.declarationDescriptor

            // TODO: expects are not checked, because findClassAcrossModuleDependencies does not work with actualization via type alias
            if (supertypeDeclaration !is ClassDescriptor || supertypeDeclaration.isExpect) continue
            if (supertypeDeclaration.visibility == Visibilities.LOCAL) continue

            val superTypeClassId = supertypeDeclaration.classId ?: continue
            val dependency = moduleDescriptor.findClassAcrossModuleDependencies(superTypeClassId)

            if (dependency == null || dependency is NotFoundClasses.MockClassDescriptor) {
                trace.report(
                    Errors.MISSING_DEPENDENCY_SUPERCLASS.on(
                        reportOn,
                        supertypeDeclaration.fqNameSafe,
                        classifierDescriptor.fqNameSafe
                    )
                )
            }
        }
    }
}
