package dev.milzipmoza.review.domain.mark

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class MarkTypeTest {

    @Test
    fun valueOfIgnoreCases() {
        val valueOfIgnoreCases = MarkType.valueOfIgnoreCases("Like")

        assertEquals(valueOfIgnoreCases, MarkType.LIKE)
    }
}