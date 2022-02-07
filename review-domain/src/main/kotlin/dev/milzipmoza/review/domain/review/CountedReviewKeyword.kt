package dev.milzipmoza.review.domain.review

import dev.milzipmoza.review.domain.review.model.ReviewKeyword

class CountedReviewKeywords(
        val content: List<CountedReviewKeyword<ReviewKeyword.Content>>,
        val informative: List<CountedReviewKeyword<ReviewKeyword.Informative>>,
        val readMore: List<CountedReviewKeyword<ReviewKeyword.ReadMore>>
) {

    fun sorted() =
            CountedReviewKeywords(
                    content = content.sortedByDescending { it.count },
                    informative = informative.sortedByDescending { it.count },
                    readMore = readMore.sortedByDescending { it.count }
            )
}

class CountedReviewKeyword<T>(
        val content: T,
        val count: Int
)