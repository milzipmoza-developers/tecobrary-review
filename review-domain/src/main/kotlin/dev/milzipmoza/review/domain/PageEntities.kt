package dev.milzipmoza.review.domain

data class PageEntities<T : Entity<T>>(
        val total: Long,
        val size: Int,
        val isFirst: Boolean,
        val isLast: Boolean,
        val items: List<T>
)