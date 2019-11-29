package com.orgmir.snapshot

import java.io.File

class Recorder(
    private val snapshotDir: File = DefaultSnapshotDir().getDir()
) {
    fun <T> assertSnapshot(snapshot: Snapshot<T>, name: String? = null) {
        val filename = name ?: extractTestCaseName()
        val file = File(snapshotDir, "$filename.${snapshot.fileExtension}")
        if (file.exists()) {
            matchValueWithExistingSnapshot(file, snapshot)
        } else {
            writeSnapshot(file, snapshot)
        }
    }

    private fun extractTestCaseName(): String {
        val testCaseTrace = TestNameDetector.getTestStackElement()
        return if (testCaseTrace == null) {
            throw RuntimeException("Snapshot library could not find the name of the test.")
        } else {
            "${testCaseTrace.className}_${testCaseTrace.methodName}"
        }
    }


    private fun <T> matchValueWithExistingSnapshot(file: File, snapshot: Snapshot<T>) {
        val referenceBytes = file.readBytes()
        val reference = snapshot.serializer.fromByteArray(referenceBytes)
        val actual = snapshot.snap()
        val (hasChanged, msg) = snapshot.diffable.diff(actual, reference)
        if (hasChanged) {
            throw SnapshotException(msg)
        }
        // TODO log that we are all G?
    }

    private fun <T> writeSnapshot(file: File, snapshot: Snapshot<T>) {
        val bytes = snapshot.serializer.toByteArray(snapshot.snap())
        file.writeBytes(bytes)
        // TODO log that no snapshot was found so we saved one
    }
}