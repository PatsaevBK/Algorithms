package leetcode

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class LengthOfLongestSubstringTest {
    @Test
    fun test1() {
        assertEquals(3, LengthOfLongestSubstring().lengthOfLongestSubstring("abcabcbb"))
    }

    @Test
    fun test2() {
        assertEquals(1, LengthOfLongestSubstring().lengthOfLongestSubstring("bbbbb"))
    }

    @Test
    fun test3() {
        assertEquals(3, LengthOfLongestSubstring().lengthOfLongestSubstring("pwwkew"))
    }

    @Test
    fun test4() {
        assertEquals(1, LengthOfLongestSubstring().lengthOfLongestSubstring(" "))
    }

    @Test
    fun test5() {
        assertEquals(1, LengthOfLongestSubstring().lengthOfLongestSubstring("c"))
    }

    @Test
    fun test6() {
        assertEquals(3, LengthOfLongestSubstring().lengthOfLongestSubstring("dvdf"))
    }

    @Test
    fun test7() {
        assertEquals(3, LengthOfLongestSubstring().lengthOfLongestSubstring("aabaab!bb"))
    }
}