package quickStartKt

internal fun task(listUser: List<String>) {
    // напишите ваше решение здесь
    val mutableUsers = listUser.toMutableList()
    val inputLine = readlnOrNull()

    when (inputLine.toInput()) {
        Input.SHOW -> {
            printUsers(mutableUsers)
        }

        Input.ADD -> {
            val newUser = readlnOrNull()
            newUser?.let { mutableUsers += it }
            printUsers(mutableUsers)
        }

        Input.REMOVE -> {
            val userToRemove = readlnOrNull()
            userToRemove?.let { mutableUsers -= it }
            printUsers(mutableUsers)
        }

        Input.REMOVE_AT -> {
            val indexToRemove = readlnOrNull()
            indexToRemove?.toIntOrNull()?.let {
                if (it in 0..mutableUsers.lastIndex) {
                    mutableUsers.removeAt(it)
                    printUsers(mutableUsers)
                }
            }
        }

        Input.UNKNOWN -> println("Некорректное значение")
    }
}

private fun printUsers(mutableUsers: MutableList<String>) {
    mutableUsers.forEach { println(it) }
}

private enum class Input {
    SHOW, ADD, REMOVE, REMOVE_AT, UNKNOWN
}

private fun String?.toInput(): Input {
    return when (this) {
        Input.SHOW.toString() -> Input.SHOW
        Input.ADD.toString() -> Input.ADD
        Input.REMOVE.toString() -> Input.REMOVE
        Input.REMOVE_AT.toString() -> Input.REMOVE_AT
        else -> Input.UNKNOWN
    }
}