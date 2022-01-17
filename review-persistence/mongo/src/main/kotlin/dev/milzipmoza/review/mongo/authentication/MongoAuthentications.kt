package dev.milzipmoza.review.mongo.authentication

import dev.milzipmoza.review.domain.authentication.Authentication
import dev.milzipmoza.review.domain.authentication.Authentications
import org.springframework.stereotype.Repository

@Repository
class MongoAuthentications(
        private val mongoAuthenticationRepository: MongoAuthenticationRepository
) : Authentications {

    override fun findBy(code: String): Authentication? {
        val documentAuthentication = mongoAuthenticationRepository.findByCode(code)
                ?: return null

        return Authentication(
                code = documentAuthentication.code,
                accessToken = documentAuthentication.accessToken,
                deviceId = documentAuthentication.identification.deviceId,
                memberNo = documentAuthentication.identification.memberNo,
                createdDateTime = documentAuthentication.createdDateTime,
                lastLoginDateTime = documentAuthentication.lastLoginDateTime
        )
    }

    override fun findBy(memberNo: String, deviceId: String): Authentication? {
        val documentAuthentication = mongoAuthenticationRepository.findByIdentification(DocumentAuthIdentification(memberNo, deviceId))
                ?: return null

        return Authentication(
                code = documentAuthentication.code,
                accessToken = documentAuthentication.accessToken,
                deviceId = documentAuthentication.identification.deviceId,
                memberNo = documentAuthentication.identification.memberNo,
                createdDateTime = documentAuthentication.createdDateTime,
                lastLoginDateTime = documentAuthentication.lastLoginDateTime
        )
    }
}