package grockingAlgoritms

import kotlin.random.Random

var COUNT = 0
fun main() {
    val list1 = List(10) { Random.nextInt(1000) }
    println(selectionSort(list1))
    println(COUNT)
}
fun <T : Comparable<T>> selectionSort(list: List<T>): List<T> {
    val locale = list.toMutableList()
    val result = mutableListOf<T>()
    repeat(locale.size) {
        val i = findSmallest(locale)
        result += locale[i]
        locale.removeAt(i)
    }
    return result.toList()
}

private fun <T : Comparable<T>> findSmallest(list: List<T>): Int {
    var i = 0
    for ((index: Int, e: T) in list.withIndex()) {
        if (e < list[i]) i = index
        COUNT++
    }
    return i
}

