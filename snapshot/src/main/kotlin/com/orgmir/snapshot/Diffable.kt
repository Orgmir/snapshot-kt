package com.orgmir.snapshot

/**
 * Represents a result of diffing two snapshots
 *
 * val (hasChanged, errorMessage) = result
 */
typealias DiffResult = Pair<Boolean, String>

/**
 * Diffs two values and returns an error message
 * if they are different
 */
interface Diffable<T> {
    fun diff(actual: T, reference: T): DiffResult
}