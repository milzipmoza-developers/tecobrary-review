package dev.milzipmoza.domain.book.model.mark

import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

internal class BookMarksTest {

    @Test
    fun `이미 추가된 북마크를 추가하면 예외가 발생한다`() {
        val bookMarks = BookMarks(mutableListOf(BookMark(BookMarkType.LIKE)))

        assertThrows<BookMarkOperationException> {
            bookMarks.mark(BookMark(BookMarkType.LIKE))
        }
    }

    @Test
    fun `추가되지 않은 북마크를 추가하면 성공적으로 추가되고 true 를 반환한다`() {
        val bookMarks = BookMarks()

        val result = bookMarks.mark(BookMark(BookMarkType.LIKE))

        assertTrue(result)
    }

    @Test
    fun `비어있을 때 해제하면 예외가 발생한다`() {
        val bookMarks = BookMarks()

        assertThrows<BookMarkOperationException> {
            bookMarks.unmark(BookMark(BookMarkType.LIKE))
        }
    }

    @Test
    fun `존재하지 않는 북마크를 해제하면 예외가 발생한다`() {
        val bookMarks = BookMarks(mutableListOf(BookMark(BookMarkType.LIKE)))

        assertThrows<BookMarkOperationException> {
            bookMarks.unmark(BookMark(BookMarkType.FAVORITE))
        }
    }

    @Test
    fun `존재하는 북마크를 해제하면 성공적으로 해제되고 true 를 반환한다`() {
        val bookMarks = BookMarks(mutableListOf(BookMark(BookMarkType.LIKE)))

        val result = bookMarks.unmark(BookMark(BookMarkType.LIKE))

        assertTrue(result)
    }
}