package dev.milzipmoza.domain

data class PageEntities<T : Entity<T>>(
        val total: Long,
        val start: Long,
        val size: Long,
        val isFirst: Boolean,
        val isLast: Boolean,
        val items: List<T>
)