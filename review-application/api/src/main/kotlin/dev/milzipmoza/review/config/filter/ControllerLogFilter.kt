package dev.milzipmoza.review.config.filter

import dev.milzipmoza.review.mongo.extension.Logger
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Component
class ControllerLogFilter : OncePerRequestFilter() {

    private val log = Logger.of(this)

    override fun doFilterInternal(request: HttpServletRequest, response: HttpServletResponse, filterChain: FilterChain) {
        val controllerLog = ControllerLog(request.servletPath, request.method)
        filterChain.doFilter(request, response)
        controllerLog.response(response.status)
        log.info(controllerLog.logMessage())
    }
}