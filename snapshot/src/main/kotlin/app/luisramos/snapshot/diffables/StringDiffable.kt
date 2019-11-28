package app.luisramos.snapshot.diffables

import app.luisramos.snapshot.DiffResult
import app.luisramos.snapshot.Diffable

class StringDiffable : Diffable<String> {
    override fun diff(actual: String, reference: String): DiffResult =
        (actual != reference) to "The values do not match.\n Reference:\n$reference\n Actual:\n$actual"
}