package dev.milzipmoza.review.jwt

import com.milzipmoza.review.util.convert
import dev.milzipmoza.review.annotation.ApplicationService
import dev.milzipmoza.review.domain.authentication.Authentication
import io.jsonwebtoken.CompressionCodec
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm

@ApplicationService
class JwtAuthenticator(
        private val jwtProperties: JwtProperties
) {

    fun createToken(authentication: Authentication): String {
        return Jwts.builder()
                // header
                .signWith(SignatureAlgorithm.HS256, jwtProperties.secretKey)
                .setHeaderParam("typ", "JWT")

                // payload
                .setIssuer(jwtProperties.issuer)
                .setSubject(jwtProperties.subject)
                .claim("accessToken", authentication.accessToken)
                .claim("scope", jwtProperties.scope)
                .claim("version", jwtProperties.version)
                .claim("issuedDateTime", authentication.createdDateTime.toString())
                .setIssuedAt(authentication.createdDateTime.convert())
                .setExpiration(authentication.expiredDateTime.convert())

                .compact()
    }
}