package juniorIos

import kotlin.random.Random

fun main() {
    val test = List(20) { Random.nextInt(until = 1000) }
    println(customFilter(test) { it % 2 == 0 })

    val string = "random string no matter"
    println(transformString(string) { it.uppercase() })

    val randomOddNumbers = generateAndTransform(from = 1, until = 10, size = 10) { it * 2 + 1 }
    println(randomOddNumbers)
}

/*
* Напишите функцию, которая принимает массив чисел и замыкание, определяющее условие фильтрации.
* Функция должна вернуть новый массив, содержащий только те элементы, которые удовлетворяют условию замыкания.
* Не используйте стандартную функцию filter(_:).
* */
private fun customFilter(numbers: List<Int>, predicate: (Int) -> Boolean): List<Int> {
    val result = mutableListOf<Int>()
    for (number in numbers) {
        if (predicate(number)) result += number
    }
    return result.toList()
}

/*
* Напишите функцию, которая принимает строку и замыкание, которое преобразует каждый символ строки.
* Функция должна вернуть новую строку, состоящую из преобразованных символов.
* */

private fun transformString(input: String, transform: (Char) -> String): String {
    return buildString {
        for (char in input) {
            append(transform(char))
        }
    }
}

/*
* Создайте функцию, которая генерирует массив случайных чисел в заданном диапазоне.
* Функция должна принимать начальное и конечное значения диапазона, а также количество чисел, которые нужно сгенерировать.
* В качестве дополнительного параметра функция должна принимать замыкание, которое выполняет какую-либо операцию над каждым
* сгенерированным числом (например, увеличивает его на 1 или умножает на 2). Функция должна вернуть массив преобразованных чисел.
* */

fun generateAndTransform(from: Int, until: Int, size: Int, transform: (Int) -> Int): List<Int> {
    val result = mutableListOf<Int>()
    repeat(size) {
        result += transform(Random.nextInt(from = from, until = until))
    }
    return result.toList()
}
