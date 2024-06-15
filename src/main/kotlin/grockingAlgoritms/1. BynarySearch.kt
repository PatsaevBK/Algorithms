package grockingAlgoritms

fun main() {
    val list = List(12) { it }
    println(list)
    println(recBinarySearchIndex(list, 0))
//    list.forEach {
//        println(it)
//        println(binarySearch(list, it))
//    }
//    (-1..18).forEach {
//        println(it)
//        println(grockingAlgoritms.myBinarySearch(list, it))
//    }
//    binarySearch(list, 9)
    println(standardBinarySearch(list, list.first()))
    println(standardBinarySearch(list, list.last()))
}

fun myBinarySearch(list: List<Int>, number: Int): Int {
    var first = 0
    var last = list.lastIndex
    while (first < last) {
        val mid = (first + last) / 2
        return when {
            number == list[mid] -> mid
            number < list[mid] -> {
                last = mid
                continue
            }

            number > list[mid] -> {
                first = mid + 1
                if (first == list.lastIndex) {
                    return if (number == list[first]) number else -1
                }
                continue
            }

            else -> -1
        }
    }
    return -1
}

fun standardBinarySearch(list: List<Int>, number: Int): Int {
    var first = 0
    var last = list.lastIndex
    while (first <= last) {
        val mid = (first + last) / 2
        when {
            (list[mid] == number) -> return mid
            (list[mid] < number) -> {
                first = mid + 1
                continue
            }
            else -> {
                last = mid - 1
                continue
            }
        }
    }
    return -1
}

//ошибка, возвращает то число из массива а должен индекс
//fun recBinarySearchSublist(list: List<Int>, number: Int): Int {
//    return if (list.isEmpty()) {
//        -1
//    } else if (list.size == 1) {
//        list[0]
//    } else {
//        val mid = list.size / 2
//        when {
//            list[mid] == number -> recBinarySearchSublist(list.subList(mid, mid + 1), number)
//            list[mid] > number -> recBinarySearchSublist(list.subList(0, mid), number)
//            list[mid] < number -> recBinarySearchSublist(list.subList(mid + 1, list.size), number)
//            else -> throw IllegalStateException("Imposable")
//        }
//    }
//}

fun recBinarySearchIndex(list: List<Int>, number: Int): Int {
    fun recBinarySearchIndex(list: List<Int>, number: Int, start: Int, end: Int): Int {
        if (start > end) {
            return -1
        }

        val mid = (start + end) / 2
        when {
            list[mid] == number -> {
                return mid
            }
            list[mid] < number -> {
                val newStart = mid + 1
                return recBinarySearchIndex(list = list, number = number, start = newStart, end = end)
            }
            list[mid] > number -> {
                val newEnd = mid - 1
                return recBinarySearchIndex(list = list, number = number, start = start, end = newEnd)
            }
        }
        throw IllegalStateException("Imposable")
    }
    return recBinarySearchIndex(list = list, number = number, start = 0, end = list.lastIndex)
}

//последнее красивое решение
fun easyBinarySearch(numbers: List<Int>, searchNumber: Int): Int {
    var start = 0
    var mid = numbers.size / 2
    var end = numbers.lastIndex
    while (start <= end) {
        if (numbers[mid] == searchNumber) return mid
        if (numbers[mid] > searchNumber) {
            end = mid - 1
        } else {
            start = mid + 1
        }
        mid = (start + end) / 2
    }
    return -1
}