package dev.milzipmoza.review.api.endpoint.authentication.auth

import dev.milzipmoza.review.api.ApiResponse
import dev.milzipmoza.review.api.ClientDto
import dev.milzipmoza.review.api.endpoint.authentication.UrlStatus
import dev.milzipmoza.review.domain.authentication.DeviceNotMatchException
import dev.milzipmoza.review.mongo.extension.Logger
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController


@RestController
class MemberAuthController(
        private val memberAuthService: MemberAuthService
) {
    private val log = Logger.of(this)

    @GetMapping("/api/authenticates")
    fun getToken(clientDto: ClientDto, @RequestParam code: String): ApiResponse<MemberAuthDto> {
        val token = memberAuthService.createToken(clientDto.deviceId, code)
        return ApiResponse.success(data = MemberAuthDto(token = token))
    }

    @ExceptionHandler(IllegalAccessException::class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    fun handleIllegalAccessException(e: DeviceNotMatchException): ApiResponse<Nothing> {
        log.error("[MemberAuthController] 로그인 도중 에러 발생", e)
        return ApiResponse.error(UrlStatus.REQUIRE_RE_AUTH.name, e.message)
    }

    @ExceptionHandler(DeviceNotMatchException::class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    fun handleDeviceNotMatchException(e: DeviceNotMatchException): ApiResponse<Nothing> {
        log.error("[MemberAuthController] 로그인 도중 에러 발생", e)
        return ApiResponse.error(UrlStatus.NOT_MATCH_DEVICE.name, e.message)
    }
}