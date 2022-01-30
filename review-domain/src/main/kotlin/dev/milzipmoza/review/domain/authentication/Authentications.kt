package dev.milzipmoza.review.domain.authentication

interface Authentications {

    fun findBy(code: String): Authentication?

    fun findBy(memberNo: String, deviceId: String): Authentication?

    fun findByAccessToken(accessToken: String): Authentication?
}