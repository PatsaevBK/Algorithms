package grockingAlgorithms

fun main() {
    val res = tailRecNodEvc(170, 2800)
    println(res)
}

/*
Утверждение 1:
Если a > b, то НОД(a, b) = НОД(a - b, b) (и наоборот)
Доказательство:
     Пусть:
           d = НОД(a, b)
           d1 = НОД(a - b, b)
     Если d = НОД(a, b) то:
           a / d, b / d -> (a - b) / d (их разность) без остатка
     тогда (1) d1 >= d (так как d1 НОД для a - b, b)
     Взглянем с другой стороны если d1 = НОД(a - b, b) то:
           (a - b) / d1, b / d1 -> a / d1 (их сумма) без остатка
     тогда (2) d >= d1 (так как d НОД для a, b)
     эти условия (1) и (2) возможны только если d == d1 что и требовалось доказать НОД(a, b) = НОД(a - b, b)

Утверждение 2:
На основании утверждения 1 можно вычитать из числа столько раз пока разница a - b > 0 т.е. до остатка r
Тоесть: НОД(a, b) = НОД(r, b) где r = a % b в диапазоне: in 0..(b - 1) откуда получается алгоритм Евклида

Алгоритм Евклида гласит:     "Если мы хотим найти НОД пары чисел то большое из чисел можно заменить
                                на остаток (r) от деления большого на меньшее и так далее до тех пор пока
                                остаток (r) != 0. В конце будет НОД(0, z) то z - НОД первоначальных чисел a, b"
* */

private tailrec fun tailRecNodEvc(a: Int, b: Int): Int {
    println("$a $b")
    if (a == 0) return b
    if (b == 0) return a
    return if (a > b) {
        tailRecNodEvc(a % b, b)
    } else {
        tailRecNodEvc(a, b % a)
    }
}