/*
 * Copyright 2010-2019 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package kotlinx.metadata.klib.impl

import kotlinx.metadata.*
import kotlinx.metadata.impl.*
import kotlinx.metadata.impl.extensions.*
import kotlinx.metadata.klib.*
import org.jetbrains.kotlin.library.metadata.KlibMetadataProtoBuf
import org.jetbrains.kotlin.metadata.ProtoBuf
import org.jetbrains.kotlin.metadata.deserialization.getExtensionOrNull

class KlibMetadataExtensions : MetadataExtensions {
    override fun readClassExtensions(v: KmClassVisitor, proto: ProtoBuf.Class, c: ReadContext) {
        val extension = v.visitExtensions(KlibClassExtensionVisitor.TYPE) as? KlibClassExtensionVisitor ?: return

        proto.getExtension(KlibMetadataProtoBuf.classAnnotation).forEach { annotation ->
            extension.visitAnnotation(annotation.readAnnotation(c.strings))
        }
        proto.getExtensionOrNull(KlibMetadataProtoBuf.classUniqId)?.let { descriptorUniqId ->
            extension.visitUniqId(descriptorUniqId.readDescriptorUniqId())
        }
        proto.getExtensionOrNull(KlibMetadataProtoBuf.classFile)?.let { file ->
            extension.visitFile(file)
        }
    }

    override fun readPackageExtensions(v: KmPackageVisitor, proto: ProtoBuf.Package, c: ReadContext) {
        val extension = v.visitExtensions(KlibPackageExtensionVisitor.TYPE) as? KlibPackageExtensionVisitor ?: return
    }

    override fun readFunctionExtensions(v: KmFunctionVisitor, proto: ProtoBuf.Function, c: ReadContext) {
        val extension = v.visitExtensions(KlibFunctionExtensionVisitor.TYPE) as? KlibFunctionExtensionVisitor ?: return

        proto.getExtension(KlibMetadataProtoBuf.functionAnnotation).forEach { annotation ->
            extension.visitAnnotation(annotation.readAnnotation(c.strings))
        }
        proto.getExtensionOrNull(KlibMetadataProtoBuf.functionUniqId)?.let { descriptorUniqId ->
            extension.visitUniqId(descriptorUniqId.readDescriptorUniqId())
        }
        proto.getExtensionOrNull(KlibMetadataProtoBuf.functionFile)?.let { file ->
            extension.visitFile(file)
        }
    }

    override fun readPropertyExtensions(v: KmPropertyVisitor, proto: ProtoBuf.Property, c: ReadContext) {
        val extension = v.visitExtensions(KlibPropertyExtensionVisitor.TYPE) as? KlibPropertyExtensionVisitor ?: return

        proto.getExtension(KlibMetadataProtoBuf.propertyAnnotation).forEach { annotation ->
            extension.visitAnnotation(annotation.readAnnotation(c.strings))
        }
        proto.getExtension(KlibMetadataProtoBuf.propertyGetterAnnotation).forEach { annotation ->
            extension.visitGetterAnnotation(annotation.readAnnotation(c.strings))
        }
        proto.getExtension(KlibMetadataProtoBuf.propertySetterAnnotation).forEach { annotation ->
            extension.visitSetterAnnotation(annotation.readAnnotation(c.strings))
        }
        proto.getExtensionOrNull(KlibMetadataProtoBuf.propertyUniqId)?.let { descriptorUniqId ->
            extension.visitUniqId(descriptorUniqId.readDescriptorUniqId())
        }
        proto.getExtensionOrNull(KlibMetadataProtoBuf.propertyFile)?.let { file ->
            extension.visitFile(file)
        }
        proto.getExtensionOrNull(KlibMetadataProtoBuf.compileTimeValue)?.let { value ->
            value.readAnnotationArgument(c.strings)?.let { extension.visitCompileTimeValue(it) }
        }
    }

    override fun readConstructorExtensions(v: KmConstructorVisitor, proto: ProtoBuf.Constructor, c: ReadContext) {
        val extension = v.visitExtensions(KlibConstructorExtensionVisitor.TYPE) as? KlibConstructorExtensionVisitor ?: return

        proto.getExtension(KlibMetadataProtoBuf.constructorAnnotation).forEach { annotation ->
            extension.visitAnnotation(annotation.readAnnotation(c.strings))
        }
        proto.getExtensionOrNull(KlibMetadataProtoBuf.constructorUniqId)?.let { descriptorUniqId ->
            extension.visitUniqId(descriptorUniqId.readDescriptorUniqId())
        }
    }

    override fun readTypeParameterExtensions(v: KmTypeParameterVisitor, proto: ProtoBuf.TypeParameter, c: ReadContext) {
        val extension = v.visitExtensions(KlibTypeParameterExtensionVisitor.TYPE) as? KlibTypeParameterExtensionVisitor ?: return

        proto.getExtension(KlibMetadataProtoBuf.typeParameterAnnotation).forEach { annotation ->
            extension.visitAnnotation(annotation.readAnnotation(c.strings))
        }
        proto.getExtensionOrNull(KlibMetadataProtoBuf.typeParamUniqId)?.let { descriptorUniqId ->
            extension.visitUniqId(descriptorUniqId.readDescriptorUniqId())
        }
    }

    override fun readTypeExtensions(v: KmTypeVisitor, proto: ProtoBuf.Type, c: ReadContext) {
        val extension = v.visitExtensions(KlibTypeExtensionVisitor.TYPE) as? KlibTypeExtensionVisitor ?: return

        proto.getExtension(KlibMetadataProtoBuf.typeAnnotation).forEach { annotation ->
            extension.visitAnnotation(annotation.readAnnotation(c.strings))
        }
    }

    override fun writeClassExtensions(type: KmExtensionType, proto: ProtoBuf.Class.Builder, c: WriteContext): KmClassExtensionVisitor? {
        if (type != KlibClassExtensionVisitor.TYPE) return null
        return object : KlibClassExtensionVisitor() {
            override fun visitAnnotation(annotation: KmAnnotation) {
                proto.setExtension(
                    KlibMetadataProtoBuf.classAnnotation,
                    listOf(annotation.writeAnnotation(c.strings).build())
                )
            }

            override fun visitUniqId(uniqId: DescriptorUniqId) {
                proto.setExtension(
                    KlibMetadataProtoBuf.classUniqId,
                    uniqId.write().build()
                )
            }

            override fun visitFile(file: Int) {
                proto.setExtension(
                    KlibMetadataProtoBuf.classFile,
                    file
                )
            }
        }
    }

    override fun writePackageExtensions(
        type: KmExtensionType,
        proto: ProtoBuf.Package.Builder,
        c: WriteContext
    ): KmPackageExtensionVisitor? {
        if (type != KlibPackageExtensionVisitor.TYPE) return null
        return object : KlibPackageExtensionVisitor() {
            override fun visitClass(klass: KmClass) {

            }
        }
    }

    override fun writeFunctionExtensions(
        type: KmExtensionType,
        proto: ProtoBuf.Function.Builder,
        c: WriteContext
    ): KmFunctionExtensionVisitor? {
        if (type != KlibFunctionExtensionVisitor.TYPE) return null
        return object : KlibFunctionExtensionVisitor() {
            override fun visitAnnotation(annotation: KmAnnotation) {
                proto.setExtension(
                    KlibMetadataProtoBuf.functionAnnotation,
                    mutableListOf(annotation.writeAnnotation(c.strings).build())
                )
            }

            override fun visitUniqId(uniqId: DescriptorUniqId) {
                proto.setExtension(
                    KlibMetadataProtoBuf.functionUniqId,
                    uniqId.write().build()
                )
            }

            override fun visitFile(file: Int) {
                proto.setExtension(
                    KlibMetadataProtoBuf.functionFile,
                    file
                )
            }
        }
    }

    override fun writePropertyExtensions(
        type: KmExtensionType,
        proto: ProtoBuf.Property.Builder,
        c: WriteContext
    ): KmPropertyExtensionVisitor? {
        if (type != KlibPropertyExtensionVisitor.TYPE) return null
        return object : KlibPropertyExtensionVisitor() {
            override fun visitAnnotation(annotation: KmAnnotation) {
                proto.setExtension(
                    KlibMetadataProtoBuf.propertyAnnotation,
                    mutableListOf(annotation.writeAnnotation(c.strings).build())
                )
            }

            override fun visitGetterAnnotation(annotation: KmAnnotation) {
                proto.setExtension(
                    KlibMetadataProtoBuf.propertyGetterAnnotation,
                    mutableListOf(annotation.writeAnnotation(c.strings).build())
                )
            }

            override fun visitSetterAnnotation(annotation: KmAnnotation) {
                proto.setExtension(
                    KlibMetadataProtoBuf.propertySetterAnnotation,
                    mutableListOf(annotation.writeAnnotation(c.strings).build())
                )
            }

            override fun visitUniqId(uniqId: DescriptorUniqId) {
                proto.setExtension(
                    KlibMetadataProtoBuf.propertyUniqId,
                    uniqId.write().build()
                )
            }

            override fun visitFile(file: Int) {
                proto.setExtension(
                    KlibMetadataProtoBuf.propertyFile,
                    file
                )
            }

            override fun visitCompileTimeValue(value: KmAnnotationArgument<*>) {
                proto.setExtension(
                    KlibMetadataProtoBuf.compileTimeValue,
                    value.writeAnnotationArgument(c.strings).build()
                )
            }
        }
    }

    override fun writeConstructorExtensions(
        type: KmExtensionType,
        proto: ProtoBuf.Constructor.Builder,
        c: WriteContext
    ): KmConstructorExtensionVisitor? {
        if (type != KlibConstructorExtensionVisitor.TYPE) return null
        return object : KlibConstructorExtensionVisitor() {
            override fun visitAnnotation(annotation: KmAnnotation) {
                proto.setExtension(
                    KlibMetadataProtoBuf.constructorAnnotation,
                    mutableListOf(annotation.writeAnnotation(c.strings).build())
                )
            }

            override fun visitUniqId(uniqId: DescriptorUniqId) {
                proto.setExtension(
                    KlibMetadataProtoBuf.constructorUniqId,
                    uniqId.write().build()
                )
            }
        }
    }

    override fun writeTypeParameterExtensions(
        type: KmExtensionType,
        proto: ProtoBuf.TypeParameter.Builder,
        c: WriteContext
    ): KmTypeParameterExtensionVisitor? {
        if (type != KlibTypeParameterExtensionVisitor.TYPE) return null
        return object : KlibTypeParameterExtensionVisitor() {
            override fun visitAnnotation(annotation: KmAnnotation) {
                proto.setExtension(
                    KlibMetadataProtoBuf.typeParameterAnnotation,
                    mutableListOf(annotation.writeAnnotation(c.strings).build())
                )
            }

            override fun visitUniqId(uniqId: DescriptorUniqId) {
                proto.setExtension(
                    KlibMetadataProtoBuf.typeParamUniqId,
                    uniqId.write().build()
                )
            }
        }
    }

    override fun writeTypeExtensions(type: KmExtensionType, proto: ProtoBuf.Type.Builder, c: WriteContext): KmTypeExtensionVisitor? {
        if (type != KlibTypeExtensionVisitor.TYPE) return null
        return object : KlibTypeExtensionVisitor() {
            override fun visitAnnotation(annotation: KmAnnotation) {
                proto.setExtension(
                    KlibMetadataProtoBuf.typeAnnotation,
                    mutableListOf(annotation.writeAnnotation(c.strings).build())
                )
            }
        }
    }

    override fun createClassExtension(): KmClassExtension =
        KlibClassExtension()

    override fun createPackageExtension(): KmPackageExtension =
        KlibPackageExtension()

    override fun createFunctionExtension(): KmFunctionExtension =
        KlibFunctionExtension()

    override fun createPropertyExtension(): KmPropertyExtension =
        KlibPropertyExtension()

    override fun createConstructorExtension(): KmConstructorExtension =
        KlibConstructorExtension()

    override fun createTypeParameterExtension(): KmTypeParameterExtension =
        KlibTypeParameterExtension()

    override fun createTypeExtension(): KmTypeExtension =
        KlibTypeExtension()
}