package dev.milzipmoza.review.api.endpoint.member.callback

import dev.milzipmoza.review.api.endpoint.member.UrlStatus
import dev.milzipmoza.review.mongo.extension.Logger
import javax.servlet.http.HttpServletResponse
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class GithubOAuthCallbackController(
        private val githubUserService: GithubUserService,
        private val memberLoginService: MemberLoginService
) {

    private val log = Logger.of(this)

    @GetMapping("/oauth2/callback/github")
    fun callback(callbackRequest: CallbackRequest, httpServletResponse: HttpServletResponse) {
        val githubUser = githubUserService.getBy(callbackRequest.code)
        val member = ExternalUserConverter.convert(githubUser)
        val code = memberLoginService.processLogin(member, callbackRequest.deviceId)
        val redirectUri = GithubOAuthCallbackUriFactory.success("login", code, UrlStatus.SUCCESS)
        httpServletResponse.setHeader("X-Referer", "tecobrary-api-server")
        httpServletResponse.sendRedirect(redirectUri)
    }
}