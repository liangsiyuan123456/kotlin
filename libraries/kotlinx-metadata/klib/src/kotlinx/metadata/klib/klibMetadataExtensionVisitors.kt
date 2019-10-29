/*
 * Copyright 2010-2019 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package kotlinx.metadata.klib

import kotlinx.metadata.*

abstract class KlibFunctionExtensionVisitor : KmFunctionExtensionVisitor {

    abstract fun visitAnnotation(annotation: KmAnnotation)

    abstract fun visitUniqId(uniqId: DescriptorUniqId)

    abstract fun visitFile(file: Int)

    override val type: KmExtensionType
        get() = TYPE

    companion object {
        val TYPE = KmExtensionType(KlibFunctionExtensionVisitor::class)
    }
}

abstract class KlibClassExtensionVisitor : KmClassExtensionVisitor {

    abstract fun visitAnnotation(annotation: KmAnnotation)

    abstract fun visitUniqId(uniqId: DescriptorUniqId)

    abstract fun visitFile(file: Int)

    override val type: KmExtensionType
        get() = TYPE

    companion object {
        val TYPE = KmExtensionType(KlibClassExtensionVisitor::class)
    }
}

abstract class KlibTypeExtensionVisitor : KmTypeExtensionVisitor {

    abstract fun visitAnnotation(annotation: KmAnnotation)

    override val type: KmExtensionType
        get() = TYPE

    companion object {
        val TYPE = KmExtensionType(KlibTypeExtensionVisitor::class)
    }
}

abstract class KlibPropertyExtensionVisitor : KmPropertyExtensionVisitor {

    abstract fun visitAnnotation(annotation: KmAnnotation)

    abstract fun visitGetterAnnotation(annotation: KmAnnotation)

    abstract fun visitSetterAnnotation(annotation: KmAnnotation)

    abstract fun visitFile(file: Int)

    abstract fun visitUniqId(uniqId: DescriptorUniqId)

    abstract fun visitCompileTimeValue(value: KmAnnotationArgument<*>)

    override val type: KmExtensionType
        get() = TYPE

    companion object {
        val TYPE = KmExtensionType(KlibPropertyExtensionVisitor::class)
    }
}

abstract class KlibConstructorExtensionVisitor : KmConstructorExtensionVisitor {

    abstract fun visitAnnotation(annotation: KmAnnotation)

    abstract fun visitUniqId(uniqId: DescriptorUniqId)

    override val type: KmExtensionType
        get() = TYPE

    companion object {
        val TYPE = KmExtensionType(KlibConstructorExtensionVisitor::class)
    }
}

abstract class KlibTypeParameterExtensionVisitor : KmTypeParameterExtensionVisitor {

    abstract fun visitAnnotation(annotation: KmAnnotation)

    abstract fun visitUniqId(uniqId: DescriptorUniqId)

    override val type: KmExtensionType
        get() = TYPE

    companion object {
        val TYPE = KmExtensionType(KlibTypeParameterExtensionVisitor::class)
    }
}

abstract class KlibPackageExtensionVisitor : KmPackageExtensionVisitor {

    abstract fun visitClass(klass: KmClass)

    override val type: KmExtensionType
        get() = TYPE

    companion object {
        val TYPE = KmExtensionType(KlibPackageExtensionVisitor::class)
    }
}