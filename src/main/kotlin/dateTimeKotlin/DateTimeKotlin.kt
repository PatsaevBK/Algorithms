package dateTimeKotlin

import kotlinx.datetime.*
import kotlin.time.Clock

private fun main() {
    val localDate = Clock.System.todayIn(TimeZone.currentSystemDefault()).minus(1, DateTimeUnit.DAY)
    println(localDate.dayOfWeek.ordinal)
}