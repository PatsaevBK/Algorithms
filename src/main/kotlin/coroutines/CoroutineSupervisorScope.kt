package coroutines

import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.supervisorScope

private fun main(): Unit = runBlocking(SupervisorJob()) {
    // https://startandroid.ru/ru/courses/kotlin/29-course/kotlin/622-urok-27-korutiny-praktika-parallelnye-vyzovy.html
    val coroutineScope = launch {
        runCatching {
            //runCatching обязателен вложенный на await не поможет
            // coroutineScope - это suspend функция, которая под капотом добавляет промежуточную корутину между родительской
            // и дочерней корутиной. Когда в дочерней корутине возникает ошибка, промежуточная корутина принимает эту
            // ошибку от дочерней, но не передает ее дальше родительской. Вместо этого она выбрасывает ее сразу в код,
            // в месте где была вызвана coroutineScope. В итоге нам достаточно просто обернуть вызов coroutineScope в try-catch.

            //Хоть ошибка и не выйдет за пределы coroutineScope и не отменит launch, но внутри самого coroutineScope все
            // корутины будут отменены. Т.е. при ошибке в одном async, второй async тоже отменится.
            //
            //Я в этом примере не обернул await в try-catch. Но не забывайте, что await выбросят исключения
            // в случае ошибок в async или отмены async.
            coroutineScope {
                val a = async { "a" }
                val b = async {
                    throw Exception()
                    "b"
                }
                // вообще бесмысленно т.к. сразу ошибка отлетает в место вызова coroutineScope
//                runCatching {
                    a.await().also {
                        println(it)
                    }
                    b.await().also {
                        println(it)
                    }
//                }
            }
        }.also { println(it.exceptionOrNull()) }
    }

    val supervisorScope = launch {
        // в случае отлова ошибки в runCatching на await, то внешний try не нужен, если убрать try на await то нужен отлов ошибки
        // также ошибка в b корутине не остановит a
        //
        supervisorScope {
            val a = async { "a" }
            val b = async {
                throw Exception()
                "b"
            }
            runCatching {
                a.await().also {
                    println(it)
                }
                b.await().also {
                    println(it)
                }
            }
        }
    }
}