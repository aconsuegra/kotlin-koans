package iii_conventions

import util.TODO


class Invokable {
    var count:Int = 0

    fun increaseInvoke(): Invokable {
        count++
        return this
    }
}

fun todoTask31(): Nothing = TODO(
    """
        Task 31.
        Change class Invokable to count the number of invocations (round brackets).
        Uncomment the commented code - it should return 4.
    """,
    references = { invokable: Invokable -> })

fun task31(invokable: Invokable): Int {
    return invokable()()()().getNumberOfInvocations()
}

fun  Invokable.getNumberOfInvocations(): Int = count

operator fun  Invokable.invoke(): Invokable = increaseInvoke()
