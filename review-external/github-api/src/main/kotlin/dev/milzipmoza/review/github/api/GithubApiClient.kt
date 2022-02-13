package dev.milzipmoza.review.github.api

import com.fasterxml.jackson.annotation.JsonProperty
import dev.milzipmoza.review.github.config.GithubApiConfiguration
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.stereotype.Component
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestHeader

@FeignClient(
        value = "github-api",
        url = "https://api.github.com",
        configuration = [GithubApiConfiguration::class]
)
@Component
interface GithubApiClient {

    @GetMapping("/user", headers = ["Accept=application/vnd.github.v3+json"])
    fun getUser(@RequestHeader("Authorization") token: String): GithubUserResponse

    @GetMapping("/user/emails", headers = ["Accept=application/vnd.github.v3+json"])
    fun getEmails(@RequestHeader("Authorization") token: String): List<GithubEmailResponse>

    @GetMapping("/user/emails", headers = ["Accept=application/vnd.github.v3+json"])
    fun getEmails(): List<GithubEmailResponse>
}

data class GithubUserResponse(
        val login: String,
        val id: String,
        @JsonProperty("avatar_url")
        val avatarUrl: String,
        var bio: String = ""
)

data class GithubEmailResponse(
        val email: String,
        val verified: Boolean,
        val primary: Boolean,
        val visibility: String? = null
)
