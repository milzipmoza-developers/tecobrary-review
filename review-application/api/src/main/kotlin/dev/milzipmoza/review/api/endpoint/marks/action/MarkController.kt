package dev.milzipmoza.review.api.endpoint.marks.action

import dev.milzipmoza.review.api.ApiResponse
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class MarkController(
        private val markService: MarkService,
        private val unmarkService: UnmarkService
) {

    @PostMapping("/api/marks/{type}/mark")
    fun mark(@PathVariable type: String,
             @RequestBody body: MarkDto): ApiResponse<Boolean> {
        val success = markService.doMark(body.bookNo, body.memberNo, type)
        return ApiResponse.success(data = success)
    }

    @PostMapping("/api/marks/{type}/unmark")
    fun unmark(@PathVariable type: String,
               @RequestBody body: MarkDto): ApiResponse<Boolean> {
        val success = unmarkService.doUnmark(body.bookNo, body.memberNo, type)
        return ApiResponse.success(data = success)
    }
}