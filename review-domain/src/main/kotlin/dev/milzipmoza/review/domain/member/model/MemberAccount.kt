package dev.milzipmoza.review.domain.member.model

import dev.milzipmoza.review.domain.Value

class MemberAccount(
        val identifier: String,
        val provider: MemberOAuthProvider
): Value<MemberAccount> {

    override fun sameAs(other: MemberAccount) = this == other

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as MemberAccount

        if (identifier != other.identifier) return false
        if (provider != other.provider) return false

        return true
    }

    override fun hashCode(): Int {
        var result = identifier.hashCode()
        result = 31 * result + provider.hashCode()
        return result
    }
}
