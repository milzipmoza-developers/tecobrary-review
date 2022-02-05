package dev.milzipmoza.review.api

sealed interface ClientMember {

    val memberNo: String?
    val deviceId: String?

    class AuthenticatedMember(
            override val memberNo: String,
            override val deviceId: String
    ) : ClientMember

    class UnauthenticatedMember(
            override val deviceId: String
    ) : ClientMember {
        override val memberNo: Nothing? = null
    }

    class UnknownMember : ClientMember {
        override val deviceId: Nothing? = null
        override val memberNo: Nothing? = null
    }

    companion object {
        const val ATTRIBUTE_NAME = "clientMember"
    }
}