package algorithmsForDevelopers

fun main() {
    println(List(10) { it }.isSorted { o1, o2 -> o1.compareTo(o2) })
    println()

    println("bubble sort ----------")
    println(bubbleSort(listOf(5, 4, 1, 2, 6)))
    println(bubbleSort(listOf(1, 2, 6, 4, 5)))
    println(bubbleSort(List(10) { it }))

    println("chooseSort sort ------")
    println(chooseSort(listOf(5, 4, 1, 2, 6)))
    println(chooseSort(listOf(1, 2, 6, 4, 5)))
    println(chooseSort(List(10) { it }))

    println("insertSort sort ------")
    println(insertSort(listOf(5, 4, 1, 2, 6)))
    println(insertSort(listOf(1, 2, 6, 4, 5)))
    println(insertSort(List(10) { it }))

    println("sortingByCountingInt sort ------")
    println(sortingByCountingInt(listOf(5, 4, 1, 2, 6)))
    println(sortingByCountingInt(listOf(1, 2, 6, 4, 5)))
    println(sortingByCountingInt(List(10) { it }))
}

private fun <T> List<T>.isSorted(comparator: Comparator<T>): Boolean {
    this.forEachIndexed { index, element ->
        if (index != this.lastIndex) {
            if (comparator.compare(element, this[index + 1]) > 0) return false
        }
    }
    return true
}

/* Сортировать быстрее чем n * logn невозможно (доказано математически)

Почему?
Предположим, у нас есть n-элементов (вектор), которые нужно отсортировать: а1, а2, …, аn.

Любой алгоритм сортировки из любого входного вектора делает отсортированный выходной вектор.
Сортировка в данном случае делается путём попарных сравнений элементов друг с другом.
Если мы представим процесс сортировки графически, получится так называемое “дерево решений”.

В каждом узле такого дерева будет сравнение между соответствующими элементами.
Переход по левому потомку будет соответствовать ситуации, когда аi < аj, переход по правому — когда аi >= аj.
Когда дошли до конца дерева, то есть до листовой вершины, — алгоритм окончен, порядок определён.

Чему равна оценка снизу для такого алгоритма?
Это количество сравнений, то есть высота дерева решений, причём в его листьях n! перестановок исходных элементов.

Обозначим за h высоту этого дерева. Число листьев для полного дерева высоты h составляет (2h - 1).
Единицу отбрасываем, потому что нас интересует только порядок величины.

Таким образом, у нас получается, что для произвольного дерева имеем:
n! < 2h

Логарифмируя, получаем:
log(n!) < log(2h) ~ h

По формуле Стирлинга log(n!) ~ n * log(n), таким образом:
h ~ n * log(n)

Это означает, что сортировка, работающая на сравнениях, не может сортировать быстрее, чем за n * log(n) сравнений.
*/

/**
 * Пузырьковая сортировка
 *
 * Ассимптотическая сложность - O(n**2)
 *
 * Оптимизации:
 *  - если массив отсортирован то сортировка прекращается;
 *  - каждый проход проходит не до конца (кроме первого) так как нет смысла проверять отсортированную часть;
 *
 *  Минусы:
 *  - требуется n**2 перестановок элементов (по сравнению с сортировкой выбором)
 */
private fun <T : Comparable<T>> bubbleSort(list: List<T>): List<T> {
    val workList = list.toMutableList()
    var isNeedContinue = true
    for (i in 0..workList.lastIndex) {
        if (!isNeedContinue) break
        isNeedContinue = false
        for (j in 0..workList.lastIndex - 1 - i) {
            if (workList[j] > workList[j + 1]) {
                val temp = workList[j]
                workList[j] = workList[j + 1]
                workList[j + 1] = temp
                isNeedContinue = true
            }
        }
    }
    return workList.toList()
}

/**
 * Сортировка выбором
 *
 * Ассимптотическая сложность - O(n**2)
 *
 * Плюсы:
 *  - swap происходит только если нужно (колличество перестановок - n)
 *
 * Минусы:
 *  - всегда количество операций (n**2)/2 даже если массив изначально отсортирован
 *  - не стабильная (устойчивая) (т.е. если сортировать по двум св-вам, порядок после первого прохода не сохраняется)
 *  пример: ["Z 0", "B 1", "B 2", "Z 3", "A 5"] - цифры по возрастанию, но если начать сортировку по буквам то "Z 0" <-> "A 5",
 *  что приведет к разрушению порядка сортировок по цифрам
 */
private fun <T : Comparable<T>> chooseSort(list: List<T>): List<T> {
    val workList = list.toMutableList()
    for (i in 0..workList.lastIndex) {
        var smallest = i
        var isNeedSwap = false
        for (j in i + 1..workList.lastIndex) {
            if (workList[j] < workList[smallest]) {
                smallest = j
                isNeedSwap = true
            }
        }
        if (isNeedSwap) {
            val temp = workList[i]
            workList[i] = workList[smallest]
            workList[smallest] = temp
        }
    }
    return workList.toList()
}

/**
 * Сортировка вставками
 *
 * Ассимптотическая сложность - O(n**2)
 *
 * Плюсы:
 *  - стабильная (устойчивая) - т.е. порядок будет сохранен. Пример ["Z 0", "B 1", "B 2", "Z 3", "A 5"] -> ["A 5", "B 1", "B 2", "Z 0", "Z 3"]
 * т.е. порядок сортировок по цифрам сохранился
 *
 */
private fun <T : Comparable<T>> insertSort(list: List<T>): List<T> {
    if (list.isEmpty() || list.size < 2) return list
    val workingList = list.toMutableList()
    for (i in 1..workingList.lastIndex) {
        val pt = workingList[i]
        var j = i
        while (j > 0 && pt < workingList[j - 1]) {
            workingList[j] = workingList[j - 1]
            j--
        }
        workingList[j] = pt
    }
    return workingList.toList()
}

/**
 * Сортировка подсчетом
 *
 * Ассимптотическая сложность - O(n**2)
 *
 * Плюсы:
 *  - стабильная (устойчивая) - т.е. порядок будет сохранен. Пример ["Z 0", "B 1", "B 2", "Z 3", "A 5"] -> ["A 5", "B 1", "B 2", "Z 0", "Z 3"]
 * т.е. порядок сортировок по цифрам сохранился
 *
 */
//private fun <T: Comparable<T>> sortingByCounting(list: List<T>): List<T> {
//
//}

/* Частный случай, подходит только для числового массива с числами от 0 до 1000
* */
private fun sortingByCountingInt(list: List<Int>): List<Int> {
    val counts = MutableList(1000) { 0 }
    for (number in list) {
        counts[number] += 1
    }

    val result = mutableListOf<Int>()
    for ((index, number) in counts.withIndex()) {
        repeat(number) {
            result += index
        }
    }

    return result.toList()
}