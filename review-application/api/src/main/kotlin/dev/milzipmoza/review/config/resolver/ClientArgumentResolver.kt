package dev.milzipmoza.review.config.resolver

import dev.milzipmoza.review.api.ClientDto
import dev.milzipmoza.review.config.TecobraryHeaders
import dev.milzipmoza.review.exception.HeaderNotFoundException
import org.springframework.core.MethodParameter
import org.springframework.stereotype.Component
import org.springframework.web.bind.support.WebDataBinderFactory
import org.springframework.web.context.request.NativeWebRequest
import org.springframework.web.method.support.HandlerMethodArgumentResolver
import org.springframework.web.method.support.ModelAndViewContainer

@Component
class ClientArgumentResolver : HandlerMethodArgumentResolver {

    override fun supportsParameter(parameter: MethodParameter): Boolean {
        return parameter.parameterType == ClientDto::class.java
    }

    override fun resolveArgument(parameter: MethodParameter, mavContainer: ModelAndViewContainer?, webRequest: NativeWebRequest, binderFactory: WebDataBinderFactory?): Any? {
        val deviceId = webRequest.getHeader(TecobraryHeaders.DEVICE_ID)
                ?: throw HeaderNotFoundException("헤더를 확인해주세요.", TecobraryHeaders.DEVICE_ID)

        return ClientDto(deviceId = deviceId)
    }
}