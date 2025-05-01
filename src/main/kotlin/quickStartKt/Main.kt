package quickStartKt

import java.io.File

private fun main() {
    val file = File("test")
    file.mkdir()
    val d = File("test/text.txt")
    d.writeText("AAA")
}

