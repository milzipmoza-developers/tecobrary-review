package dev.milzipmoza.review.api

import com.fasterxml.jackson.annotation.JsonFormat
import java.time.LocalDate
import java.time.LocalDateTime

data class ApiResponse<T>(
        val status: String,
        val message: String,
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS")
        val responseDateTime: LocalDateTime = LocalDateTime.now(),
        val data: T?
) {
    companion object {
        fun <T> success(message: String = "응답을 성공적으로 수신하였습니다.", data: T): ApiResponse<T> {
            return ApiResponse(
                    status = "SUCCESS",
                    message = message,
                    data = data
            )
        }

        fun error(message: String): ApiResponse<Nothing> {
            return ApiResponse(
                    status = "ERROR",
                    message = message,
                    data = null
            )
        }
    }
}