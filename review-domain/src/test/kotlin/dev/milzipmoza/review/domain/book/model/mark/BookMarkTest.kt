package dev.milzipmoza.review.domain.book.model.mark

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class BookMarkTest {

    @Test
    fun `타입이 같으면 같은 것으로 처리한다`() {
        val it = BookMark(BookMarkType.LIKE)
        val other = BookMark(BookMarkType.LIKE)

        assertTrue(it.sameAs(other))
    }

    @Test
    fun `타입이 다르면 다른 것으로 처리한다`() {
        val it = BookMark(BookMarkType.LIKE)
        val other = BookMark(BookMarkType.FAVORITE)

        assertFalse(it.sameAs(other))
    }
}