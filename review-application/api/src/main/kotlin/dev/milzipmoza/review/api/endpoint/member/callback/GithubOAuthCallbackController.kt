package dev.milzipmoza.review.api.endpoint.member.callback

import dev.milzipmoza.review.api.endpoint.member.UrlStatus
import dev.milzipmoza.review.domain.authentication.DeviceNotMatchException
import dev.milzipmoza.review.mongo.extension.Logger
import javax.servlet.http.HttpServletResponse
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ResponseStatus
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

    @ExceptionHandler(DeviceNotMatchException::class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    fun handleIllegalAccessException(e: DeviceNotMatchException, httpServletResponse: HttpServletResponse) {
        log.error("[GithubOAuthCallbackController] 로그인 도중 에러 발생", e)
        val redirectUri = GithubOAuthCallbackUriFactory.failed("login", UrlStatus.NOT_MATCH_DEVICE)
        httpServletResponse.setHeader("X-Referer", "tecobrary-api-server")
        httpServletResponse.sendRedirect(redirectUri)
    }

    @ExceptionHandler(Exception::class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    fun handleException(e: Exception, httpServletResponse: HttpServletResponse) {
        log.error("[GithubOAuthCallbackController] 로그인 도중 에러 발생", e)
        val redirectUri = GithubOAuthCallbackUriFactory.failed("login", UrlStatus.AUTH_FAILURE)
        httpServletResponse.setHeader("X-Referer", "tecobrary-api-server")
        httpServletResponse.sendRedirect(redirectUri)
    }
}