package dev.milzipmoza.review.jwt

import dev.milzipmoza.review.annotation.ApplicationService
import dev.milzipmoza.review.domain.authentication.Authentication

@ApplicationService
class JwtAuthenticator(
        jwtProperties: JwtProperties
) {

    private val jwtParser = JwtParser(
            secretKey = jwtProperties.secretKey,
            issuer = jwtProperties.issuer,
            subject = jwtProperties.subject,
            scope = jwtProperties.scope,
            version = jwtProperties.version
    )

    fun createToken(authentication: Authentication) =
            jwtParser.encode(authentication.accessToken, authentication.createdDateTime, authentication.expiredDateTime)

}