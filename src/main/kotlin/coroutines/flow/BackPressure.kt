package coroutines.flow

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

private fun main() = runBlocking {
    quickSender(this)
    quickSubscriber(this)
    slowSubscriber(this)
}

private val mutableSharedFlow = MutableSharedFlow<Int>(extraBufferCapacity = 1)

private fun quickSender(scope: CoroutineScope) {
    repeat(10) { number ->
        scope.launch {
            delay(1000)
            mutableSharedFlow.emit(number)
        }
    }
}

private fun slowSubscriber(scope: CoroutineScope) {
    scope.launch {
        mutableSharedFlow.collect {
            delay(3000)
            println("slowSubscriber get $it")
        }
    }
}

private fun quickSubscriber(scope: CoroutineScope) {
    scope.launch {
        mutableSharedFlow.collect {
            println("quickSubscriber get $it")
        }
    }
}