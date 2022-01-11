package dev.milzipmoza.review.api.endpoint

import dev.milzipmoza.review.api.ApiResponse
import java.util.*
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class DeviceIdGenerateController {

    @GetMapping("/api/device-ids")
    fun generate(): ApiResponse<String> {
        return ApiResponse.success(data = UUID.randomUUID().toString())
    }
}