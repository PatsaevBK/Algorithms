package algorithmsForDevelopers

import java.util.PriorityQueue

fun main() {
    println(maxNotCrossedStripes(listOf(Pair(1, 5), Pair(1, 2), Pair(3, 4), Pair(2, 3))))
    println("-----")
    println(optimalPyramid(listOf(Pair(10, 11), Pair(20, 100), Pair(30, 10))))
}

/* Задача про полоски:
* Есть N полосок.
* Полоски лежат просто все на одной прямой, у каждой есть координата начала и конца - I[i] и r[i].
* Нужно выбрать максимальное колочество полосок так, чтобы выбранные полоски не пересекались
* между собой. (Аналог задачи с расписание и наибольшим количеством уроков)
* Пример:
* N = 4,    left =  {1, 1, 3, 2}
*           right = {5, 2, 4, 3}
* result = 3 {1:2,2:3,3:4}
*/

private fun maxNotCrossedStripes(stripes: List<Pair<Int, Int>>): Int {
    //сортируем по хвосту
    val sortByTail = stripes.sortedBy { it.second }
    var end = 0
    val result = mutableListOf<Pair<Int, Int>>()
    sortByTail.forEach {
        if (it.first >= end) {
            result += it
            end = it.second
        }
    }
    return result.size
}

/* Задача про коробки:
* Есть N коробок.
* У каждой коробки 2 параметра.
* weight[i] - вес коробки
* strong[i] - максимальный вес, который коробка модет выдержать
* Нужно сложить оптимальную пирамиду
*
* Пример:
* N = 3,    weight = {10, 20, 30}
*           strong = {11, 100, 10}
* result = {20:100, 30:10, 10:11}
*/

private fun optimalPyramid(list: List<Pair<Int, Int>>): String {
    val sortedList = list.sortedBy { it.first + it.second }
    val comparator = compareBy<Pair<Int, Int>> { it.second }
    val heaviest: PriorityQueue<Pair<Int, Int>> = PriorityQueue<Pair<Int, Int>>(comparator)
    val resultPyramid = mutableListOf<Pair<Int, Int>>()
    var totalWeight = 0

    sortedList.forEach { box ->
        if (box.second >= totalWeight) {
            heaviest += box
            resultPyramid += box
            totalWeight += box.first
        } else {
            //try to decrease weight
            if (box.first < heaviest.peek().first) {
                val removedBox = heaviest.poll()
                totalWeight -= removedBox.first
                heaviest += box
                val removedBoxIndex = resultPyramid.indexOf(removedBox)
                resultPyramid[removedBoxIndex] = box
                totalWeight += box.first
            }
        }
    }

    //prepare result
    resultPyramid.reversed()
    val resultString = buildString {
        resultPyramid.forEachIndexed { index, element ->
            if (index == resultPyramid.lastIndex){
                append("\u2193  ")
            } else {
                append("|  ")
            }
            appendLine(element)
        }
    }
    return resultString
}

/* Алгоритм Хафмана
* нужен для сжатия информации
*
*/