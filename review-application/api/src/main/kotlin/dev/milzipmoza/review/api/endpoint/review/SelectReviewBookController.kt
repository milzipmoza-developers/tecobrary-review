package dev.milzipmoza.review.api.endpoint.review

import dev.milzipmoza.review.api.ApiResponse
import dev.milzipmoza.review.api.OptionalAuthMemberDto
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
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestAttribute
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class SelectReviewBookController(
        private val selectReviewBookService: SelectReviewBookService
) {

    @PostMapping("/api/review/select-books")
    fun select(@RequestAttribute(OptionalAuthMemberDto.ATTRIBUTE_NAME) memberDto: OptionalAuthMemberDto?,
               @RequestBody selectReviewBook: SelectReviewBookDto): ApiResponse<SelectReviewBookResultDto> {
        val selectResult = selectReviewBookService.select(memberDto, selectReviewBook)
        return ApiResponse.success(data = selectResult)
    }
}