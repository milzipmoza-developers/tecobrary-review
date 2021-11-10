package dev.milzipmoza.domain.book.category

import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class BookCategoryTest {

    @Test
    internal fun `색깔과 이름이 같으면 같다`() {
        val it = BookCategory("black", "리액트")
        val other = BookCategory("black", "리액트")

        val sameAs = it.sameAs(other)
        assertTrue(sameAs)
    }

    @Test
    internal fun `색깔이 다르면 다르다`() {
        val it = BookCategory("yellow", "리액트")
        val other = BookCategory("black", "리액트")

        val sameAs = it.sameAs(other)
        assertFalse(sameAs)
    }

    @Test
    internal fun `이름이 다르면 다르다`() {
        val it = BookCategory("black", "스프링부트")
        val other = BookCategory("black", "리액트")

        val sameAs = it.sameAs(other)
        assertFalse(sameAs)
    }
}