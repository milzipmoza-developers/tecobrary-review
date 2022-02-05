package dev.milzipmoza.review.api.endpoint.review

import dev.milzipmoza.review.annotation.ApplicationService
import dev.milzipmoza.review.api.ClientMember
import dev.milzipmoza.review.domain.review.Reviews
import dev.milzipmoza.review.domain.review.model.ReviewReadRange


@ApplicationService
class ReviewSelectableRangeService(
        private val reviews: Reviews
) {
    fun getAvailableRanges(clientMember: ClientMember, isbn: String): ReviewSelectableRangeDto {
        val allRanges = ReviewReadRange.values().toList()
        val availableRanges = when (clientMember) {
            is ClientMember.AuthenticatedMember -> {
                reviews.getAll(clientMember.memberNo, isbn).availableRanges()
            }
            else -> {
                ReviewReadRange.values().toList()
            }
        }
        val ranges = allRanges.map { ReviewRangeDto(it.displayOrder, it.displayName, it.name, !availableRanges.contains(it)) }

        return ReviewSelectableRangeDto(ranges)
    }
}
