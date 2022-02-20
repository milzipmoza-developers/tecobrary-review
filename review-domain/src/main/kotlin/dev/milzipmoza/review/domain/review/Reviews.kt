package dev.milzipmoza.review.domain.review

import dev.milzipmoza.review.domain.review.model.EnrolledReviews
import dev.milzipmoza.review.domain.review.model.Review

interface Reviews {

    fun getAll(memberNo: String, bookIsbn: String): EnrolledReviews

    fun count(bookIsbn: String): Long

    fun getRecent(size: Int, lastReviewNo: String?): List<Review>
}