package dev.milzipmoza.review.domain.review.model

class EnrolledReviews(
        private val reviews: List<Review>
) {

    fun availableRanges(): List<ReviewReadRange> {
        val enrolledRanges = reviews.map { it.range }.toSet()

        val highestRange = enrolledRanges.maxByOrNull { it.displayOrder }
                ?: return ReviewReadRange.values().toList()

        return ReviewReadRange.values().filter { it.displayOrder > highestRange.displayOrder }
    }

    fun isNotAvailableToEnroll(range: ReviewReadRange): Boolean {
        val availableRanges = availableRanges()

        return !availableRanges.contains(range)
    }
}