package dev.milzipmoza.review.api.endpoint.authentication.info

import dev.milzipmoza.review.api.ApiResponse
import dev.milzipmoza.review.api.AuthMemberDto
import dev.milzipmoza.review.domain.member.Members
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestAttribute
import org.springframework.web.bind.annotation.RestController

@RestController
class AuthenticatedUserInfoController(
        private val members: Members
) {

    @GetMapping("/api/authentications/user-infos")
    fun getUserInfo(@RequestAttribute(AuthMemberDto.ATTRIBUTE_NAME) authMemberDto: AuthMemberDto): ApiResponse<AuthenticatedUserInfoDto> {
        val member = members.findBy(authMemberDto.memberNo)
        val authenticatedUserInfo = AuthenticatedUserInfoDto(memberNo = member.no, memberName = member.info.name, profileImageUrl = member.info.profileImageUrl)
        return ApiResponse.success(data = authenticatedUserInfo)
    }
}