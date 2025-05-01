package coroutines.flow

import kotlinx.coroutines.*
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow

private fun main(): Unit = runBlocking {
    val channel = Channel<Int>(capacity = Channel.BUFFERED)
    val flow = channel.receiveAsFlow()

// Запуск отправки данных в канал
    CoroutineScope(Dispatchers.IO).launch {
        repeat(10) {
            println("Channel sends $it")
            channel.send(it)
        }
        channel.close()
    }

// Два коллектора собирают поток одновременно
    val job = CoroutineScope(Dispatchers.IO).launch {
        flow.collect { value ->
            println("Collector 1 получил: $value")
        }
    }
    val job2 = CoroutineScope(Dispatchers.IO).launch {
        flow.collect { value ->
            println("Collector 2 получил: $value")
        }
    }
    job.join()
    job2.join()
}