package grockingAlgorithms

import java.util.LinkedList

fun main() {
    val map = mutableMapOf<String, List<String>>()
    map["you"] = listOf("alice", "bob", "claire")
    map["claire"] = listOf("thom", "jonny")
    map["bob"] = listOf("anuj", "peggy")
    map["alice"] = listOf("peggy")
    map["peggy"] = listOf()
    map["thom"] = listOf()
    map["jonny"] = listOf()
//    breadthFirstSearchFindSeller(map)

    val graph = mapOf(
        "Start" to listOf("Car", "Cat"),
        "Car" to listOf("Cat", "Bar"),
        "Cat" to listOf("Mat", "Finish"),
        "Bar" to listOf("Finish"),
    )
    val graph2 = mapOf(
        "Start" to listOf("Car", "Cat"),
        "Car" to listOf("Cat", "Bar", "Finish"),
        "Cat" to listOf("Mat", ),
        "Bar" to listOf("Finish"),
        "Mat" to listOf("Finish")
    )
    println(
        breadthFirstSearchFindShortestWay(
            graph = graph2,
            start = "Start",
        )
    )
}

//Поиск в ширину
private fun breadthFirstSearchFindSeller(graph: Map<String, List<String>>, start: String): Boolean {
    val queue = LinkedList<String>()
    val checked = mutableListOf<String>()

    graph[start]?.let { strings ->
        strings.forEach { s -> queue.add(s) }
        while (queue.isNotEmpty()) {
            val next = queue.poll()
            if (next !in checked) {
                if (next.isSeller()) {
                    return true
                } else {
                    graph[next]?.let { stringList -> stringList.forEach { queue += it } }
                }
                checked += next
            }
        }
    }
    return false
}

private fun String.isSeller() = this.endsWith("m")

//кратчайший путь с помощью BFS
private fun breadthFirstSearchFindShortestWay(graph: Map<String, List<String>>, start: String): List<String> {
    val queue = LinkedList<String>()
    val checked = mutableListOf<String>()
    val parents = mutableMapOf<String, List<String>>()
    graph[start]?.let { strings ->
        strings.forEach {
            queue.add(it)
            parents[it] = listOf(start)
        }
        while (queue.isNotEmpty()) {
            val next = queue.poll()
            if (next !in checked) {
                if (next.isFinish()) {
                    return getPath(end = next, parents = parents)
                } else {
                    graph[next]?.forEach {
                        val stringList = parents[it]
                        if (stringList != null) {
                            parents[it] = stringList.plus(next)
                        } else {
                            parents[it] = listOf(next)
                        }
                        queue += it
                    }
                }
                checked += next
            }
        }
    }
    return emptyList()
}

private fun String.isFinish() = this == "Finish"

private fun <T> getPath(end: T, parents: Map<T, List<T>>): List<T> {
    val result = mutableListOf<T>()
    result.add(end)
    var next = parents[end]?.first()
    while (next != null) {
        result += next
        next = parents[next]?.first()
    }
    return result.reversed().toList()
}
