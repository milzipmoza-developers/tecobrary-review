package dev.milzipmoza.review.mongo.authentication

import java.time.LocalDateTime
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.index.Indexed
import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
interface MongoAuthenticationRepository : MongoRepository<DocumentAuthentication, String> {

    fun findByCode(code: String): DocumentAuthentication?

    fun findByIdentification(identification: DocumentAuthIdentification): DocumentAuthentication?

    fun findByAccessToken(accessToken: String): DocumentAuthentication?
}

@Document("authentications")
data class DocumentAuthentication(
        @Id val code: String,
        val accessToken: String,
        @Indexed(unique = true) val identification: DocumentAuthIdentification,
        val expiredDateTime: LocalDateTime,
        val lastLoginDateTime: LocalDateTime,
        @Indexed(expireAfterSeconds = 30) val createdDateTime: LocalDateTime
)

data class DocumentAuthIdentification(
        val deviceId: String,
        val memberNo: String,
)
