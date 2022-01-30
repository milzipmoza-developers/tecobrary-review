package dev.milzipmoza.review.api.endpoint.authentication.callback

import dev.milzipmoza.review.domain.member.model.Member
import dev.milzipmoza.review.domain.member.model.MemberAccount
import dev.milzipmoza.review.domain.member.model.MemberInfo
import dev.milzipmoza.review.domain.member.model.MemberOAuthProvider
import dev.milzipmoza.review.github.api.GithubUserDto

object ExternalUserConverter {

    fun convert(githubUser: GithubUserDto): Member {
        return Member(
                account = MemberAccount(identifier = githubUser.id, provider = MemberOAuthProvider.GITHUB),
                info = MemberInfo(name = githubUser.name, profileImageUrl = githubUser.avatarUrl, email = githubUser.email, blogUrl = "", description = githubUser.bio)
        )
    }
}