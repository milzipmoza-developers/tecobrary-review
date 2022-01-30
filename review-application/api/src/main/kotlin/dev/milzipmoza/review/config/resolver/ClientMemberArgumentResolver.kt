package dev.milzipmoza.review.config.resolver

import dev.milzipmoza.review.api.ClientMemberDto
import dev.milzipmoza.review.config.TecobraryHeaders
import dev.milzipmoza.review.exception.HeaderNotFoundException
import org.springframework.core.MethodParameter
import org.springframework.stereotype.Component
import org.springframework.web.bind.support.WebDataBinderFactory
import org.springframework.web.context.request.NativeWebRequest
import org.springframework.web.method.support.HandlerMethodArgumentResolver
import org.springframework.web.method.support.ModelAndViewContainer

@Component
class ClientMemberArgumentResolver : HandlerMethodArgumentResolver {

    override fun supportsParameter(parameter: MethodParameter): Boolean {
        return parameter.parameterType == ClientMemberDto::class.java
    }

    override fun resolveArgument(parameter: MethodParameter, mavContainer: ModelAndViewContainer?, webRequest: NativeWebRequest, binderFactory: WebDataBinderFactory?): Any? {
        val deviceId = webRequest.getHeader(TecobraryHeaders.DEVICE_ID)
                ?: throw HeaderNotFoundException("헤더를 확인해주세요.", TecobraryHeaders.DEVICE_ID)
        val memberNo = webRequest.getHeader("X-TECOBRARY-MEMBER-NO")
                ?: throw HeaderNotFoundException("헤더를 확인해주세요.", "X-TECOBRARY-MEMBER-NO")

        return ClientMemberDto(memberNo, deviceId)
    }
}