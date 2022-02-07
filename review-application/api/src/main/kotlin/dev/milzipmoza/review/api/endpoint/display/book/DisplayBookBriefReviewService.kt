package dev.milzipmoza.review.api.endpoint.display.book

import dev.milzipmoza.review.annotation.ApplicationService
import dev.milzipmoza.review.domain.review.ReviewAggregation
import dev.milzipmoza.review.domain.review.model.ReviewReadRange

@ApplicationService
class DisplayBookBriefReviewService(
        private val reviewAggregation: ReviewAggregation
) {

    fun brief(isbn: String): DisplayBookBriefReviewDto {

        val briefKeywords = reviewAggregation.getBriefKeywords(isbn).sorted()
        val reviews = reviewAggregation.getBriefReviews(isbn).sorted()

        val content = briefKeywords.content.map { DisplayReviewKeywordDto(it.content.name, it.count) }
        val informative = briefKeywords.informative.map { DisplayReviewKeywordDto(it.content.name, it.count) }
        val readMore = briefKeywords.readMore.map { DisplayReviewKeywordDto(it.content.name, it.count) }
        val selectables = reviews.selectables.map { DisplayReviewKeywordDto(it.selectable.name, it.count) }

        return DisplayBookBriefReviewDto(
                content = content,
                informative = informative,
                readMore = readMore,
                selectables = selectables
        )
    }
}