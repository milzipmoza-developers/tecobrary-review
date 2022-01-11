package dev.milzipmoza.review.mongo.member.mongo

import dev.milzipmoza.review.domain.member.model.Member
import dev.milzipmoza.review.domain.member.model.MemberAccount
import dev.milzipmoza.review.domain.member.model.MemberInfo

object DocumentMemberMapper {

    fun map(member: Member): DocumentMember {
        return DocumentMember(
                account = map(member.account),
                info = map(member.info)
        )
    }

    fun map(memberAccount: MemberAccount) = DocumentMemberAccount(
            identifier = memberAccount.identifier,
            provider = memberAccount.provider.name
    )

    private fun map(memberInfo: MemberInfo) = DocumentMemberInfo(
            name = memberInfo.name,
            email = memberInfo.email,
            description = memberInfo.description
    )
}