package dev.milzipmoza.review.api.endpoint.display.main

import dev.milzipmoza.review.api.ApiResponse
import dev.milzipmoza.review.api.endpoint.display.main.dto.DisplayMainDto
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class DisplayMainController(
        private val displayMainFacade: DisplayMainFacade
) {

    @GetMapping("/api/main")
    fun get(): ApiResponse<DisplayMainDto> {
        val displayMain = displayMainFacade.get()
        return ApiResponse.success(data = displayMain)
    }
}