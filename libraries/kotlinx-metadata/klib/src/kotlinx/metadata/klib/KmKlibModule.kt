/*
 * Copyright 2010-2019 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package kotlinx.metadata.klib

import org.jetbrains.kotlin.library.SerializedMetadata
import org.jetbrains.kotlin.metadata.ProtoBuf
import org.jetbrains.kotlin.metadata.serialization.StringTable

// TODO: We need to keep order of fragments in sync.
fun writeModule(
    stringTable: StringTable,
    header: KmHeader,
    fragments: List<KmPackageFragment>
): SerializedMetadata {

    val fragmentsProtos: List<ProtoBuf.PackageFragment> = fragments.map {
        val visitor = KmPackageFragmentWriter(stringTable)
        it.accept(visitor)
        visitor.t.build()
    }



    return SerializedMetadata()
}