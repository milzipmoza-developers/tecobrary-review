package dev.milzipmoza.review.api.endpoint.category.action

import dev.milzipmoza.review.api.ApiResponse
import dev.milzipmoza.review.domain.category.CategoryOperationException
import dev.milzipmoza.review.mongo.extension.Logger
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class CategoryActionControllerAdvice {

    private val logger = Logger.of(this)

    @ExceptionHandler(CategoryOperationException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun handle(e: CategoryOperationException): ApiResponse<Nothing> {
        logger.warn("카테고리 예외 발생", e)
        return ApiResponse.error(e.message ?: "카테고리 연산 수행 중 에러가 발생하였습니다.")
    }

    @ExceptionHandler(IllegalArgumentException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun handle(e: IllegalArgumentException): ApiResponse<Nothing> {
        logger.warn("카테고리 예외 발생", e)
        return ApiResponse.error(e.message ?: "카테고리 연산 수행 중 에러가 발생하였습니다.")
    }
}