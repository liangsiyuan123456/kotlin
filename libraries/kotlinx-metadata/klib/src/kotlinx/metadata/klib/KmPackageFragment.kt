/*
 * Copyright 2010-2019 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package kotlinx.metadata.klib

import kotlinx.metadata.KmClass
import kotlinx.metadata.KmClassVisitor
import kotlinx.metadata.KmPackage
import kotlinx.metadata.KmPackageVisitor
import kotlinx.metadata.impl.ClassWriter
import kotlinx.metadata.impl.PackageWriter
import kotlinx.metadata.impl.WriteContext
import org.jetbrains.kotlin.metadata.ProtoBuf
import org.jetbrains.kotlin.metadata.serialization.StringTable


abstract class KmPackageFragmentVisitor(protected open val delegate: KmPackageFragmentVisitor? = null) {

    open fun visitClass(klass: KmClass) {
        delegate?.visitClass(klass)
    }

    open fun visitPackage(pkg: KmPackage) {
        delegate?.visitPackage(pkg)
    }

}

class KmPackageFragment : KmPackageFragmentVisitor() {

    // TODO: StringTable, QualifiedNameTable?
    val classes: MutableList<KmClass> = ArrayList(0)
    var pkg: KmPackage? = null

    override fun visitClass(klass: KmClass) {
        classes += klass
    }

    override fun visitPackage(pkg: KmPackage) {
        this.pkg = pkg
    }

    fun accept(visitor: KmPackageFragmentVisitor) {
        pkg?.let(visitor::visitPackage)
        classes.forEach(visitor::visitClass)
    }
}

open class KmPackageFragmentWriter(
    private val stringTable: StringTable
) : KmPackageFragmentVisitor() {
    val t = ProtoBuf.PackageFragment.newBuilder()

    override fun visitClass(klass: KmClass) {
        val classWriter = object : ClassWriter(stringTable) {
            fun write(output: (ProtoBuf.Class.Builder) -> Unit) {
                output(t)
            }
        }
        klass.accept(classWriter)
        classWriter.write { t.addClass_(it.build()) }
    }

    override fun visitPackage(pkg: KmPackage) {
        val packageWriter = object : PackageWriter(stringTable) {
            fun write(output: (ProtoBuf.Package.Builder) -> Unit) {
                output(t)
            }
        }
        pkg.accept(packageWriter)
        packageWriter.write { t.`package` = it.build() }
    }

    open fun visitEnd() {}
}