package grockingAlgorithms

fun main() {
    println(recSum(listOf(1,11,121)))
    println(tailRecSum(listOf(1,11,121)))
}

private fun recSum(list: List<Int>): Int {
    println(list)
    if (list.isEmpty()) return 0
    if (list.size == 1) return list[0]
    return list[0] + recSum(list.subList(1, list.size))
}

private tailrec fun tailRecSum(list: List<Int>, acc: Int = 0): Int {
    println(list)
    if (list.isEmpty()) return 0
    if (list.size == 1) return acc + list[0]
    return tailRecSum(list.subList(1, list.size), acc + list[0])
}