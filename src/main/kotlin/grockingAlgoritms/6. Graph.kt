package grockingAlgoritms

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
    checkGraph(map)
}

//Поиск в ширину
private fun checkGraph(map: MutableMap<String, List<String>>): Boolean {
    val queue = LinkedList<String>()
    val checked = mutableListOf<String>()
    map["you"]?.let { strings ->
        strings.forEach { s -> queue.add(s) }
        while (queue.isNotEmpty()) {
            val next = queue.poll()
            if (next !in checked) {
                println("$next is checked")
                if (next.isSeller()) {
                    println("$next is seller!")
                    return true
                } else {
                    map[next]?.let { stringList -> stringList.forEach { queue += it } }
                }
                checked += next
            }
        }
    }
    return false
}

private fun String.isSeller() = this.endsWith("m")
