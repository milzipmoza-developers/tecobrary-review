package dev.milzipmoza.review.api.endpoint.tag.search

data class TagSearchRequest(
        val bookNo: String?,
        val tagName: String?
)