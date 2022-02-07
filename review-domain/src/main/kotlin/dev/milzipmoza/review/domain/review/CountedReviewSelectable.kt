package dev.milzipmoza.review.domain.review

import dev.milzipmoza.review.domain.review.model.ReviewKeyword

class CountedReviewSelectables(
        val selectables: List<CountedReviewSelectable>
) {
    fun sorted(): CountedReviewSelectables {
        val sortedCountDesc = selectables.sortedByDescending { it.count }
        return CountedReviewSelectables(sortedCountDesc)
    }
}

class CountedReviewSelectable(
        val selectable: ReviewKeyword.Selectable,
        val count: Int
)