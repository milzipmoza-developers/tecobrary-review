package dev.milzipmoza.review.jwt

import com.milzipmoza.review.util.convert
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import java.time.LocalDateTime

class JwtParser(
        private val secretKey: String,
        private val issuer: String,
        private val subject: String,
        private val scope: String,
        private val version: String,
) {

    fun encode(accessToken: String,
               createdDateTime: LocalDateTime,
               expiredDateTime: LocalDateTime) =
            Jwts.builder()
                    // header
                    .signWith(SIGNATURE_ALGORITHM, secretKey)
                    .setHeaderParam("typ", "JWT")

                    // payload
                    .setIssuer(issuer)
                    .setSubject(subject)
                    .claim("accessToken", accessToken)
                    .claim("scope", scope)
                    .claim("version", version)
                    .claim("issuedDateTime", createdDateTime.toString())
                    .setIssuedAt(createdDateTime.convert())
                    .setExpiration(expiredDateTime.convert())

                    .compact()

    fun decode(jwt: String): Map<String, Any?> {
        return Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJwt(jwt)
                .body
    }

    companion object {
        private val SIGNATURE_ALGORITHM = SignatureAlgorithm.HS256
    }
}