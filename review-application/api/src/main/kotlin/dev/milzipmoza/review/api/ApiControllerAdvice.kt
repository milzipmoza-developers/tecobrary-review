package dev.milzipmoza.review.api

import dev.milzipmoza.review.exception.HeaderNotFoundException
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class ApiControllerAdvice {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(HeaderNotFoundException::class)
    fun handleHeaderNotFound(e: HeaderNotFoundException): ApiResponse<Nothing> {
        return ApiResponse.error(e.message ?: "헤더를 확인해주세요.")
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception::class)
    fun handleUnknown(e: Exception): ApiResponse<Nothing> {
        return ApiResponse.error("알 수 없는 문제가 발생하였어요.")
    }
}