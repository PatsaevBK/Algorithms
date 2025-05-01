package justTestKotlin

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import kotlinx.datetime.*

class CustomComparator<T>(private val firstElement: T) : Comparator<T> {
    override fun compare(o1: T, o2: T): Int {
        return when {
            o1 == firstElement -> -1
            o2 == firstElement -> 1
            else -> (o1 as Comparable<T>).compareTo(o2)
        }
    }
}

private fun main(): Unit = runBlocking {
    val a = launch {
        return@launch
    }
    delay(1000)
    println(a.isActive)

//    println(0 % 4)
//    println("A BCD".contains("ab", ignoreCase = true))
//    println("BC" in "A_BCD")
//    val instant = Instant.fromEpochSeconds(Clock.System.now().epochSeconds)
//    val localDateTime = instant.toLocalDateTime(TimeZone.currentSystemDefault())
//    LocalDateTime.Format {
//
//    }
//    println(instant)
//    val stateFlow = MutableStateFlow(0)
//    val resultFlow = MutableSharedFlow<String>()
//
//    println(3 / 5)
//
//    // consumer
//    launch(Dispatchers.IO) {
//        stateFlow.collect {
//            println("StartProccess $it")
//            delay(1000)
//            resultFlow.emit(it.toString())
//        }
//    }
//
//    // emitter
//    launch(Dispatchers.IO) {
//        repeat(100) {
//            delay(500)
//            stateFlow.emit(it)
//        }
//    }
//
//    // result
//    launch {
//        resultFlow.collect {
//            println("----- XXX result $it")
//        }
//    }
}

//    val small = listOf(5, 6, 7, 8)
//    val large = List(10) { it }
//    println(large.subtract(small.toSet()))
//    val list = listOf(5, 2, 8, 3, 7, 1)
//    val specialElement = 3
//    val sortedList = list.sortedWith(CustomComparator(specialElement))
//    println(sortedList)
//    val b = listOf(1,2, 3)
//    val a = mutableMapOf<Int, Int>().apply { b.forEach { put(it, it)} }.also { println(it) }
//    val a = MutableStateFlow<Int>(1)
//    launch {
//        a.collect {
//            println(it)
//        }
//    }
//    delay(1000)
//    a.value = throw Exception("MSG")
//    val b = launch { a.collect { println("XXX") } }
//    println(b.isActive)
//    println(b.isCompleted)


//fun main() = runBlocking {
//    val a = MutableSharedFlow<Int>()
//
//    launch {
//        a.collect { println(it) }
//        println("AAA")
//    }
////    A()
////    val d = flow<Boolean> {
////        println("Flow")
//////        CoroutineScope(Dispatchers.Default).launch {
////            emit(false)
////            println("XXX")
////        throw CancellationException("XXX")
//////        }
////    }
////    d.collect {
////        println(it)
////    }
//// Parent job
////    val a= CoroutineScope(Dispatchers.Main).launch(CoroutineExceptionHandler { CoroutineContext, Throwable →
////
////    }) {
////        throw RuntimeException("Exception in child coroutine")
////    }
////    a.join()
////    println("X")
////    log("main")
////    flow {
////        log("flow")
////        emit("T")
////    }.flowOn(Dispatchers.IO.limitedParallelism(1)).collect { println(it) }
//
////    val a = MutableStateFlow(false)
//    val b = MutableStateFlow(false)
//
//    launch {
//        delay(1000)
//        println("emit a")
//        a.emit(true)
//
//        delay(1000)
//        println("emit b")
//        b.emit(true)
//    }
//
//    val c = MutableSharedFlow<String>()
//    val flows = listOf(a.onEach { println("onEach a $it") }, b.onEach { println("onEach b $it") })
//
//    launch {
//        flows.merge().collect {
//            println(it)
//            c.emit(it.toString())
//            return@collect
//        }
//    }
//
//    launch {
//        combine(flows) {
//
//        }.collect {
//            println(it)
//            return@collect
//        }
//    }
//
//    yield()
////    val a = MutableStateFlow(1)
////    a.update {  }
//}



class A {
    val sharedFlow:StateFlow<Int> = MutableStateFlow<Int>(calcFirstState())

    private fun calcFirstState(): Int {
        println(sharedFlow.value)
        return 1
    }
}

private fun log(text: String) {
    println("TAG $text [${Thread.currentThread().name}]")
}

val hotFlow = MutableStateFlow(1)
val hotFlow1 = MutableStateFlow(2)
val coldFlow = flow<Int> {
    CoroutineScope(Dispatchers.Default).launch {
        hotFlow.collect {
            emit(it)
        }
    }

    CoroutineScope(Dispatchers.Default).launch {
        hotFlow1.collect {
            emit(it)
        }
    }
}

suspend fun test() {
    coldFlow.collect { it ->
        println("XXX $it")
    }
    println("YYY")
}
