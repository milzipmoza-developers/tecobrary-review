package dev.milzipmoza.review.api

import dev.milzipmoza.review.exception.HeaderNotFoundException
import dev.milzipmoza.review.mongo.DocumentNotFoundException
import dev.milzipmoza.review.mongo.extension.Logger
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class ApiControllerAdvice {

    private val log = Logger.of(this)

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(DocumentNotFoundException::class)
    fun handle(e: DocumentNotFoundException): ApiResponse<Nothing> {
        log.warn("도큐먼트를 찾을 수 없습니다.", e)
        return ApiResponse.error(message = e.message ?: "요청하신 내용을 찾을 수 없어요.")
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(HeaderNotFoundException::class)
    fun handleHeaderNotFound(e: HeaderNotFoundException): ApiResponse<Nothing> {
        log.warn("헤더가 누락되었습니다. fieldName=${e.fieldName}", e)
        return ApiResponse.error(e.message ?: "헤더를 확인해주세요.")
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(IllegalArgumentException::class)
    fun handleIllegalArgument(e: IllegalArgumentException): ApiResponse<Nothing> {
        log.warn("부정확한 요청입니다.", e)
        return ApiResponse.error(e.message ?: "요청을 확인해주세요.")
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception::class)
    fun handleUnknown(e: Exception): ApiResponse<Nothing> {
        log.error("알 수 없는 에러가 발생하였습니다.", e)
        return ApiResponse.error("알 수 없는 문제가 발생하였어요.")
    }
}