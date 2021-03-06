package dev.milzipmoza.review.api.endpoint.display.main

import dev.milzipmoza.review.annotation.ApplicationService
import dev.milzipmoza.review.domain.book.Books
import dev.milzipmoza.review.domain.mark.CountedMark
import dev.milzipmoza.review.domain.mark.MarkAggregation
import dev.milzipmoza.review.domain.mark.MarkType
import dev.milzipmoza.review.domain.review.CountedReview
import dev.milzipmoza.review.domain.review.ReviewAggregation
import dev.milzipmoza.review.domain.review.Reviews

@ApplicationService
class DisplayMainInterestBookService(
        private val markAggregation: MarkAggregation,
        private val reviewAggregation: ReviewAggregation,
        private val books: Books,
) {

    fun getByType(type: String): DisplayMainInterestBookSectionDto {
        val interestBookType = DisplayMainInterestBookType.valueOfIgnoreCases(type)

        return when(interestBookType) {
            DisplayMainInterestBookType.MANY_LIKES -> {
                val topLikes: List<CountedMark> = markAggregation.getTop(MarkType.LIKE, 3)

                val foundBooks = books.findAllIn(topLikes.map { it.book.no })

                DisplayMainInterestBookSectionDto(
                        type = DisplayMainInterestBookType.MANY_LIKES,
                        books = foundBooks.map { DisplayMainInterestBookDto.of(it, topLikes.find { like -> like.book.no == it.isbn }!!.count) }
                )
            }
            DisplayMainInterestBookType.MANY_FAVORITES -> {
                val topFavorites: List<CountedMark> = markAggregation.getTop(MarkType.FAVORITE, 3)

                val foundBooks = books.findAllIn(topFavorites.map { it.book.no })

                DisplayMainInterestBookSectionDto(
                        type = DisplayMainInterestBookType.MANY_FAVORITES,
                        books = foundBooks.map { DisplayMainInterestBookDto.of(it, topFavorites.find { like -> like.book.no == it.isbn }!!.count) }
                )
            }
            DisplayMainInterestBookType.MANY_REVIEWS -> {
                val topReviewBooks: List<CountedReview> = reviewAggregation.getTop(3)

                val foundBooks = books.findAllIn(topReviewBooks.map { it.bookIsbn })

                DisplayMainInterestBookSectionDto(
                        type = DisplayMainInterestBookType.MANY_REVIEWS,
                        books = foundBooks.map { DisplayMainInterestBookDto.of(it, topReviewBooks.find { review -> review.bookIsbn == it.isbn }!!.count) }
                )
            }
        }
    }
}
