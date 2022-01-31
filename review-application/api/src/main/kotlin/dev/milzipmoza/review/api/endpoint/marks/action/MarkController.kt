package dev.milzipmoza.review.api.endpoint.marks.action

import dev.milzipmoza.review.api.ApiResponse
import dev.milzipmoza.review.api.AuthMemberDto
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestAttribute
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
class MarkController(
        private val markService: MarkService,
        private val unmarkService: UnmarkService
) {

    @PostMapping("/api/marks/{type}/mark")
    fun mark(@PathVariable type: String,
             @RequestAttribute(AuthMemberDto.ATTRIBUTE_NAME) memberDto: AuthMemberDto,
             @RequestBody body: MarkDto): ApiResponse<Boolean> {
        val success = markService.doMark(body.isbn, memberDto.memberNo, type)
        return ApiResponse.success(data = success)
    }

    @PostMapping("/api/marks/{type}/unmark")
    fun unmark(@PathVariable type: String,
               @RequestAttribute(AuthMemberDto.ATTRIBUTE_NAME) memberDto: AuthMemberDto,
               @RequestBody body: MarkDto): ApiResponse<Boolean> {
        val success = unmarkService.doUnmark(body.isbn, memberDto.memberNo, type)
        return ApiResponse.success(data = success)
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun handle(e: IllegalArgumentException): ApiResponse<Nothing> {
        return ApiResponse.error(message = e.message ?: "올바른 요청이 아닙니다.")
    }
}