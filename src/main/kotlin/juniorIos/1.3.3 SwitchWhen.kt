package juniorIos


import juniorIos.Color.*
import juniorIos.Filling.*

@OptIn(ExperimentalStdlibApi::class)
fun main() {
    val packageMAndM = List(100) {
        MAndM(
            color = Color.entries[it % Color.entries.size],
            filling = Filling.entries[it % Filling.entries.size]
        )
    }
    val heap1 = mutableListOf<MAndM>()
    val heap2 = mutableListOf<MAndM>()
    val heap3 = mutableListOf<MAndM>()
    val heapUnknown = mutableListOf<MAndM>()
    for (e in packageMAndM) {
        when (e) {
            MAndM(RED, CHOCOLATE) -> {
                heap1.add(e)
            }
            MAndM(YELLOW, NUTS) -> {
                heap2.add(e)
            }
            MAndM(BROWN, CHOCOLATE), MAndM(GREEN, CHOCOLATE) -> {
                heap3.add(e)
            }
            else -> {
                heapUnknown.add(e)
            }
        }
    }
    println("""
        quantity Red + Chocolate = ${heap1.size}
        quantity Yellow + Nuts = ${heap2.size}
        quantity Brown, Green + Chocolate = ${heap3.size}
            quantity Unknown = ${heapUnknown.size}
    """.trimIndent())
}

private data class MAndM(
    val color: Color = YELLOW,
    val filling: Filling = NUTS
)

private enum class Color {
    GREEN, RED, YELLOW, BROWN, BLUE
}

private enum class Filling {
    CHOCOLATE, NUTS
}

