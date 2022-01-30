package dev.milzipmoza.review.api.endpoint.member

import dev.milzipmoza.review.annotation.ApplicationService
import dev.milzipmoza.review.domain.member.Members

@ApplicationService
class MemberMyInfoService(
        private val members: Members
) {

    fun get(memberNo: String): MemberMyInfoDto {

        val member = members.findBy(memberNo)

        return MemberMyInfoDto(
                member = MemberDto(
                        name = member.info.name,
                        email = member.info.email,
                        profileImageUrl = member.info.profileImageUrl,
                        blogUrl = member.info.blogUrl,
                        description = member.info.description
                )
        )
    }
}