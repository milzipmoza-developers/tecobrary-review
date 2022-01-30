package dev.milzipmoza.review.jwt

import org.springframework.stereotype.Component

@Component
class JwtValidator(
        jwtProperties: JwtProperties
) {

    private val jwtParser = JwtParser(
            secretKey = jwtProperties.secretKey,
            issuer = jwtProperties.issuer,
            subject = jwtProperties.subject,
            scope = jwtProperties.scope,
            version = jwtProperties.version
    )

    fun getValidatedDecodedJwt(authorization: String): DecodedJwt {
        if (!authorization.startsWith("token ")) {
            throw IllegalArgumentException("Authorization 헤더 정보가 정확하지 않습니다.")
        }

        val splitAuthorization = authorization.split(" ")

        if (splitAuthorization.size != 2) {
            throw IllegalArgumentException("Authorization 헤더 정보가 정확하지 않습니다.")
        }

        val jwt = splitAuthorization[1]

        val decodedJwt = DecodedJwt(jwtParser.decode(jwt))

        if (jwtParser.isNotValidParsed(decodedJwt)) {
            throw InvalidTokenException("유효하지 않은 토큰입니다.")
        }

        return decodedJwt
    }
}