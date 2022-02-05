package dev.milzipmoza.review.api.endpoint.review

import dev.milzipmoza.review.annotation.ApplicationService
import dev.milzipmoza.review.api.ClientMember
import dev.milzipmoza.review.domain.review.DraftReviewOperation
import dev.milzipmoza.review.domain.review.DraftReviews
import dev.milzipmoza.review.domain.review.model.DraftReview
import dev.milzipmoza.review.domain.review.model.DraftReviewMember
import dev.milzipmoza.review.domain.review.model.ReviewReadRange


@ApplicationService
class SelectReviewRangeService(
        private val draftReviews: DraftReviews,
        private val draftReviewOperation: DraftReviewOperation
) {
    fun select(clientMember: ClientMember, rangeDto: SelectReviewRangeDto) =
            when (clientMember) {
                is ClientMember.UnknownMember -> {
                    SelectReviewRangeResultDto(false)
                }
                is ClientMember.UnauthenticatedMember,
                is ClientMember.AuthenticatedMember -> {
                    val selectedRange = ReviewReadRange.valueOf(rangeDto.selected)
                    val draftReview = draftReviews.get(rangeDto.draftReviewNo!!) as DraftReview.DraftReviewFirstStep

                    if (draftReview.isNotOwnedBy(DraftReviewMember(clientMember.memberNo, clientMember.deviceId!!))) {
                        throw IllegalArgumentException("현재 작성중인 리뷰가 아니예요")
                    }

                    val draftReviewSecondStep = draftReview.selectRange(selectedRange)
                    val draftSaved = draftReviewOperation.saveOrUpdate(draftReviewSecondStep)
                    SelectReviewRangeResultDto(draftSaved, draftReview.no)
                }
            }
}
