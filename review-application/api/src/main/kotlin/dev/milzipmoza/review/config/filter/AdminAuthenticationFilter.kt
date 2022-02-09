package dev.milzipmoza.review.config.filter

import dev.milzipmoza.review.api.AdminMemberDto
import dev.milzipmoza.review.config.TecobraryHeaders
import dev.milzipmoza.review.domain.authentication.AuthenticationConfirm
import dev.milzipmoza.review.domain.authentication.Authentications
import dev.milzipmoza.review.domain.member.Members
import dev.milzipmoza.review.jwt.JwtValidator
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter

@Component
class AdminAuthenticationFilter(
        private val jwtValidator: JwtValidator,
        private val authentications: Authentications,
        private val members: Members
) : OncePerRequestFilter() {

    override fun doFilterInternal(request: HttpServletRequest, response: HttpServletResponse, filterChain: FilterChain) {
        if (request.method == "GET") {
            filterChain.doFilter(request, response)
            return
        }

        val authorization = request.getHeader(TecobraryHeaders.AUTHORIZATION)
                ?: throw RuntimeException()

        val deviceId = request.getHeader(TecobraryHeaders.DEVICE_ID)
                ?: throw RuntimeException()

        val validatedDecodedJwt = jwtValidator.getValidatedDecodedJwt(authorization)

        val authentication = authentications.findByAccessToken(validatedDecodedJwt.accessToken)
                ?: throw RuntimeException()

        val member = members.findBy(authentication.memberNo)

        if (!member.isAdmin) {
            throw RuntimeException()
        }

        val confirm = AuthenticationConfirm(authentication)

        confirm.expired()
        confirm.device(deviceId)

        request.setAttribute(AdminMemberDto.ATTRIBUTE_NAME, AdminMemberDto(member.no, authentication.deviceId))

        filterChain.doFilter(request, response)
    }
}