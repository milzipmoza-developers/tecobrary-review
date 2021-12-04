package dev.milzipmoza.review.naver.search.api

data class NaverSearchBookItemDto(
        val title: String = "",
        val link: String = "",
        val image: String = "",
        val price: Int = 0,
        val discount: Int = 0,
        val author: String = "",
        val publisher: String = "",
        val pubdate: String = "",
        val isbn: String = "",
        val description: String = ""
)
