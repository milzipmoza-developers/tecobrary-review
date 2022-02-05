package dev.milzipmoza.review.api.endpoint.review

import dev.milzipmoza.review.annotation.ApplicationService
import dev.milzipmoza.review.api.ClientMember
import dev.milzipmoza.review.domain.book.BookOperation
import dev.milzipmoza.review.domain.book.Books
import dev.milzipmoza.review.domain.book.model.Book
import dev.milzipmoza.review.domain.book.model.detail.BookDetail
import dev.milzipmoza.review.domain.book.model.detail.BookImageUrl
import dev.milzipmoza.review.domain.book.model.detail.BookLanguage
import dev.milzipmoza.review.domain.review.DraftReviewOperation
import dev.milzipmoza.review.domain.review.model.DraftReview
import dev.milzipmoza.review.domain.review.model.DraftReviewMember
import dev.milzipmoza.review.domain.review.model.ReviewBook
import dev.milzipmoza.review.mongo.DocumentNotFoundException

@ApplicationService
class SelectReviewBookService(
        private val books: Books,
        private val bookOperation: BookOperation,
        private val reviewOperation: DraftReviewOperation
) {

    fun select(clientMember: ClientMember, selectReviewBook: SelectReviewBookDto): SelectReviewBookResultDto {
        val book: Book = try {
            books.findBy(selectReviewBook.isbn)
        } catch (e: DocumentNotFoundException) {
            val newBook = Book(
                    isbn = selectReviewBook.isbn,
                    detail = BookDetail(
                            image = BookImageUrl(selectReviewBook.imageUrl),
                            title = selectReviewBook.title,
                            publisher = selectReviewBook.publisher,
                            author = selectReviewBook.author,
                            locale = BookLanguage.KOREAN,
                            publishDate = selectReviewBook.publishDate,
                            description = selectReviewBook.description
                    )
            )
            bookOperation.save(newBook)
            books.findBy(newBook.isbn)
        }

        return when (clientMember) {
            is ClientMember.UnknownMember -> {
                return SelectReviewBookResultDto(false)
            }

            is ClientMember.AuthenticatedMember,
            is ClientMember.UnauthenticatedMember -> {
                val draftReviewNo = DraftReviewNoFactory.create()

                val draftReview = DraftReview.DraftReviewFirstStep(
                        no = draftReviewNo,
                        member = DraftReviewMember(
                                no = clientMember.memberNo,
                                deviceId = clientMember.deviceId!!
                        ),
                        book = ReviewBook(
                                isbn = book.isbn,
                                title = book.detail.title
                        )
                )

                val saved = reviewOperation.saveOrUpdate(draftReview)

                return SelectReviewBookResultDto(saved, draftReviewNo)
            }
        }
    }
}