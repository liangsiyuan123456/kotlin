/*
 * Copyright 2010-2019 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package kotlinx.metadata.klib

import kotlinx.metadata.Flags
import kotlinx.metadata.KmAnnotation
import kotlinx.metadata.impl.WriteContext
import kotlinx.metadata.impl.writeAnnotation
import org.jetbrains.kotlin.library.metadata.KlibMetadataProtoBuf

class KmHeader(
    val moduleName: String,
    val flags: Flags,
    val annotations: List<KmAnnotation>,
    val packageFragmentNames: List<String>,
    val emptyPackages: List<String>,
    val files: List<KmFile>
)

abstract class KmHeaderVisitor {

    abstract fun visitAnnotation(annotation: KmAnnotation)

    abstract fun visitFile(file: KmFile)

    abstract fun visitEnd()
}

fun writeHeader(
    c: WriteContext,
    moduleName: String,
    flags: Flags,
    packageFragmentNames: List<String>,
    emptyPackages: List<String>,
    output: (KlibMetadataProtoBuf.Header.Builder) -> Unit
): KmHeaderVisitor =
    object : KmHeaderVisitor() {
        private val t = KlibMetadataProtoBuf.Header.newBuilder()

        override fun visitAnnotation(annotation: KmAnnotation) {
            t.addAnnotation(annotation.writeAnnotation(c.strings))
        }

        override fun visitFile(file: KmFile) {
            t.addFile(file.writeFile())
        }

        override fun visitEnd() {
            t.moduleName = moduleName
            t.flags = flags
            t.addAllPackageFragmentName(packageFragmentNames)
            t.addAllEmptyPackage(emptyPackages)
            output(t)
        }
    }