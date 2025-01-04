package dateTimeKotlin

import kotlinx.datetime.*

fun main() {
    val localDate = Clock.System.todayIn(TimeZone.currentSystemDefault()).minus(1, DateTimeUnit.DAY)
    println(localDate.dayOfWeek.ordinal)
}