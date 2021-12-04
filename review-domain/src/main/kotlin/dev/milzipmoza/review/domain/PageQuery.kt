package dev.milzipmoza.review.domain

data class PageQuery(
        val page: Int,
        val size: Int
) {
    val start: Int
        get() = page * size + 1
}