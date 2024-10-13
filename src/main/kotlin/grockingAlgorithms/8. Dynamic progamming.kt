package grockingAlgorithms

import kotlin.math.max

fun main() {
    var job: Job? = Job(mutableListOf())
    var person: Person? = Person(job!!)
    job?.apply { employees.add(person!!) }

    job = null
    person = null
}

fun optimalStrategy(costs: List<Pair<String, Float>>, bagCapacity: Int, unit: Float = 1.0f): Float {
    val columns = mutableListOf<Int>()
    repeat((bagCapacity / unit).toInt()) {
        columns += it + 1
    }

    val result = MutableList(costs.size) { MutableList(columns.size) { -1f } }

    costs.onEachIndexed { i, entry ->
        for ((j, row) in columns.withIndex()) {
            result[i][j] = max(
                entry.second,
                (costs[i].second + result.getOrNull(i - 1)?.getOrNull(j - entry.second.toInt())!!)
            )
        }
    }

    return result[costs.lastIndex][columns.lastIndex]
}

private class Person(val job: Job) {
    fun  finalize() = println("Kill ${this::class.simpleName}: ${this.hashCode()}")
}

private class Job(val employees: MutableList<Person>) {
    fun  finalize() = println("Kill ${this::class.simpleName}: ${this.hashCode()}")
}