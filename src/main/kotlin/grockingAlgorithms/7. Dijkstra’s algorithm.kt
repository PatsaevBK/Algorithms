package grockingAlgorithms

fun main() {
    val start = "Start"
    val finish = "Finish"
    val graph = mapOf(
        start to mapOf("Car" to 6, "Cat" to 4),
        "Car" to mapOf("Cat" to 7, "Bar" to 8),
        "Cat" to mapOf("Mat" to 10, finish to 5),
        "Bar" to mapOf(finish to 3),
        "Mat" to mapOf(),
    )
    println(findShortestWay(graph, start, finish))

    val graph2 = mapOf(
        start to mapOf("B" to 5, "D" to 2),
        "D" to mapOf("B" to 8, "E" to 7),
        "B" to mapOf("C" to 4, "E" to 2),
        "C" to mapOf(finish to 3, "E" to 6),
        "E" to mapOf(finish to 1),
    )
    println(findShortestWay(graph2, start, finish))

    val graph3 = mapOf(
        start to mapOf("B" to 10),
        "D" to mapOf("B" to 1),
        "B" to mapOf("C" to 20),
        "C" to mapOf(finish to 30, "D" to 1),
    )

    println(findShortestWay(graph3, start, finish))
}

// Алгоритм Дейкстры
private fun findShortestWay(graph: Map<String, Map<String, Int>>, from: String, to: String): String {
    val costsWayFromStart = mutableMapOf<String, Int>()
    costsWayFromStart[to] = Int.MAX_VALUE
    val parents = mutableMapOf<String, String>()
    val checked = mutableListOf<String>()

    graph[from]?.let { nodes ->
        nodes.forEach { (key, value) ->
            parents[key] = from
            costsWayFromStart[key] = value
        }
        checked += from
    }

    var next = findShortest(costsWayFromStart, checked)
    while (next != null) {
        val cost = costsWayFromStart[next]
        if (cost != null) {
            val neighbours = graph[next]
            if (neighbours != null) {
                for ((neighbour, neighbourCost) in neighbours) {
                    val newCost = cost + neighbourCost
                    val neighbourWay = costsWayFromStart[neighbour]
                    if (neighbourWay != null) {
                        if (newCost < neighbourWay) {
                            costsWayFromStart[neighbour] = newCost
                            parents[neighbour] = next
                        }
                    } else {
                        costsWayFromStart += neighbour to newCost
                        parents[neighbour] = next
                    }
                }
            }
        }
        checked += next
        next = findShortest(costsWayFromStart, checked)
    }

    return prepareResult(parents, to, costsWayFromStart)
}

private fun findShortest(ways: Map<String, Int>, checked: List<String>): String? {
    val notChecked = ways.filter { it.key !in checked }
    val minCost = notChecked.values.minOrNull()
    minCost?.let {
        notChecked.forEach { (key, value) ->
            if (value == minCost) {
                return key
            }
        }
    }
    return null
}

fun prepareResult(
    parents: Map<String, String>,
    to: String,
    costsWayFromStart: Map<String, Int>
): String {
    val way = mutableListOf<String>()
    way += to
    var next = parents[to]
    while (next != null) {
        way += next
        next = parents[next]
    }
    return buildString {
        append("Path = ${way.reversed()} ${costsWayFromStart.getValue(to)}")
    }
}
