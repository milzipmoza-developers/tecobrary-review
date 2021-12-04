package dev.milzipmoza.review.naver.search.api

data class NaverApiResponse<T>(
        val total: Long = 0,
        val start: Int = 1,
        val display: Int = 10,
        val lastBuildDate: String = "",
        val items: List<T> = listOf()
)
