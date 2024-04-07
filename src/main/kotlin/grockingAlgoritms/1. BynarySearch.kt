package grockingAlgoritms

fun main() {
    val list = List(12) { it }
    println(list)
    println(recBinarySearch(list, 8))
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
    println(recBinarySearch(list, list.first()))
    println(standardBinarySearch(list, list.last()))
    println(recBinarySearch(list, list.last()))
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

fun recBinarySearch(list: List<Int>, number: Int): Int {
    return if (list.isEmpty()) {
        -1
    } else if (list.size == 1) {
        list[0]
    } else {
        val mid = list.size / 2
        when {
            list[mid] == number -> recBinarySearch(list.subList(mid, mid + 1), number)
            list[mid] > number -> recBinarySearch(list.subList(0, mid), number)
            list[mid] < number -> recBinarySearch(list.subList(mid + 1, list.size), number)
            else -> throw IllegalStateException("Imposable")
        }
    }
}