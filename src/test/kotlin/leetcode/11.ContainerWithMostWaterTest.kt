package leetcode

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class ContainerWithMostWaterTest {
    private lateinit var sut: ContainterWithMostWater

    @BeforeEach
    fun setUp() {
        sut = ContainterWithMostWater()
    }

    @Test
    fun formatRomanToInteger() {
        assertEquals(49, sut.invoke(intArrayOf(1,8,6,2,5,4,8,3,7)))
        assertEquals(1, sut.invoke(intArrayOf(1,1)))
    }
}