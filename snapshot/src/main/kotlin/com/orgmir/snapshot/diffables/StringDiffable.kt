package com.orgmir.snapshot.diffables

import com.orgmir.snapshot.DiffResult
import com.orgmir.snapshot.Diffable

class StringDiffable : Diffable<String> {
    override fun diff(actual: String, reference: String): DiffResult =
        (actual != reference) to "The values do not match.\n Reference:\n$reference\n Actual:\n$actual"
}