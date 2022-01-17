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

    fun findByMemberNo(memberNo: String): DocumentAuthentication?
}

@Document("authentications")
data class DocumentAuthentication(
        @Id val code: String,
        val accessToken: String,
        val deviceId: String,
        val memberNo: String,
        val expiredDateTime: LocalDateTime,
        val lastLoginDateTime: LocalDateTime,
        @Indexed(expireAfterSeconds = 30) val createdDateTime: LocalDateTime
)
