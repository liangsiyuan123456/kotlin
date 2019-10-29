/*
 * Copyright 2010-2019 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package kotlinx.metadata.klib

import org.jetbrains.kotlin.metadata.ProtoBuf

class KmQualifiedNamesTable(
    val qualifiedNames: List<QualifiedName>
) {
    class QualifiedName(
        val parentQualifiedName: Int?,
        val shortName: Int,
        val kind: Kind
    ) {
        enum class Kind {
            CLASS, PACKAGE, LOCAL
        }
    }
}

fun KmQualifiedNamesTable.writeQualifiedNamesTable():
        ProtoBuf.QualifiedNameTable.Builder =
    ProtoBuf.QualifiedNameTable.newBuilder().also { proto ->
        proto.addAllQualifiedName(qualifiedNames.map { it.write().build() })
    }

private fun KmQualifiedNamesTable.QualifiedName.write():
        ProtoBuf.QualifiedNameTable.QualifiedName.Builder =
    ProtoBuf.QualifiedNameTable.QualifiedName.newBuilder().also { proto ->
        proto.shortName = shortName
        parentQualifiedName?.let { proto.parentQualifiedName = it}
        proto.kind = kind.write()
    }

private fun KmQualifiedNamesTable.QualifiedName.Kind.write():
        ProtoBuf.QualifiedNameTable.QualifiedName.Kind = when (this) {
    KmQualifiedNamesTable.QualifiedName.Kind.CLASS -> ProtoBuf.QualifiedNameTable.QualifiedName.Kind.CLASS
    KmQualifiedNamesTable.QualifiedName.Kind.PACKAGE -> ProtoBuf.QualifiedNameTable.QualifiedName.Kind.PACKAGE
    KmQualifiedNamesTable.QualifiedName.Kind.LOCAL -> ProtoBuf.QualifiedNameTable.QualifiedName.Kind.LOCAL
}
