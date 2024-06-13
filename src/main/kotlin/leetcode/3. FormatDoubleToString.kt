package leetcode


fun main() {
    val number = 12345678.11
    println(formatDoubleToString(number))
}

private fun formatDoubleToString(number: Double): String {
    val longNumber = (number * 100).toLong()
    val remainder = longNumber % 100
    val integerNumber = longNumber / 100
    return buildString {
        var count = 0
        val string = integerNumber.toString()
        for (index in string.lastIndex downTo 0) {
            if (count != 3) {
                append(string[index])
                count++
            } else {
                append(" ")
                count = 0
                append(string[index])
                count++
            }
        }
    }.reversed() + "." + remainder
}