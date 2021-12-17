package dev.milzipmoza.review.api.endpoint.tag.action.update

import dev.milzipmoza.review.api.ApiResponse
import dev.milzipmoza.review.api.ApiUpdateBody
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class UpdateTagController(
        private val updateTagService: UpdateTagService
) {

    @PostMapping("/api/tags/{tagNo}")
    fun update(@PathVariable tagNo: String,
               @RequestBody body: ApiUpdateBody<UpdateTagDto>): ApiResponse<Boolean> {
        val result = updateTagService.doUpdate(tagNo, body.update)
        return ApiResponse.success(data = result)
    }
}