package dev.milzipmoza.review.domain.review

import dev.milzipmoza.review.domain.review.model.DraftReview

interface DraftReviewOperation {

    fun saveOrUpdate(draftReview: DraftReview): Boolean
}