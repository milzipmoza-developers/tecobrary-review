package dev.milzipmoza.review.api.endpoint.display.book

import dev.milzipmoza.review.annotation.ApplicationService
import dev.milzipmoza.review.api.ClientMember
import dev.milzipmoza.review.domain.book.Books
import dev.milzipmoza.review.domain.book.model.category.BookCategory
import dev.milzipmoza.review.domain.mark.MarkBook
import dev.milzipmoza.review.domain.mark.MarkMember
import dev.milzipmoza.review.domain.mark.MarkType
import dev.milzipmoza.review.domain.mark.Marks

@ApplicationService
class DisplayBookDetailService(
        private val books: Books,
        private val marks: Marks
) {

    fun get(isbn: String, clientMember: ClientMember): DisplayBookDto {
        val book = books.findBy(isbn)

        val bookCategory = book.category

        return DisplayBookDto(
                book = DisplayBookDetailDto(
                        isbn = book.isbn,
                        imageUrl = book.detail.fullImageUrl,
                        title = book.detail.summarizedTitle,
                        publisher = book.detail.publisher,
                        author = book.detail.author,
                        locale = book.detail.locale.name,
                        publishDate = book.detail.publishDate,
                        description = book.detail.summarizedDescription
                ),
                mark = DisplayBookMarkDto(
                        like = DisplayBookLikeDto(
                                liked = when (clientMember) {
                                    is ClientMember.AuthenticatedMember -> marks.isMarked(MarkMember(clientMember.memberNo), MarkBook(book.isbn), MarkType.LIKE)
                                    else -> false
                                },
                                counts = marks.count(MarkBook(book.isbn), MarkType.LIKE)
                        ),
                        favorite = DisplayBookFavoriteMarkDto(
                                marked = when (clientMember) {
                                    is ClientMember.AuthenticatedMember -> marks.isMarked(MarkMember(clientMember.memberNo), MarkBook(book.isbn), MarkType.FAVORITE)
                                    else -> false
                                },
                                counts = marks.count(MarkBook(book.isbn), MarkType.FAVORITE)
                        )
                ),
                category = when (bookCategory) {
                    is BookCategory.EnrolledBookCategory -> {
                        DisplayBookCategoryDto(
                                no = bookCategory.no,
                                imageUrl = bookCategory.fullImageUrl
                        )
                    }
                    is BookCategory.NoBookCategory -> {
                        null
                    }
                },
                tags = book.tags.map { DisplayBookTagDto(it.name, it.colorCode) }
                        .toList()
        )
    }
}