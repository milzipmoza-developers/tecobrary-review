package dev.milzipmoza.review.api.endpoint.search

import dev.milzipmoza.review.api.PageRequest

data class SearchBookPageRequest(
        val keyword: String,
        override val page: Int = 0,
        override val size: Int = 10
) : PageRequest(page, size)