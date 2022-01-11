package dev.milzipmoza.review.domain.authentication

interface AuthenticationOperation {

    fun save(authentication: Authentication): Boolean
}