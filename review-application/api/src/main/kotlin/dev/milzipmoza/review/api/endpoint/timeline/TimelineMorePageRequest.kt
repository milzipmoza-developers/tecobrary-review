package dev.milzipmoza.review.api.endpoint.timeline

data class TimelineMorePageRequest(
        val lastAttribute: String? = null,
        val size: Int = 10
)