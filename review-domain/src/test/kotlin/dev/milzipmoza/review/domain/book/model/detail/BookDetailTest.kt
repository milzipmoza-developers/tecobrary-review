package dev.milzipmoza.review.domain.book.model.detail

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.time.LocalDate

internal class BookDetailTest {

    @Test
    fun `도서 상세를 수정할 때 내용이 같으면 수정이 불가능하다`() {
        val sameImagePath = "/img/path.png"
        val sameTitle = "제주도 책"
        val samePublisher = "민슬기"
        val sameAuthor = ""
        val sameLocale = BookLanguage.KOREAN
        val samePublishDate = LocalDate.now()
        val sameDescription = ""

        val it = BookDetail(
                imagePath = sameImagePath,
                title = sameTitle,
                publisher = samePublisher,
                author = sameAuthor,
                locale = sameLocale,
                publishDate = samePublishDate,
                description = sameDescription
        )

        val other = BookDetail(
                imagePath = sameImagePath,
                title = sameTitle,
                publisher = samePublisher,
                author = sameAuthor,
                locale = sameLocale,
                publishDate = samePublishDate,
                description = sameDescription
        )

        assertThrows<BookDetailOperationException> {
            it.edit(other)
        }
    }

    @Test
    fun `도서 상세를 수정할 때 내용이 다르면 수정이 가능하다`() {
        val sameImagePath = "/img/path.png"
        val sameTitle = "제주도 책"
        val sameAuthor = ""
        val sameLocale = BookLanguage.KOREAN
        val samePublishDate = LocalDate.now()
        val sameDescription = ""

        val it = BookDetail(
                imagePath = sameImagePath,
                title = sameTitle,
                publisher = "",
                author = sameAuthor,
                locale = sameLocale,
                publishDate = samePublishDate,
                description = sameDescription
        )

        val other = BookDetail(
                imagePath = sameImagePath,
                title = sameTitle,
                publisher = "귤귤",
                author = sameAuthor,
                locale = sameLocale,
                publishDate = samePublishDate,
                description = sameDescription
        )

        val editedBook = it.edit(other)

        assertNotEquals(it.publisher, editedBook.publisher)
        assertEquals(other.publisher, editedBook.publisher)
    }
}
