package leetcode

/**
 * Example:
 * var li = ListNode(5)
 * var v = li.`val`
 * Definition for singly-linked list.
 * class ListNode(var `val`: Int) {
 *     var next: ListNode? = null
 * }
 * https://leetcode.com/problems/add-two-numbers/description/
 * https://leetcode.com/problems/add-two-numbers/solutions/3111480/kotlin-solution/
 */

fun main() {
    val list1 = intArrayOf(9, 9, 9, 9, 9, 9, 9)
    val list2 = intArrayOf(9, 9, 9, 9)
    val list1n = list1.map {
        ListNode(it)
    }
    val list2n = list2.map {
        ListNode(it)
    }
    for ((index, nod) in list1n.withIndex()) {
        if (index != list1n.lastIndex) {
            nod.next = list1n[index + 1]
        }
    }
    list2n.forEachIndexed { index, listNode ->
        if (index != list2n.lastIndex) {
            listNode.next = list2n[index + 1]
        }
    }
    val nod = addTwoNumbersSolution(list1n.first(), list2n.first())
    println(printNods(nod))
}

private class ListNode(var `val`: Int) {
    var next: ListNode? = null
}

private fun addTwoNumbers(l1: ListNode?, l2: ListNode?): ListNode? {
    if (l1?.next == null && l2?.next == null) {
        val number1 = l1?.`val` ?: 0
        val number2 = l2?.`val` ?: 0
        val sum = number1 + number2
        return if (sum <= 9) {
            ListNode(sum)
        } else {
            val result = ListNode(sum % 10)
            result.next = ListNode(1)
            result
        }
    } else {
        val number1 = l1?.`val` ?: 0
        val number2 = l2?.`val` ?: 0
        val sum = number1 + number2
        return if (sum <= 9) {
            val result = ListNode(sum)
            result.next = addTwoNumbers(l1?.next, l2?.next)
            result
        } else {
            val result = ListNode(sum % 10)
            if (l1?.next != null) {
                l1.next?.`val` = l1.next?.`val`?.inc() ?: 0
            } else if (l2?.next != null) {
                l2.next?.`val` = l2.next?.`val`?.inc() ?: 0
            }
            result.next = addTwoNumbers(l1?.next, l2?.next)
            result
        }
    }
}

private fun printNods(node: ListNode?): List<Int> {
    return if (node == null) {
        return emptyList()
    } else {
        listOf(node.`val`) + printNods(node.next)
    }
}

private fun addTwoNumbersSolution(l1: ListNode?, l2: ListNode?): ListNode? {
    var node1 = l1; var node2 = l2
    val dummyHead = ListNode(0)
    var current = dummyHead
    var carry = 0
    while (node1 != null || node2 != null || carry != 0) {
        var sum = 0

        node1?.let {
            sum += it.`val`
            node1 = it.next
        }

        node2?.let {
            sum += it.`val`
            node2 = it.next
        }

        sum += carry
        current.next = ListNode(sum % 10)
        carry = sum / 10

        current.next?.let {
            current = it
        }
    }
    return dummyHead.next
}