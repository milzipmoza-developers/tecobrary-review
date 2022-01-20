package dev.milzipmoza.review.api.endpoint.member.callback

import dev.milzipmoza.review.annotation.ApplicationService
import org.springframework.beans.factory.annotation.Value

@ApplicationService
class MemberAccessTokenFactory(
        @Value("\${tecobrary.authentication.encrypt.key}") private val key: String
) {

    private val aes256Encryptor = Aes256Encryptor(key)

    fun create(memberNo: String): String {
        val currentTimeMillis = System.currentTimeMillis()
        return aes256Encryptor.encrypt("$memberNo$currentTimeMillis")
    }
}