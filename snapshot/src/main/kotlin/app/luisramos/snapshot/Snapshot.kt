package app.luisramos.snapshot

/**
 * Snapshot of something we want to save a snapshot
 */
class Snapshot<T>(
    val fileExtension: String,
    val serializer: Serializer<T>,
    val diffable: Diffable<T>,
    val snap: () -> T
)