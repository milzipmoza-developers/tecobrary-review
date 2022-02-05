package dev.milzipmoza.review.mongo.review

import java.time.LocalDateTime
import org.bson.types.ObjectId
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.index.Indexed
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "draft_reviews")
data class DocumentDraftReview(
        @Id val id: ObjectId = ObjectId(),
        @Indexed(unique = true) val no: String,
        val member: DocumentDraftReviewMember,
        val book: DocumentDraftReviewBook,
        val range: String? = null,
        val keyword: DocumentDraftReviewKeyword? = null,
        val enrolled: Boolean,
        @Indexed(expireAfter = "3d") val createdDateTime: LocalDateTime = LocalDateTime.now(),
        val expiredDateTime: LocalDateTime = createdDateTime.plusDays(3),
)

data class DocumentDraftReviewMember(
        val no: String? = null,
        val deviceId: String
)

data class DocumentDraftReviewBook(
        val isbn: String,
        val title: String
)

data class DocumentDraftReviewKeyword(
        val content: String,
        val informative: String,
        val readMore: String?,
        val selectables: Set<String>
)