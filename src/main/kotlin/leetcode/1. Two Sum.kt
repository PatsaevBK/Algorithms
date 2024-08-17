package leetcode

/*
* https://leetcode.com/problems/two-sum/description/
* https://github.com/akhilkammila/leetcode-screenshotter/blob/main/editorial-screenshots/1-999/001.%20Two%20Sum.png
* */

fun main() {

}

fun twoSumBruteForce(nums: IntArray, target: Int): IntArray {
    for ((index, num) in nums.withIndex()) {
        for (restIndex in index + 1..nums.lastIndex) {
            if (num + nums[restIndex] == target) {
                return intArrayOf(index, restIndex)
            }
        }
    }
    return intArrayOf()
}

fun twoSumHashTableTwice(nums: IntArray, target: Int): IntArray {
    val hashMap = mutableMapOf<Int, Int>()
    for ((index, num) in nums.withIndex()) {
        hashMap += num to index
    }
    for (num in nums.withIndex()) {
        val complement = target - num.value
        if (hashMap.contains(complement) && num.index != hashMap[complement]) {
            return intArrayOf(num.index, hashMap.getValue(complement))
        }
    }
    return intArrayOf()
}

fun twoSumHashTableOnce(nums: IntArray, target: Int): IntArray {
    val hashMap = mutableMapOf<Int, Int>()
    for ((index, num) in nums.withIndex()) {
        val complement = target - num
        if (hashMap.contains(complement)) return intArrayOf(index, hashMap.getValue(complement))
        hashMap += num to index
    }
    return intArrayOf()
}