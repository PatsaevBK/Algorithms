package coroutines

import kotlinx.coroutines.Deferred
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlin.time.Clock

internal fun main() = runBlocking(SupervisorJob()) {
    val job = launch {
        val a: Deferred<String> = async {
            delay(500)
            //бросаем ошибку, отменяя корутину async и верхнеуровневый launch
            throw Exception("fake").also { println(Clock.System.now().epochSeconds) }
            "work"
        }

        // если написать так то код после ловли ошибки не выполнится:
        // потому что await распакует ошибку
//        val result = a.await().also { println("await") }

        // если написать так то код после ловли ошибки выполнится
        // т.к. мы тут перехватим fake exception
        val result2 = runCatching {
            a.await()
        }.getOrElse { println(it) }.also { println(Clock.System.now().epochSeconds) }

        println("нужная работа которая выполнится если обернуть await в try")
    }

    val infinity = launch {
        repeat(1000) {
            println(job.isActive)
            delay(100)
        }
    }
    infinity.join()
}