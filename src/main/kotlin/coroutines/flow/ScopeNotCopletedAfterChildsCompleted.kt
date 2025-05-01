package coroutines.flow

import kotlinx.coroutines.*

private fun main() = runBlocking {
    val job = Job()
    val scope = CoroutineScope(Dispatchers.Default + job)

    scope.launch {
        delay(500L)
        println("Task 1 done!")
    }

    scope.launch {
        delay(1000L)
        println("Task 2 done!")
    }

    job.invokeOnCompletion {
        println("Job completed automatically after tasks finished!")
    }

    println("Waiting for tasks to complete...")
    job.join()
}