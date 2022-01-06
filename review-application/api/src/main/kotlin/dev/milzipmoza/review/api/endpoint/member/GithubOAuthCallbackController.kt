package dev.milzipmoza.review.api.endpoint.member

import dev.milzipmoza.review.github.login.GithubLoginService
import dev.milzipmoza.review.mongo.extension.Logger
import javax.servlet.http.HttpServletResponse
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class GithubOAuthCallbackController(
        private val githubLoginService: GithubLoginService
) {

    private val log = Logger.of(this)

    @GetMapping("/oauth2/callback/github")
    fun callback(callbackRequest: CallbackRequest, httpServletResponse: HttpServletResponse) {
        log.info("[GithubOAuthCallbackController] params={}", callbackRequest)
        val accessToken = githubLoginService.getAccessToken(callbackRequest.code)
        log.info("[GithubOAuthCallbackController] accessToken={}", accessToken)
        httpServletResponse.sendRedirect("http://localhost:3000/")
    }
}