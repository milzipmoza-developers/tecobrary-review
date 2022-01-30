package dev.milzipmoza.review.jwt

import com.milzipmoza.review.util.convert

class DecodedJwt(
        decodeJwt: Map<String, Any?>
) {
    val issuer = decodeJwt["iss"] as? String
            ?: throw InvalidTokenException("유효하지 않은 토큰입니다.")

    val subject = decodeJwt["sub"] as? String
            ?: throw InvalidTokenException("유효하지 않은 토큰입니다.")

    val accessToken = decodeJwt["accessToken"] as? String
            ?: throw InvalidTokenException("유효하지 않은 토큰입니다.")

    val version = decodeJwt["version"] as? String
            ?: throw InvalidTokenException("유효하지 않은 토큰입니다.")

    val issuedDateTime = (decodeJwt["issuedDateTime"] as? String)?.convert()
            ?: throw InvalidTokenException("유효하지 않은 토큰입니다.")
}