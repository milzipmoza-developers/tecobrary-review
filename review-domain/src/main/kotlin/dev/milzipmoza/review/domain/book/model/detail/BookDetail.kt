package dev.milzipmoza.review.domain.book.model.detail

import dev.milzipmoza.review.domain.Value
import java.time.LocalDate

class BookDetail(
        val image: BookImageUrl,
        val title: String,
        val publisher: String,
        val author: String,
        val locale: BookLanguage = BookLanguage.KOREAN,
        val publishDate: LocalDate,
        val description: String
) : Value<BookDetail> {
    val fullImageUrl: String
        get() = image.toUrl()

    val summarizedTitle: String
        get() = if (title.contains("(")) {
            title.split("(")[0].trim()
        } else {
            title
        }

    val summarizedDescription: String
        get() = if (description.length > CONTENT_LENGTH) {
            description.removeRange(CONTENT_LENGTH, description.length) + LENGTH_EXCEED_SUFFIX
        } else {
            description
        }


    internal fun edit(bookDetail: BookDetail): BookDetail {
        if (this.sameAs(bookDetail)) {
            throw BookDetailOperationException("도서 상세 정보가 기존의 정보와 동일합니다.")
        }

        return edit(
                imageUrl = bookDetail.image.toUrl(),
                title = bookDetail.title,
                publisher = bookDetail.publisher,
                author = bookDetail.author,
                description = bookDetail.description
        )
    }

    private fun edit(imageUrl: String, title: String, publisher: String, author: String, description: String): BookDetail {
        return BookDetail(
                image = if (notEdited(image.toUrl(), imageUrl)) image else BookImageUrl(imageUrl),
                title = if (notEdited(this.title, title)) this.title else title,
                publisher = if (notEdited(this.publisher, publisher)) this.publisher else publisher,
                author = if (notEdited(this.author, author)) this.author else author,
                locale = this.locale,
                publishDate = this.publishDate,
                description = if (notEdited(this.description, description)) this.description else description
        )
    }

    private fun notEdited(original: String, edit: String) = original == edit || edit.isBlank()

    override fun sameAs(other: BookDetail) = this == other

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as BookDetail

        if (image != other.image) return false
        if (title != other.title) return false
        if (publisher != other.publisher) return false
        if (author != other.author) return false
        if (locale != other.locale) return false
        if (publishDate != other.publishDate) return false
        if (description != other.description) return false

        return true
    }

    override fun hashCode(): Int {
        var result = image.hashCode()
        result = 31 * result + title.hashCode()
        result = 31 * result + publisher.hashCode()
        result = 31 * result + author.hashCode()
        result = 31 * result + locale.hashCode()
        result = 31 * result + publishDate.hashCode()
        result = 31 * result + description.hashCode()
        return result
    }

    companion object {
        private const val CONTENT_LENGTH = 200
        private const val LENGTH_EXCEED_SUFFIX = "..."
    }
}
