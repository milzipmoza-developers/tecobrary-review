package dev.milzipmoza.review.api.endpoint.book

import dev.milzipmoza.review.api.ApiResponse
import dev.milzipmoza.review.api.endpoint.book.action.BookAddCategoryController
import dev.milzipmoza.review.api.endpoint.book.action.BookClearCategoryController
import dev.milzipmoza.review.api.endpoint.book.action.CreateBookController
import dev.milzipmoza.review.api.endpoint.book.action.UpdateBookController
import dev.milzipmoza.review.api.endpoint.category.action.CategoryCreateController
import dev.milzipmoza.review.api.endpoint.category.action.CategoryUpdateController
import dev.milzipmoza.review.api.endpoint.category.search.CategoryController
import dev.milzipmoza.review.domain.book.BookOperationException
import dev.milzipmoza.review.domain.category.CategoryOperationException
import dev.milzipmoza.review.mongo.extension.Logger
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice(basePackages = [
    "dev.milzipmoza.review.api.endpoint.book"
])
class BookControllerAdvice {

    private val logger = Logger.of(this)

    @ExceptionHandler(BookOperationException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun handle(e: BookOperationException): ApiResponse<Nothing> {
        logger.warn("도서 예외 발생", e)
        return ApiResponse.error(e.message ?: "도서 작업 중 에러가 발생하였습니다.")
    }

    @ExceptionHandler(IllegalArgumentException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun handle(e: IllegalArgumentException): ApiResponse<Nothing> {
        logger.warn("도서 예외 발생", e)
        return ApiResponse.error(e.message ?: "도서 작업 중 에러가 발생하였습니다.")
    }
}