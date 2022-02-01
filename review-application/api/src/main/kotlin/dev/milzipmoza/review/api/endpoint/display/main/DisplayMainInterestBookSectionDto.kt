package dev.milzipmoza.review.api.endpoint.display.main

import com.fasterxml.jackson.annotation.JsonFormat
import dev.milzipmoza.review.domain.book.model.Book
import dev.milzipmoza.review.domain.mark.MarkType
import java.time.LocalDate

data class DisplayMainInterestBookSectionDto(
        @JsonFormat(pattern = "yyyy-MM-dd")
        val updateDate: LocalDate = LocalDate.now(),
        val type: DisplayMainInterestBookType,
        val books: List<DisplayMainInterestBookDto>
)

data class DisplayMainInterestBookDto(
        val isbn: String,
        val title: String,
        val author: String,
        val imageUrl: String,
        val tags: List<DisplayMainTagDto>,
        val counts: Int
) {
    companion object {
        fun of(book: Book, counts: Int) = DisplayMainInterestBookDto(
                isbn = book.isbn,
                title = book.detail.title,
                author = book.detail.author,
                imageUrl = book.detail.fullImageUrl,
                tags = book.tags.map { DisplayMainTagDto(it.name, it.colorCode) },
                counts = counts
        )
    }
}

data class DisplayMainTagDto(
        val name: String,
        val colorCode: String
)

enum class DisplayMainInterestBookType(
        val displayName: String
) {
    MANY_REVIEWS("리뷰"),
    MANY_LIKES("좋아요"),
    MANY_FAVORITES("즐겨찾기")
    ;

    companion object {
        fun valueOfIgnoreCases(value: String): DisplayMainInterestBookType {
            return values().find { it.name == value.uppercase() }
                    ?: throw IllegalArgumentException("분류를 확인해주세요.")
        }
    }
}
