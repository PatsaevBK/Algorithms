package justTestKotlin

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch

suspend fun main() {
    val job = CoroutineScope(Dispatchers.Default).launch {
        test()
    }
    delay(1000)
    job.join()
}

val hotFlow =  MutableStateFlow(1)
val hotFlow1 =  MutableStateFlow(2)
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
