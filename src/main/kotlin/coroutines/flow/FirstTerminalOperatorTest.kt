package coroutines.flow

import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.TimeoutCancellationException
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withTimeoutOrNull

internal fun main(): Unit = runBlocking {
//    emitAfterDelay()
//    checkFirstWaiting()
    a()
    launch {
        repeat(10) {
            delay(1000)
            sharedFlow.emit(1)
        }
    }
}

private val stateFlow = MutableStateFlow(0)
private val sharedFlow = MutableStateFlow(0)

private val handler = CoroutineExceptionHandler { ctx, thr ->
    println("catch $thr")
}

private fun CoroutineScope.emitAfterDelay() = launch {
    delay(5_000)
    stateFlow.update { it + 2 }.also { println("Update 2") }
}

private fun CoroutineScope.checkFirstWaiting() = launch(handler) {
    val result = withTimeoutOrNull(6000) {
        stateFlow.first { it == 1 }
        println("1 has come!")
    }
    if (result == null) {
        throw IllegalStateException("Движок не стал в готовность в течение 2 секунд после отправки команды")
    }
}

private suspend fun a() = coroutineScope {
    val succeedNavigation = async {
        println("XXX Async start")
        sharedFlow
            .first { event ->
                true
            }
    }

    println("XXX delay")
    delay(1000)

    succeedNavigation.await()
}
