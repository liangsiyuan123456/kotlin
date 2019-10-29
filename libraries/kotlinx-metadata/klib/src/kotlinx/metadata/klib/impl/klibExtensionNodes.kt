/*
 * Copyright 2010-2019 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package kotlinx.metadata.klib.impl

import kotlinx.metadata.*
import kotlinx.metadata.impl.extensions.*
import kotlinx.metadata.klib.*

val KmFunction.klibExtensions: KlibFunctionExtension
    get() = visitExtensions(KlibFunctionExtensionVisitor.TYPE) as KlibFunctionExtension

val KmClass.klibExtensions: KlibClassExtension
    get() = visitExtensions(KlibClassExtensionVisitor.TYPE) as KlibClassExtension

val KmType.klibExtensions: KlibTypeExtension
    get() = visitExtensions(KlibTypeExtensionVisitor.TYPE) as KlibTypeExtension

val KmProperty.klibExtensions: KlibPropertyExtension
    get() = visitExtensions(KlibPropertyExtensionVisitor.TYPE) as KlibPropertyExtension

val KmConstructor.klibExtensions: KlibConstructorExtension
    get() = visitExtensions(KlibConstructorExtensionVisitor.TYPE) as KlibConstructorExtension

val KmTypeParameter.klibExtensions: KlibTypeParameterExtension
    get() = visitExtensions(KlibTypeParameterExtensionVisitor.TYPE) as KlibTypeParameterExtension

val KmPackage.klibExtensions: KlibPackageExtension
    get() = visitExtensions(KlibPackageExtensionVisitor.TYPE) as KlibPackageExtension

class KlibFunctionExtension : KlibFunctionExtensionVisitor(), KmFunctionExtension {

    val annotations: MutableList<KmAnnotation> = mutableListOf()
    var uniqId: DescriptorUniqId? = null
    var file: Int? = null

    override fun visitUniqId(uniqId: DescriptorUniqId) {
        this.uniqId = uniqId
    }

    override fun visitAnnotation(annotation: KmAnnotation) {
        annotations += annotation
    }

    override fun visitFile(file: Int) {
        this.file = file
    }

    override fun accept(visitor: KmFunctionExtensionVisitor) {
        require(visitor is KlibFunctionExtensionVisitor)
        annotations.forEach(visitor::visitAnnotation)
        uniqId?.let { visitor.visitUniqId(it) }
        file?.let { visitor.visitFile(it) }
    }
}

class KlibClassExtension : KlibClassExtensionVisitor(), KmClassExtension {

    val annotations: MutableList<KmAnnotation> = mutableListOf()
    var uniqId: DescriptorUniqId? = null
    var file: Int? = null

    override fun visitAnnotation(annotation: KmAnnotation) {
        annotations += annotation
    }

    override fun visitUniqId(uniqId: DescriptorUniqId) {
        this.uniqId = uniqId
    }

    override fun visitFile(file: Int) {
        this.file = file
    }

    override fun accept(visitor: KmClassExtensionVisitor) {
        require(visitor is KlibClassExtensionVisitor)
        annotations.forEach(visitor::visitAnnotation)
        uniqId?.let { visitor.visitUniqId(it) }
        file?.let { visitor.visitFile(it) }
    }
}

class KlibTypeExtension : KlibTypeExtensionVisitor(), KmTypeExtension {

    val annotations: MutableList<KmAnnotation> = mutableListOf()

    override fun visitAnnotation(annotation: KmAnnotation) {
        annotations += annotation
    }

    override fun accept(visitor: KmTypeExtensionVisitor) {
        require(visitor is KlibTypeExtensionVisitor)
        annotations.forEach(visitor::visitAnnotation)
    }
}

class KlibPropertyExtension : KlibPropertyExtensionVisitor(), KmPropertyExtension {

    val annotations: MutableList<KmAnnotation> = mutableListOf()
    val getterAnnotations: MutableList<KmAnnotation> = mutableListOf()
    val setterAnnotations: MutableList<KmAnnotation> = mutableListOf()
    var uniqId: DescriptorUniqId? = null
    var file: Int? = null
    var compileTimeValue: KmAnnotationArgument<*>? = null

    override fun visitAnnotation(annotation: KmAnnotation) {
        annotations += annotation
    }

    override fun visitGetterAnnotation(annotation: KmAnnotation) {
        getterAnnotations += annotation
    }

    override fun visitSetterAnnotation(annotation: KmAnnotation) {
        setterAnnotations += annotation
    }

    override fun visitFile(file: Int) {
        this.file = file
    }

    override fun visitUniqId(uniqId: DescriptorUniqId) {
        this.uniqId = uniqId
    }

    override fun visitCompileTimeValue(value: KmAnnotationArgument<*>) {
        this.compileTimeValue = value
    }

    override fun accept(visitor: KmPropertyExtensionVisitor) {
        require(visitor is KlibPropertyExtensionVisitor)
        annotations.forEach(visitor::visitAnnotation)
        getterAnnotations.forEach(visitor::visitGetterAnnotation)
        setterAnnotations.forEach(visitor::visitSetterAnnotation)
        file?.let { visitor.visitFile(it) }
        uniqId?.let { visitor.visitUniqId(it) }
        compileTimeValue?.let { visitor.visitCompileTimeValue(it) }
    }
}

class KlibConstructorExtension : KlibConstructorExtensionVisitor(), KmConstructorExtension {

    val annotations: MutableList<KmAnnotation> = mutableListOf()
    var uniqId: DescriptorUniqId? = null

    override fun visitAnnotation(annotation: KmAnnotation) {
        annotations += annotation
    }

    override fun visitUniqId(uniqId: DescriptorUniqId) {
        this.uniqId = uniqId
    }

    override fun accept(visitor: KmConstructorExtensionVisitor) {
        require(visitor is KlibConstructorExtensionVisitor)
        annotations.forEach(visitor::visitAnnotation)
        uniqId?.let { visitor.visitUniqId(it) }
    }
}

class KlibTypeParameterExtension : KlibTypeParameterExtensionVisitor(), KmTypeParameterExtension {

    val annotations: MutableList<KmAnnotation> = mutableListOf()
    var uniqId: DescriptorUniqId? = null

    override fun visitAnnotation(annotation: KmAnnotation) {
        annotations += annotation
    }

    override fun visitUniqId(uniqId: DescriptorUniqId) {
        this.uniqId = uniqId
    }

    override fun accept(visitor: KmTypeParameterExtensionVisitor) {
        require(visitor is KlibTypeParameterExtensionVisitor)
        annotations.forEach(visitor::visitAnnotation)
        uniqId?.let { visitor.visitUniqId(it) }
    }
}

class KlibPackageExtension : KlibPackageExtensionVisitor(), KmPackageExtension {

    val classes: MutableList<KmClass> = mutableListOf()

    override fun visitClass(klass: KmClass) {
        classes += klass
    }

    override fun accept(visitor: KmPackageExtensionVisitor) {
        require(visitor is KlibPackageExtensionVisitor)
        classes.forEach(visitor::visitClass)
    }
}