package algorithmsForDevelopers

import kotlin.math.pow

private var steps = 0

fun main() {
    val result = quickPow(2.0, 32)
    println(steps)
    println(result == 2.0.pow(32))
}

private fun quickPow(number: Double, power: Int): Double {
    steps++
    return when {
        power == 0 -> 1.0

        power % 2 == 0 -> {
            val newNumber = quickPow(number,power / 2)
            newNumber * newNumber
        }

        else -> {
            number * quickPow(number, power - 1)
        }
    }
}