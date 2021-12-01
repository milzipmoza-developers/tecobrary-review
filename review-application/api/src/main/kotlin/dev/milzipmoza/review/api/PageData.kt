package dev.milzipmoza.review.api

import dev.milzipmoza.review.domain.Entity
import dev.milzipmoza.review.domain.PageEntities

data class PageData<R>(
        val total: Long,
        val size: Int,
        val isFirst: Boolean,
        val isLast: Boolean,
        val items: List<R>
) {
    companion object {
        fun <T : Entity<T>, R> of(pageEntities: PageEntities<T>, mapper: (entity: T) -> R) : PageData<R> {
            return PageData(
                    total = pageEntities.total,
                    size = pageEntities.size,
                    isFirst = pageEntities.isFirst,
                    isLast = pageEntities.isLast,
                    items = pageEntities.items
                            .map { mapper(it) }
                            .toList()
            )
        }
    }
}