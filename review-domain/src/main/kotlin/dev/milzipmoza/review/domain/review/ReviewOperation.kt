package dev.milzipmoza.review.domain.review

import dev.milzipmoza.review.domain.review.model.Review

interface ReviewOperation {

    fun save(review: Review): Boolean
}