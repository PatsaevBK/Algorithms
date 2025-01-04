package algorithmsForDevelopers

fun main() {
    val tree = Tree()
    tree.add(1)
    tree.add(0)
    tree.add(3)
    tree.add(4)
    tree.add(2)
    println(tree)
}

/* Не сбалансированное
*
* */
private class Tree {
    private var root: Node? = null

    fun add(number: Int) {
        if (root == null) {
            root = Node(number, null, null, null)
        } else {
            root?.let { add(it, number) }
        }
    }

    override fun toString(): String {
        return buildString {
            appendLine("(${root?.value})")
            root?.left?.let { append("(${it.value})") }
            append(" | ")
            root?.right?.let { append("(${it.value})") }
            append("\n")
        }
    }

    private fun add(parent: Node, value: Int) {
        if (value < parent.value) {
            if (parent.left == null) {
                parent.left = Node(value = value, parent = parent, left = null, right = null)
            } else {
                parent.left?.let { add(it, value) }
            }
        } else {
            if (parent.right == null) {
                parent.right = Node(value = value, parent = parent, left = null, right = null)
            } else {
                parent.right?.let { add(it, value) }
            }
        }
    }
}

private data class Node(
    val value: Int,
    var parent: Node?,
    var left: Node?,
    var right: Node?,
)