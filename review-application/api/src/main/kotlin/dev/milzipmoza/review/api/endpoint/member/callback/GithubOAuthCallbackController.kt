package dev.milzipmoza.review.api.endpoint.member.callback

import dev.milzipmoza.review.domain.member.MemberOperation
import dev.milzipmoza.review.mongo.extension.Logger
import javax.servlet.http.HttpServletResponse
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class GithubOAuthCallbackController(
        private val githubUserService: GithubUserService,
        private val memberOperation: MemberOperation
) {

    private val log = Logger.of(this)

    @GetMapping("/oauth2/callback/github")
    fun callback(callbackRequest: CallbackRequest, httpServletResponse: HttpServletResponse) {
        try {
            val githubUser = githubUserService.getBy(callbackRequest.code)
            val member = ExternalUserConverter.convert(githubUser)
            memberOperation.upsert(member)
            val redirectUri = GithubOAuthCallbackUriFactory.success("login", "success", "temp-code")
            httpServletResponse.sendRedirect(redirectUri)
        } catch (e: Exception) {
            log.error("[GithubOAuthCallbackController] 로그인 도중 에러 발생", e)
            val redirectUri = GithubOAuthCallbackUriFactory.failed("login", "success", "L0001")
            httpServletResponse.sendRedirect(redirectUri)
        }
    }
}