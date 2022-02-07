package dev.milzipmoza.review.api.endpoint.display.book

data class DisplayBookBriefReviewDto(
        val readRange: DisplayReviewReadRangeDto,
        val content: List<DisplayReviewKeywordDto>,
        val informative: List<DisplayReviewKeywordDto>,
        val readMore: List<DisplayReviewKeywordDto>,
        val selectables: List<DisplayReviewKeywordDto>
)

data class DisplayReviewReadRangeDto(
        val name: String,
        val displayName: String
)

data class DisplayReviewKeywordDto(
        val keyword: String,
        val count: Int
)
