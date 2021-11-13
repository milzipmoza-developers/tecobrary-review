package dev.milzipmoza.domain.book.detail

import dev.milzipmoza.domain.Value
import java.time.LocalDate

class BookDetail(
        val imagePath: String,
        val title: String,
        val publisher: String,
        val author: String,
        val locale: BookLanguage,
        val publishDate: LocalDate,
        val description: String
) : Value<BookDetail> {

    internal fun edit(bookDetail: BookDetail): BookDetail {
        if (this.sameAs(bookDetail)) {
            throw BookDetailOperationException("도서 상세 정보가 기존의 정보와 동일합니다.")
        }

        return bookDetail
    }

    override fun sameAs(other: Value<BookDetail>) = this == other

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as BookDetail

        if (imagePath != other.imagePath) return false
        if (title != other.title) return false
        if (publisher != other.publisher) return false
        if (author != other.author) return false
        if (locale != other.locale) return false
        if (publishDate != other.publishDate) return false
        if (description != other.description) return false

        return true
    }

    override fun hashCode(): Int {
        var result = imagePath.hashCode()
        result = 31 * result + title.hashCode()
        result = 31 * result + publisher.hashCode()
        result = 31 * result + author.hashCode()
        result = 31 * result + locale.hashCode()
        result = 31 * result + publishDate.hashCode()
        result = 31 * result + description.hashCode()
        return result
    }
}
