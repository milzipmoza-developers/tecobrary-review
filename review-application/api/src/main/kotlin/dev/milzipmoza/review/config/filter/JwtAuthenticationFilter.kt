package dev.milzipmoza.review.config.filter

import dev.milzipmoza.review.api.TecobraryMemberDto
import dev.milzipmoza.review.config.TecobraryHeaders
import dev.milzipmoza.review.domain.authentication.AuthenticationConfirm
import dev.milzipmoza.review.domain.authentication.Authentications
import dev.milzipmoza.review.exception.HeaderNotFoundException
import dev.milzipmoza.review.jwt.InvalidTokenException
import dev.milzipmoza.review.jwt.JwtValidator
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter

@Component
class JwtAuthenticationFilter(
        private val jwtValidator: JwtValidator,
        private val authentications: Authentications
) : OncePerRequestFilter() {

    override fun doFilterInternal(request: HttpServletRequest, response: HttpServletResponse, filterChain: FilterChain) {
        val authorization = request.getHeader(TecobraryHeaders.AUTHORIZATION)
                ?: throw HeaderNotFoundException(fieldName = TecobraryHeaders.AUTHORIZATION)

        val deviceId = request.getHeader(TecobraryHeaders.DEVICE_ID)
                ?: throw HeaderNotFoundException(fieldName = TecobraryHeaders.DEVICE_ID)

        val validatedDecodedJwt = jwtValidator.getValidatedDecodedJwt(authorization)

        val authentication = authentications.findByAccessToken(validatedDecodedJwt.accessToken)
                ?: throw InvalidTokenException("유효하지 않은 토큰입니다.")

        val confirm = AuthenticationConfirm(authentication)

        confirm.expired()
        confirm.device(deviceId)

        request.setAttribute(TecobraryMemberDto.ATTRIBUTE_NAME, TecobraryMemberDto(authentication.memberNo, authentication.deviceId))

        filterChain.doFilter(request, response)
    }
}