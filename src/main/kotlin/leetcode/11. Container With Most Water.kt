package leetcode

import kotlin.math.min

// https://leetcode.com/problems/container-with-most-water/description/

internal class ContainterWithMostWater {
    operator fun invoke(heights: IntArray): Int = trickyDynamic(heights)

    private fun bruteForce(heights: IntArray): Int {
        var first = 0
        var second = first + 1
        var result = 0
        while (first <= heights.lastIndex) {
            while (second <= heights.lastIndex) {
                val maxHeight = min(heights[second], heights[first])
                val width = second - first
                val newResult = maxHeight * width
                if (newResult > result) {
                    result = newResult
                }
                second++
            }
            first++
            second = first + 1
        }
        return result
    }

    private fun trickyDynamic(heights: IntArray): Int = calculateMax(
        maxResult = 0,
        heights = heights,
        startIndex = 0,
        endIndex = heights.lastIndex,
    )

    private fun calculateMax(maxResult: Int, heights: IntArray, startIndex: Int, endIndex: Int): Int {
        if (endIndex <= startIndex) {
            return maxResult
        }

        val startHeight = heights[startIndex]
        val endHeight = heights[endIndex]
        val maxHeight = min(startHeight, endHeight)
        val width = endIndex - startIndex
        val interimResult = maxHeight * width

        // двигаем индекс той высоты, что меньшая, надеясь увеличить результат
        // причем если равны то двигаем левый (просто условился)
        val newStartIndex = if (startHeight < endHeight) startIndex + 1 else startIndex
        val newEndIndex = if (endHeight <= startHeight) endIndex - 1 else endIndex
        val newMaxResult = if (interimResult > maxResult) interimResult else maxResult

        return calculateMax(
            maxResult = newMaxResult,
            heights = heights,
            startIndex = newStartIndex,
            endIndex = newEndIndex,
        )
    }
}