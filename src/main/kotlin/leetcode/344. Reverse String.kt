package leetcode

fun main() {
    println(reverseString("ABCDEFG".toCharArray()))
}

private fun reverseString(s: CharArray): CharArray {
    var startPoint = 0
    var endPoint = s.lastIndex

    while (startPoint < endPoint) {
        val temp = s[startPoint]
        s[startPoint] = s[endPoint]
        s[endPoint] = temp
        startPoint++
        endPoint--
    }
    return s
}

/*
* 344. Reverse String
Solved
Easy

Write a function that reverses a string. The input string is given as an array of characters s.

You must do this by modifying the input array in-place with O(1) extra memory.



Example 1:

Input: s = ["h","e","l","l","o"]
Output: ["o","l","l","e","h"]
Example 2:

Input: s = ["H","a","n","n","a","h"]
Output: ["h","a","n","n","a","H"]
* */