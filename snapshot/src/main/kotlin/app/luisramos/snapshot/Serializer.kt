package app.luisramos.snapshot

/**
 * Serializes a type from and to ByteArray
 */
interface Serializer<T> {
    fun toByteArray(value: T): ByteArray
    fun fromByteArray(data: ByteArray): T
}