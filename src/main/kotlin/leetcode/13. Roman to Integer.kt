package leetcode

fun main() {

}

internal class RomanToInteger {
    fun formatRomanToInteger(romanNumber: String): Int {
        var result = 0

        val reminder = romanNumber.reversed()
        var max = 0

        for (symbol in reminder) {
            val number = symbol.getCost()
            if (number < max) {
                result -= number
            } else {
                result += number
            }
            max = number
        }
        return result
    }

    fun Char.getCost(): Int {
        return when(this) {
            'I' -> 1
            'V' -> 5
            'X' -> 10
            'L' -> 50
            'C' -> 100
            'D' -> 500
            'M' -> 1000
            else -> 0
        }
    }
}