/*
 * Copyright 2000-2018 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.idea.caches.resolve.util

import com.intellij.openapi.project.Project
import org.jetbrains.kotlin.context.GlobalContextImpl
import org.jetbrains.kotlin.idea.project.useCompositeAnalysis
import org.jetbrains.kotlin.storage.ExceptionTracker
import org.jetbrains.kotlin.storage.LockBasedStorageManager
import org.jetbrains.kotlin.storage.LockNames

private fun GlobalContextImpl.contextWithCompositeExceptionTracker(debugName: String, lockOrder: Int?): GlobalContextImpl {
    val newExceptionTracker = CompositeExceptionTracker(this.exceptionTracker)
    return GlobalContextImpl(
        storageManager.replaceExceptionHandling(debugName, lockOrder, newExceptionTracker),
        newExceptionTracker
    )
}

private fun GlobalContextImpl.contextWithNewLockAndCompositeExceptionTracker(debugName: String, lockOrder: Int? = null): GlobalContextImpl {
    val newExceptionTracker = CompositeExceptionTracker(this.exceptionTracker)
    return GlobalContextImpl(
        LockBasedStorageManager.createWithExceptionHandling(lockOrder, debugName, newExceptionTracker),
        newExceptionTracker
    )
}

internal fun GlobalContextImpl.contextWithCompositeExceptionTracker(
    project: Project,
    lockName: LockNames
): GlobalContextImpl =
    this.contextWithCompositeExceptionTracker(project, lockName.debugName, lockName.lockOrder)

internal fun GlobalContextImpl.contextWithCompositeExceptionTracker(
    project: Project,
    debugName: String,
    lockOrder: Int? = null
): GlobalContextImpl =
    if (project.useCompositeAnalysis) {
        this.contextWithCompositeExceptionTracker(debugName, lockOrder)
    } else {
        this.contextWithNewLockAndCompositeExceptionTracker(debugName, lockOrder)
    }

private class CompositeExceptionTracker(val delegate: ExceptionTracker) : ExceptionTracker() {
    override fun getModificationCount(): Long {
        return super.getModificationCount() + delegate.modificationCount
    }
}
