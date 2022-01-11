package dev.milzipmoza.review.mongo.authentication

import dev.milzipmoza.review.domain.authentication.Authentication
import dev.milzipmoza.review.domain.authentication.Authentications
import org.springframework.stereotype.Repository

@Repository
class MongoAuthentications(
        private val mongoAuthenticationRepository: MongoAuthenticationRepository
) : Authentications {

    override fun findByCode(code: String): Authentication? {
        val documentAuthentication = mongoAuthenticationRepository.findByCode(code)
                ?: return null

        return Authentication(
                code = documentAuthentication.code,
                deviceId = documentAuthentication.deviceId,
                memberNo = documentAuthentication.memberNo,
                createdDateTime = documentAuthentication.createdDateTime,
                lastLoginDateTime = documentAuthentication.lastLoginDateTime
        )
    }

    override fun findByMemberNo(memberNo: String): Authentication? {
        val documentAuthentication = mongoAuthenticationRepository.findByMemberNo(memberNo)
                ?: return null

        return Authentication(
                code = documentAuthentication.code,
                deviceId = documentAuthentication.deviceId,
                memberNo = documentAuthentication.memberNo,
                createdDateTime = documentAuthentication.createdDateTime,
                lastLoginDateTime = documentAuthentication.lastLoginDateTime
        )
    }
}