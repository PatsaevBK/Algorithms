package leetcode

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class RomanToIntegerTest {
    private lateinit var sut: RomanToInteger

    @BeforeEach
    fun setUp() {
        sut = RomanToInteger()
    }

    @Test
    fun formatRomanToInteger() {
        assertEquals(sut.formatRomanToInteger("III"), 3)
        assertEquals(sut.formatRomanToInteger("LVIII"), 58)
        assertEquals(sut.formatRomanToInteger("MCMXCIV"), 1994)
    }
}