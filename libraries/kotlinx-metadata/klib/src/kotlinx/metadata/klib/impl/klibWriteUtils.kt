/*
 * Copyright 2010-2019 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package kotlinx.metadata.klib.impl

import kotlinx.metadata.impl.PackageWriter
import org.jetbrains.kotlin.backend.common.serialization.metadata.KlibMetadataStringTable
import org.jetbrains.kotlin.library.SerializedMetadata
import org.jetbrains.kotlin.library.metadata.KlibMetadataProtoBuf
import org.jetbrains.kotlin.metadata.ProtoBuf

private data class SerializedPackage(val name: String, val fragments: List<ByteArray>)

class KlibPackageWriter(
    val stringTable: KlibMetadataStringTable
) : PackageWriter(stringTable) {

    fun write(moduleName: String, packageName: String): SerializedMetadata {

        val pkg = buildPackage(packageName, t.build())
        val packageNames = listOf(pkg.name)
        val packages = listOf(pkg.fragments)

        val header = serializeHeader(moduleName, packageNames)

        // TODO: It looks like SerializedMetadata.fragments field should be called `packages`.
        return SerializedMetadata(
            header.toByteArray(),
            packages,
            packageNames
        )
    }

    private fun buildPackage(
        fqName: String,
        packageProto: ProtoBuf.Package
    ): SerializedPackage {

        fun buildPackageFragment(
            isEmpty: Boolean
        ): ProtoBuf.PackageFragment {
            val (stringTableProto, nameTableProto) = stringTable.buildProto()
            return ProtoBuf.PackageFragment.newBuilder()
                .setPackage(packageProto)
                .setStrings(stringTableProto)
                .setQualifiedNames(nameTableProto)
                .also { packageFragment ->
                    packageFragment.setExtension(KlibMetadataProtoBuf.fqName, fqName)
                    packageFragment.setExtension(KlibMetadataProtoBuf.isEmpty, isEmpty)
                }
                .build()
        }
        return SerializedPackage(fqName, listOf(buildPackageFragment(false).toByteArray()))
    }

    private fun serializeHeader(
        moduleName: String, packageFragmentNames: List<String>
    ) = KlibMetadataProtoBuf.Header.newBuilder().apply {
        this.moduleName = "<$moduleName>"
        addAllPackageFragmentName(packageFragmentNames)
    }.build()
}