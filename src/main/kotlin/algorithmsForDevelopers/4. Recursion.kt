package algorithmsForDevelopers

fun main() {
//    allPossibleCombinationAZ(3, "")
//    println(result)
//    println(result.size)
    towerOfHanoi(2, 'A', 'C', 'B')
}

private val result = mutableListOf<String>()

private fun allPossibleCombinationAZ(length: Int, string: String) {
    if (string.length == length) {
        result.add(string)
        return
    } else {
        for (char in 'a'..'z') {
            val str = string + char
            allPossibleCombinationAZ(length, str)
        }
    }
}

// a
  // aa
    // aaa
        // aab


private fun allPossibleCombinationOnlyUniqueAZ(length: Int, string: String) {
    if (string.length == length) {
        result.add(string)
        return
    } else {
        for (char in 'a'..'z') {
            if (char in string) continue
            val str = string + char
            allPossibleCombinationAZ(length, str)
        }
    }
}

private fun towerOfHanoi(countCircle: Int, from: Char, to: Char, aux: Char) {
    if (countCircle == 1) {
        println("Move from $from to $to")
        return
    }
    towerOfHanoi(countCircle = countCircle - 1, from = from, to = aux, aux = to)
    println("Move from $from to $to")
    towerOfHanoi(countCircle = countCircle - 1, from = aux, to = to, aux = from)
}