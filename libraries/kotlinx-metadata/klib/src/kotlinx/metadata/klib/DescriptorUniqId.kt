/*
 * Copyright 2010-2019 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package kotlinx.metadata.klib

import org.jetbrains.kotlin.library.metadata.KlibMetadataProtoBuf

class DescriptorUniqId(val index: Long)

fun DescriptorUniqId.write(): KlibMetadataProtoBuf.DescriptorUniqId.Builder =
    KlibMetadataProtoBuf.DescriptorUniqId.newBuilder().apply {
        index = this@write.index
    }

fun KlibMetadataProtoBuf.DescriptorUniqId.readDescriptorUniqId(): DescriptorUniqId =
    DescriptorUniqId(index)