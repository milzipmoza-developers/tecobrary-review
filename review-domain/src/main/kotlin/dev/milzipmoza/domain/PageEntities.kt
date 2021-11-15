package dev.milzipmoza.domain

data class PageEntities<T>(
        val total: Long,
        val start: Long,
        val size: Long,
        val items: List<T>
)