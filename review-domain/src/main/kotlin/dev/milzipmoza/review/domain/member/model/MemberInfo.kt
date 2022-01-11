package dev.milzipmoza.review.domain.member.model

import dev.milzipmoza.review.domain.Value

class MemberInfo(
        val name: String,
        val email: String,
        val blogUrl: String,
        val description: String
): Value<MemberInfo> {

    override fun sameAs(other: MemberInfo) = this == other

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as MemberInfo

        if (name != other.name) return false
        if (email != other.email) return false
        if (blogUrl != other.blogUrl) return false
        if (description != other.description) return false

        return true
    }

    override fun hashCode(): Int {
        var result = name.hashCode()
        result = 31 * result + email.hashCode()
        result = 31 * result + blogUrl.hashCode()
        result = 31 * result + description.hashCode()
        return result
    }
}
