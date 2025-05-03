package leetcode

private fun main() {
    val nums = intArrayOf(-1, 0, 1, 2, -1, -4)
    println(threeSum(nums))

    println(threeSum(intArrayOf(-2,0,1,1,2)))
    //expected [[-2,0,2],[-2,1,1]]
}

private fun threeSum(nums: IntArray): List<List<Int>> {
    val result = mutableSetOf<List<Int>>()
    val sortedNums = nums.sorted().toMutableList()
    for (i in sortedNums.indices) {
        val target = -sortedNums[i]
        var left = i + 1
        var right = sortedNums.lastIndex

        while (left < right) {
            val currentSum = sortedNums[left] + sortedNums[right]
            if (currentSum == target) {
                result.add(listOf(sortedNums[i], sortedNums[left], sortedNums[right]))
            }
            if (currentSum > target) {
                right--
            } else {
                left++
            }
        }
    }
    return result.toList()
}

/*
* 15. 3Sum
Solved
Medium
* Given an integer array nums, return all the triplets
* [nums[i], nums[j], nums[k]] such that i != j, i != k, and j != k,
* and nums[i] + nums[j] + nums[k] == 0.

Notice that the solution set must not contain duplicate triplets.



Example 1:

Input: nums = [-1,0,1,2,-1,-4]
Output: [[-1,-1,2],[-1,0,1]]
Explanation:
nums[0] + nums[1] + nums[2] = (-1) + 0 + 1 = 0.
nums[1] + nums[2] + nums[4] = 0 + 1 + (-1) = 0.
nums[0] + nums[3] + nums[4] = (-1) + 2 + (-1) = 0.
The distinct triplets are [-1,0,1] and [-1,-1,2].
Notice that the order of the output and the order of the triplets does not matter.
Example 2:

Input: nums = [0,1,1]
Output: []
Explanation: The only possible triplet does not sum up to 0.
Example 3:

Input: nums = [0,0,0]
Output: [[0,0,0]]
Explanation: The only possible triplet sums up to 0.


Constraints:

3 <= nums.length <= 3000
-105 <= nums[i] <= 105
* */