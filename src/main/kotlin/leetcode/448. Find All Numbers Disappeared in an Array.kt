package leetcode

import kotlin.math.abs

fun main() {
    val nums = intArrayOf(4, 3, 2, 7, 8, 2, 3, 1)
    assert(listOf(5, 6) == findDisappearedNumbers(nums))
}

/*448. Find All Numbers Disappeared in an Array
Solved
Easy
Topics
Companies
Hint
Given an array nums of n integers where nums[i] is in the range [1, n], return an array of all the integers in the range [1, n] that do not appear in nums.



Example 1:

Input: nums = [4,3,2,7,8,2,3,1]
Output: [5,6]
Example 2:

Input: nums = [1,1]
Output: [2]


Constraints:

n == nums.length
1 <= n <= 105
1 <= nums[i] <= n


Follow up: Could you do it without extra space and in O(n) runtime? You may assume the returned list does not count as extra space.
* */

private fun findDisappearedNumbers(nums: IntArray): List<Int> {
    for (num in nums) {
        val index = abs(num) - 1
        nums[index] = -1 * abs(nums[index])
    }
    val result = mutableListOf<Int>()
    for ((index, num) in nums.withIndex()) {
        if (num > 0) {
            result.add(index + 1)
        }
    }
    return result
}