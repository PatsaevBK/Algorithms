package grockingAlgoritms

fun main() {
    val a = recFactorial(5)
    assert(a == 120L)
    val b = tailRecFactorial(5)
    assert(b == 120L)
    val n = recCountSize(listOf(1,1,1,2))
    assert(n == 4)
    println(recMax(listOf(0, 2, 1, 99)))
}

private fun recFactorial(n: Int): Long {
    if (n == 1) return 1L
    return n * recFactorial(n - 1)
}

private tailrec fun tailRecFactorial(n: Int, acc: Long = 1L): Long {
    if (n == 1) return acc
    return tailRecFactorial(n - 1, acc * n)
}

private fun <T> recCountSize(list: List<T>): Int {
    return if (list.isEmpty()) {
        0
    } else {
        1 + recCountSize(list.subList(1, list.size))
    }
}

private fun <T: Comparable<T>> recMax(list: List<T>): T {
    return if (list.size == 1) {
        list[0]
    } else {
        maxOf(list[0], recMax(list.subList(1, list.size)))
    }
}