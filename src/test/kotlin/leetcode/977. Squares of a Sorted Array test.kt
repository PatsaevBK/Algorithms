package leetcode

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class SquaresOfASortedArrayTest {
    private lateinit var sut: SquaresOfASortedArray

    @BeforeEach
    fun setUp() {
        sut = SquaresOfASortedArray()
    }

    @Test
    fun formatRomanToInteger() {
        assertEquals(listOf(0,1,9,16,100), sut.invoke(listOf(-4,-1,0,3,10)))
        assertEquals(listOf(4,9,9,49,121), sut.invoke(listOf(-7,-3,2,3,11)))
        assertEquals(listOf(1,4,9,25), sut.invoke(listOf(-5,-3,-2,-1)))
    }
}