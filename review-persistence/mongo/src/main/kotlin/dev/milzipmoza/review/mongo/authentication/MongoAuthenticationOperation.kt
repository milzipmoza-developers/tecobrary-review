package dev.milzipmoza.review.mongo.authentication

import dev.milzipmoza.review.domain.authentication.Authentication
import dev.milzipmoza.review.domain.authentication.AuthenticationOperation
import org.springframework.stereotype.Repository

@Repository
class MongoAuthenticationOperation(
        private val mongoAuthenticationRepository: MongoAuthenticationRepository
) : AuthenticationOperation {

    override fun save(authentication: Authentication): Boolean {
        val documentAuthentication = DocumentAuthentication(
                code = authentication.code,
                accessToken = authentication.accessToken,
                identification = DocumentAuthIdentification(authentication.deviceId, authentication.memberNo),
                expiredDateTime = authentication.expiredDateTime,
                createdDateTime = authentication.createdDateTime,
                lastLoginDateTime = authentication.lastLoginDateTime
        )
        mongoAuthenticationRepository.save(documentAuthentication)
        return true
    }
}