package dev.milzipmoza.review.api.endpoint.member

import dev.milzipmoza.review.api.ApiResponse
import dev.milzipmoza.review.api.AuthMemberDto
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestAttribute
import org.springframework.web.bind.annotation.RestController

@RestController
class MemberMyInfoController(
        private val memberMyInfoService: MemberMyInfoService
) {

    @GetMapping("/api/members/my-info")
    fun get(@RequestAttribute(AuthMemberDto.ATTRIBUTE_NAME) tecobraryMember: AuthMemberDto): ApiResponse<MemberMyInfoDto> {
        val memberMyInfo = memberMyInfoService.get(tecobraryMember.memberNo)
        return ApiResponse.success(data = memberMyInfo)
    }
}