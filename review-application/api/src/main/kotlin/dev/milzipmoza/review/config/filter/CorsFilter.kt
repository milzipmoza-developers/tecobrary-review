package dev.milzipmoza.review.config.filter

import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse
import org.springframework.core.Ordered
import org.springframework.core.annotation.Order
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter


@Order(Ordered.HIGHEST_PRECEDENCE)
@Component
class CorsFilter : OncePerRequestFilter() {

    override fun doFilterInternal(request: HttpServletRequest, response: HttpServletResponse, filterChain: FilterChain) {

        response.setHeader("Access-Control-Allow-Origin", "http://localhost:3000")
        response.setHeader("Access-Control-Allow-Credentials", "true")
        response.setHeader("Access-Control-Allow-Methods", "*")
        response.setHeader("Access-Control-Max-Age", "3600")
        response.setHeader("Access-Control-Allow-Headers", "*")

        if ("OPTIONS".equals(request.method, ignoreCase = true)) {
            response.status = HttpServletResponse.SC_OK
        } else {
            filterChain.doFilter(request, response)
        }
    }
}