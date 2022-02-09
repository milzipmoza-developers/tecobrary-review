package dev.milzipmoza.review.api.endpoint.tag.action.create

import dev.milzipmoza.review.api.AdminMemberDto
import dev.milzipmoza.review.api.ApiCreateBody
import dev.milzipmoza.review.api.ApiResponse
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestAttribute
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class CreateTagController(
        private val createTagService: CreateTagService
) {

    @PostMapping("/api/tags")
    fun create(@RequestBody body: ApiCreateBody<CreateTagDto>,
               @RequestAttribute(AdminMemberDto.ATTRIBUTE_NAME) adminMemberDto: AdminMemberDto): ApiResponse<Boolean> {
        val result = createTagService.doCreate(body.create)
        return ApiResponse.success(data = result)
    }
}