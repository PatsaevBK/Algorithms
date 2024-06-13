package leetcode

import java.math.BigDecimal

private fun problemWithBinaryFloatingPointNumbers() {
    /**
     * Проблема в сложении плавающих чисел типа 0,1 заключается в том что
     * при переводе 0.1 в бинарный вид происходит зацикливание 0.0 0011 0011....
     * из-за этого возникает непредвиденное поведение как в примере ниже.
     * Способы решения:
     *  1) BigDecimal
     *  2) Кастовать и отбрасывать с помощью Int
     *
     * See [Medium Roman Elizarov](https://elizarov.medium.com/floating-point-for-decimals-fc2861898455)
     */
    var sum: BigDecimal = BigDecimal(0)
    val delta = 0.1.toBigDecimal()
    repeat(3) {
        sum += delta
    }
    println(sum)
//    println(sum == 0.3)
}