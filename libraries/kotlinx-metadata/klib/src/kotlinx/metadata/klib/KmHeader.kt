/*
 * Copyright 2010-2019 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package kotlinx.metadata.klib

import kotlinx.metadata.Flags
import kotlinx.metadata.KmAnnotation
import kotlinx.metadata.impl.writeAnnotation
import org.jetbrains.kotlin.library.metadata.KlibMetadataProtoBuf

class KmHeader(
    val moduleName: String,
    val flags: Flags,
    val annotations: List<KmAnnotation>,
    val packageFragmentNames: List<String>,
    val emptyPackages: List<String>,
    val files: List<File>
)

abstract class KmHeaderVisitor {
    abstract fun visitModuleName()
}

class KmHeaderWriter