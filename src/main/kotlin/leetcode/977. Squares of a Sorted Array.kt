package leetcode


// https://leetcode.com/problems/squares-of-a-sorted-array/description/

internal class SquaresOfASortedArray {

    operator fun invoke(
        nums: List<Int>,
    ): List<Int> {
        var startIndex = 0
        var endIndex = nums.lastIndex

        return buildList {
            while (startIndex <= endIndex) {
                val startSquared = nums[startIndex].squared()
                val endSquared = nums[endIndex].squared()
                if (startSquared > endSquared) {
                    // заполняем массив влево
                    add(0, startSquared)
                    startIndex++
                } else {
                    // заполняем массив влево
                    add(0, endSquared)
                    endIndex--
                }
            }
        }
    }

    private fun Int.squared(): Int = this * this
}