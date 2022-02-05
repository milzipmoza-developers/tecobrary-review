package dev.milzipmoza.review.domain.review

import dev.milzipmoza.review.domain.review.model.DraftReview

interface DraftReviews {

    fun get(no: String): DraftReview
}