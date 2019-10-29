/*
 * Copyright 2010-2019 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package kotlinx.metadata.klib

import org.jetbrains.kotlin.metadata.ProtoBuf

class KmStringTable(val strings: List<String>)

fun KmStringTable.writeStringTable(): ProtoBuf.StringTable.Builder =
    ProtoBuf.StringTable.newBuilder().apply {
        addAllString(strings)
    }
