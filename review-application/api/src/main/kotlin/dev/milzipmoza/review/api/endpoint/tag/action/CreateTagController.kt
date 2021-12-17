package dev.milzipmoza.review.api.endpoint.tag.action

import dev.milzipmoza.review.api.ApiCreateBody
import dev.milzipmoza.review.api.ApiResponse
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class CreateTagController(
        private val createTagService: CreateTagService
) {

    @PutMapping("/api/tags")
    fun create(@RequestBody body: ApiCreateBody<CreateTagDto>): ApiResponse<Boolean> {
        val result = createTagService.doCreate(body.create)
        return ApiResponse.success(data = result)
    }
}