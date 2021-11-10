package dev.milzipmoza.domain.book.category

import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class BookCategoriesTest {

    @Test
    internal fun `도서 카테고리에 중복되지 않은 카테고리를 추가하면 성공적으로 추가된다`() {
        val bookCategories = BookCategories(
                mutableListOf(
                        BookCategory("black", "리액트"),
                        BookCategory("blue", "스프링부트")
                )
        )

        val result = bookCategories.add(BookCategory("black", "스프링부트"))
        assertTrue(result)
    }

    @Test
    internal fun `도서 카테고리에 중복된 카테고리를 추가하면 예외가 발생한다`() {
        val bookCategories = BookCategories(
                mutableListOf(
                        BookCategory("black", "리액트"),
                        BookCategory("blue", "스프링부트")
                )
        )

        assertThrows<BookCategoryOperationException> {
            bookCategories.add(BookCategory("black", "리액트"))
        }
    }
}