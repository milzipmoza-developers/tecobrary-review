package dev.milzipmoza.review.api.endpoint.timeline

data class TimelineContentDto(
        val member: TimelineContentMemberDto,
        val book: TimelineContentBookDto,
        val review: TimelineContentReviewDto
)

data class TimelineContentMemberDto(
        val no: String,
        val name: String,
        val profileImageUrl: String,
)

data class TimelineContentBookDto(
        val isbn: String,
        val title: String,
        val imageUrl: String,
        val tags: List<TimelineContentTagDto>
)

data class TimelineContentTagDto(
        val name: String,
        val colorCode: String
)

data class TimelineContentReviewDto(
        val range: String,
        val content: String,
        val informative: String,
        val readMore: String? = null,
        val selectables: List<String>
)
