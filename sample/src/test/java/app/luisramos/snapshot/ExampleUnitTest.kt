package app.luisramos.snapshot

import app.luisramos.snapshot.diffables.StringDiffable
import app.luisramos.snapshot.serializers.StringSerializer
import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {

    private val recorder = Recorder()

    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @Test
    fun snapshot_isRecorded() {
        val array = arrayOf("ones", "twos", "threes")
        recorder.assertSnapshot(array.snapshot())
    }

    fun Array<String>.snapshot() = Snapshot(
        fileExtension = "txt",
        serializer = StringSerializer(),
        diffable = StringDiffable(),
        snap = { joinToString() }
    )
}