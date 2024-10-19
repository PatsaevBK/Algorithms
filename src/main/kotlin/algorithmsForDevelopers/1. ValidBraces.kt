package algorithmsForDevelopers

fun main() {
    println(!isValidBraces("((}}"))
    println(isValidBraces("(())"))
    println(isValidBraces("({})"))
    println(!isValidBraces("({}})"))
}

private fun isValidBraces(braces: String): Boolean {
    val bracesPairs = mapOf('[' to ']', '{' to '}', '(' to ')')

    val stack = ArrayDeque<Char?>()

    for (char in braces) {
        when (char) {
            in bracesPairs.keys -> {
                stack.add(char) // Добавляем открывающую скобку в стек
            }

            in bracesPairs.values -> {
                if (stack.isEmpty() || bracesPairs[stack.removeLast()] != char) {
                    return false // Неверная последовательность скобок или скобки не подходят
                }
            }

            else -> {
                return false // Неподдерживаемый символ
            }
        }
    }
    return stack.isEmpty() // Проверяем, что все открывающие скобки закрыты
}
