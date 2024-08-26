package leetcode

import kotlin.math.max

//https://github.com/akhilkammila/leetcode-screenshotter/blob/main/editorial-screenshots/1-999/003.%20Longest%20Substring%20Without%20Repeating%20Characters.png

class LengthOfLongestSubstring {
    fun lengthOfLongestSubstring(s: String): Int {
        return slidingWindow(s)
    }

    private fun myApproach(s: String): Int {
        if (s.isEmpty()) {
            return 0
        } else if (s.isBlank()) {
            return 1
        }
        val set = mutableSetOf<Char>()
        var result = 0
        var currentMax = 0
        for (char in s) {
            if (set.add(char)) {
                currentMax++
            } else {
                result = max(result, currentMax)
                val indexOfTwin = set.indexOf(char)
                val list = set.toList()
                val savePart = if (indexOfTwin != list.lastIndex) {
                    list.subList(indexOfTwin + 1, list.size)
                } else {
                    emptyList()
                }
                set.clear()
                set.addAll(savePart)
                set.add(char)
                currentMax = set.size
            }
        }
        result = max(result, currentMax)
        return result
    }

    private fun bruteForce(s: String): Int {
        var result = 0

        fun checkRepetition(s: String, start: Int, end: Int): Boolean {
            val set = mutableSetOf<Char>()

            for (index in start..end) {
                if (!set.add(s[index])) {
                    return false
                }
            }
            return true
        }

        for (i in s.indices) {
            for (j in i..s.lastIndex) {
                if (checkRepetition(s, start = i, end = j)) {
                    result = max(result, j - i + 1)
                }
            }
        }
        return result
    }

    private fun slidingWindow(s: String): Int {
        val charSet = mutableSetOf<Char>()

        var left = 0
        var maxLength = 0

        for (right in 0..s.lastIndex) {
            val r = s[right]
            if (r !in charSet) {
                charSet += r
                maxLength = max(maxLength, right - left + 1)
            } else {
                while (r in charSet) {
                    charSet -= s[left]
                    left++
                }
                charSet += s[right]
            }
        }
        return maxLength
    }
}
