package dev.milzipmoza.review.api.endpoint.authentication.login

import dev.milzipmoza.review.api.ApiResponse
import dev.milzipmoza.review.api.ClientDto
import dev.milzipmoza.review.github.oauth.GithubLoginUrlGenerator
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class LoginUrlController(
        private val githubLoginUrlGenerator: GithubLoginUrlGenerator
) {

    @GetMapping("/api/logins")
    fun getUrl(clientDto: ClientDto): ApiResponse<String> {
        return ApiResponse.success(data = githubLoginUrlGenerator.generate(clientDto.deviceId))
    }
}