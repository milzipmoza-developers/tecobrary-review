package dev.milzipmoza.review.api.endpoint.authentication.callback

import java.util.*
import javax.crypto.Cipher
import javax.crypto.spec.IvParameterSpec
import javax.crypto.spec.SecretKeySpec

class Aes256Encryptor(
        private val key: String
) {

    private val iv = key.substring(0, 16) // 16byte
    
    fun encrypt(text: String): String {
        val cipher = Cipher.getInstance(ALGORITHM)
        val keySpec = SecretKeySpec(key.toByteArray(), "AES")
        val ivParamSpec = IvParameterSpec(iv.toByteArray())
        cipher.init(Cipher.ENCRYPT_MODE, keySpec, ivParamSpec)
        val encrypted = cipher.doFinal(text.toByteArray(CHARACTER_ENCODING))
        return Base64.getEncoder().encodeToString(encrypted)
    }

    fun decrypt(cipherText: String): String {
        val cipher = Cipher.getInstance(ALGORITHM)
        val keySpec = SecretKeySpec(key.toByteArray(), "AES")
        val ivParamSpec = IvParameterSpec(iv.toByteArray())
        cipher.init(Cipher.DECRYPT_MODE, keySpec, ivParamSpec)
        val decodedBytes = Base64.getDecoder().decode(cipherText)
        val decrypted = cipher.doFinal(decodedBytes)
        return String(decrypted, CHARACTER_ENCODING)
    }

    companion object {
        private const val ALGORITHM = "AES/CBC/PKCS5Padding"
        private val CHARACTER_ENCODING = charset("UTF-8")
    }
}