package dev.milzipmoza.review.mongo.member.mongo

import dev.milzipmoza.review.domain.member.model.Member
import dev.milzipmoza.review.domain.member.model.MemberAccount
import dev.milzipmoza.review.domain.member.model.MemberInfo
import dev.milzipmoza.review.domain.member.model.MemberOAuthProvider

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

    fun map(documentMember: DocumentMember): Member {
        return Member(
                no = documentMember.id.toHexString(),
                account = map(documentMember.account),
                info = map(documentMember.info)
        )
    }

    private fun map(documentMemberAccount: DocumentMemberAccount) =
            MemberAccount(identifier = documentMemberAccount.identifier, provider = MemberOAuthProvider.valueOf(documentMemberAccount.provider))

    private fun map(documentMemberInfo: DocumentMemberInfo) =
            MemberInfo(name = documentMemberInfo.name, email = documentMemberInfo.email, blogUrl = "", description = documentMemberInfo.description)
}