package com.orgmir.snapshot

import java.io.File

class DefaultSnapshotDir : SnapshotDir {
    override fun getDir(): File {
        val externalDir = System.getProperty("user.dir") ?: ""
        if (externalDir.isEmpty() || externalDir == "/") {
            throw RuntimeException("No user.dir found. DefaultSnapshotDir needs it to be set so we can save snapshots files in the project directory")
        }

        val dir = "$externalDir/__snapshots__/"
        val child = "$dir/default"

        File(dir).mkdirs()

        val childDir = File(child)
        childDir.mkdir()

        if (!childDir.exists()) {
            throw RuntimeException("Failed to create the directory ${childDir.absolutePath}.")
        }
        return childDir
    }
}