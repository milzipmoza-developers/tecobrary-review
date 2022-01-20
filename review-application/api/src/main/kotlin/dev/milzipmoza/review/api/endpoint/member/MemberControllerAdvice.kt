package dev.milzipmoza.review.api.endpoint.member

import dev.milzipmoza.review.api.endpoint.member.auth.MemberAuthController
import dev.milzipmoza.review.api.endpoint.member.callback.GithubOAuthCallbackController
import dev.milzipmoza.review.api.endpoint.member.callback.GithubOAuthCallbackUriFactory
import dev.milzipmoza.review.domain.authentication.DeviceNotMatchException
import dev.milzipmoza.review.mongo.extension.Logger
import javax.servlet.http.HttpServletResponse
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus

@ControllerAdvice(basePackageClasses = [
    MemberAuthController::class,
    GithubOAuthCallbackController::class
])
class MemberControllerAdvice {

    private val log = Logger.of(this)

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