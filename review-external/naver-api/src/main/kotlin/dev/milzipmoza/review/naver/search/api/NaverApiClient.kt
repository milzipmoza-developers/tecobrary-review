package dev.milzipmoza.review.naver.search.api

import dev.milzipmoza.review.naver.config.NaverApiConfiguration
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam

@FeignClient(
        value = "naver-api",
        url = "https://openapi.naver.com/",
        configuration = [NaverApiConfiguration::class]
)
interface NaverApiClient {

    @GetMapping("/v1/search/book.json")
    fun searchBooks(@RequestParam("query") query: String,
                    @RequestParam("display") display: Int,
                    @RequestParam("start") start: Int): NaverApiResponse<NaverSearchBookItemDto>
}