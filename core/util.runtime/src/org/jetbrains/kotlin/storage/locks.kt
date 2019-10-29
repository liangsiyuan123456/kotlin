/*
 * Copyright 2010-2019 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.storage

import java.lang.UnsupportedOperationException
import java.util.*

enum class LockNames(val lockOrder: Int, val debugName: String, val lockFactory: (Int, String, LockBlock?) -> LockBlock) {
    SDK(1, "sdk", { order, name, lockBlock -> SdkLock(order, name, lockBlock) }),
    Libraries(2, "project libraries", { order, name, lockBlock -> LibrariesLock(order, name, lockBlock) }),
    NotUnderContentRootModuleInfo(3, "LibrarySourceInfo or NotUnderContentRootModuleInfo", { order, name, lockBlock -> ModulesLock(order, name, lockBlock) }),
    Modules(4, "project source roots and libraries", { order, name, lockBlock -> ModulesLock(order, name, lockBlock) }),
    ScriptDependencies(5, "dependencies of scripts", { order, name, lockBlock -> ScriptDependenciesLock(order, name, lockBlock) }),
    SpecialInfo(6, "completion/highlighting in ", { order, name, lockBlock -> SpecialInfoLock(order, name, lockBlock) })
}

private object LockHelper {
    // overloaded method for the sake of java interop
    @JvmStatic
    fun resolveLock(name: String): LockBlock = resolveLock(null, name)

    // overloaded method for the sake of java interop
    @JvmStatic
    fun resolveLock(lockOrder: Int?, name: String): LockBlock = resolveLock(lockOrder, name, null)

    @JvmStatic
    fun resolveLock(lockOrder: Int?, name: String, sourceLockBlock: LockBlock?): LockBlock =
        lockOrder?.let { order -> LockNames.values().find { it.lockOrder == order }?.lockFactory?.invoke(order, name, sourceLockBlock) }
            ?: SimpleLock(sourceLockBlock?.lock ?: Object())
}

private object AcquiredLocks : ThreadLocal<Stack<LockHolderInfo>>() {
    override fun initialValue(): Stack<LockHolderInfo> = Stack()
}

interface LockBlock {
    val lock: Any
    fun <T> guarded(computable: () -> T?): T?
}

private object NoLockBlock : LockBlock {
    override val lock: Any
        get() = throw UnsupportedOperationException("NoLockBlock has no lock")

    override fun <Any> guarded(computable: () -> Any?): Any? {
        return computable()
    }
}

private class SimpleLock(override val lock: Any) : LockBlock {
    override fun <T> guarded(computable: () -> T?): T? =
        // Use `synchronized` as dead lock case will be handled by JVM and would be immediately visible rather with ReentrantLock
        synchronized(lock) {
            computable()
        }
}

data class LockHolderInfo(val order: Int, val name: String, val lock: Any) {
    override fun toString(): String {
        return "'$name'/$order@$lock"
    }
}

abstract class TrackLock(val order: Int, val name: String, override val lock: Any) : LockBlock {
    protected fun <T> checkAndExecute(computable: () -> T?): T? {
        preLockCheck()

        // Use `synchronized` as dead lock case will be handled by JVM and would be immediately visible rather with ReentrantLock
        return synchronized(lock) {
            postLock()
            try {
                computable()
            } finally {
                preUnlock()
            }
        }
    }

    private inline fun preLockCheck() {
        val infos: Stack<LockHolderInfo> = AcquiredLocks.get()
        val peek = if (infos.isEmpty()) null else infos.peek()
        if (peek != null) {
            check(order <= peek.order) {
                ("${Thread.currentThread().name}: Incorrect lock order: '$name'/$order@$lock tries to acquire lock after $peek")
            }

            // fail in case if diff lock instances
            check(!(order == peek.order && this !== peek.lock)) {
                ("${Thread.currentThread().name}: Incorrect lock order: '$name'/$order@$lock same lock order is already acquired for $peek")
            }
        }
    }


    private inline fun postLock() {
        val infos: Stack<LockHolderInfo> = AcquiredLocks.get()
        infos.push(LockHolderInfo(order, name, lock))
    }

    private inline fun preUnlock() {
        val infos: Stack<LockHolderInfo> = AcquiredLocks.get()
        infos.pop()
    }

}

/**
 * The reason behind all those SdkLock, LibrariesLock etc is to keep <b>named trace</b> in thread dumps / stack traces
 */
private class SdkLock(order: Int, name: String, lockBlock: LockBlock?) : TrackLock(order, name, lockBlock?.lock ?: Object()) {
    override fun <T> guarded(computable: () -> T?): T? = checkAndExecute(computable)
}

private class LibrariesLock(order: Int, name: String, lockBlock: LockBlock?) : TrackLock(order, name, lockBlock?.lock ?: Object()) {
    override fun <T> guarded(computable: () -> T?): T? = checkAndExecute(computable)
}

private class ModulesLock(order: Int, name: String, lockBlock: LockBlock?) : TrackLock(order, name, lockBlock?.lock ?: Object()) {
    override fun <T> guarded(computable: () -> T?): T? = checkAndExecute(computable)
}

private class ScriptDependenciesLock(order: Int, name: String, lockBlock: LockBlock?) : TrackLock(order, name, lockBlock?.lock ?: Object()) {
    override fun <T> guarded(computable: () -> T?): T? = checkAndExecute(computable)
}

private class SpecialInfoLock(order: Int, name: String, lockBlock: LockBlock?) : TrackLock(order, name, lockBlock?.lock ?: Object()) {
    override fun <T> guarded(computable: () -> T?): T? = checkAndExecute(computable)
}