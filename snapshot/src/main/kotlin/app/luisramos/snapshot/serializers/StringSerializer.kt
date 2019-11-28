package app.luisramos.snapshot.serializers

import app.luisramos.snapshot.Serializer

class StringSerializer : Serializer<String> {
    override fun toByteArray(value: String): ByteArray = value.toByteArray(charset = Charsets.UTF_8)

    override fun fromByteArray(data: ByteArray): String = data.toString(charset = Charsets.UTF_8)
}