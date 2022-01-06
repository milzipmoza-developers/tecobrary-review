package dev.milzipmoza.review.github.login

import org.springframework.cloud.openfeign.FeignClient
import org.springframework.stereotype.Component
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody

@FeignClient(
        value = "github-api",
        url = "https://github.com",
)
@Component
interface GithubLoginClient {

    @PostMapping("/login/oauth/access_token", headers = ["Accept=application/json"])
    fun getAccessToken(@RequestBody body: GithubAccessTokenRequest): GithubAccessTokenResponse
}
