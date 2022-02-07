package dev.milzipmoza.review.mongo.review

import dev.milzipmoza.review.domain.review.CountedReview
import dev.milzipmoza.review.domain.review.CountedReviewKeyword
import dev.milzipmoza.review.domain.review.CountedReviewKeywords
import dev.milzipmoza.review.domain.review.CountedReviewSelectable
import dev.milzipmoza.review.domain.review.CountedReviewSelectables
import dev.milzipmoza.review.domain.review.ReviewAggregation
import dev.milzipmoza.review.domain.review.model.ReviewKeyword
import dev.milzipmoza.review.domain.review.model.ReviewReadRange
import org.springframework.stereotype.Repository

@Repository
class MongoReviewAggregation(
        private val mongoReviewAggregator: MongoReviewAggregator
) : ReviewAggregation {

    override fun getTop(top: Long): List<CountedReview> {
        val documents = mongoReviewAggregator.findTop(top)
        return documents.map { CountedReview(it.bookIsbn, it.count) }
    }

    override fun getBriefKeywords(isbn: String, range: ReviewReadRange): CountedReviewKeywords {
        val aggregationResults = mongoReviewAggregator.getBriefKeywords(isbn, range.name)

        val documents = aggregationResults.mappedResults

        val content = documents.filter { it.type == "content" }
                .map { CountedReviewKeyword(ReviewKeyword.Content.valueOf(it.value), it.count) }
                .toList()

        val informative = documents.filter { it.type == "informative" }
                .map { CountedReviewKeyword(ReviewKeyword.Informative.valueOf(it.value), it.count) }
                .toList()

        val readMore = documents.filter { it.type == "readMore" }
                .map { CountedReviewKeyword(ReviewKeyword.ReadMore.valueOf(it.value), it.count) }
                .toList()

        return CountedReviewKeywords(
                content = content,
                informative = informative,
                readMore = readMore
        )
    }

    override fun getBriefReviews(isbn: String, range: ReviewReadRange): CountedReviewSelectables {
        val aggregationResults = mongoReviewAggregator.getBriefReviews(isbn, range.name)

        val documents = aggregationResults.mappedResults

        val selectables = documents.map { CountedReviewSelectable(ReviewKeyword.Selectable.valueOf(it.selectable), it.count) }

        return CountedReviewSelectables(selectables)
    }
}