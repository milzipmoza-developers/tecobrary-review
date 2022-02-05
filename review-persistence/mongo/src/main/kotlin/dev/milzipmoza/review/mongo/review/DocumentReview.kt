package dev.milzipmoza.review.mongo.review

import java.time.LocalDate
import org.bson.types.ObjectId
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "reviews")
data class DocumentReview(
        @Id val id: ObjectId = ObjectId(),
        val member: DocumentReviewMember,
        val book: DocumentReviewBook,
        val range: String,
        val keyword: DocumentReviewKeyword,
        val type: DocumentReviewType = DocumentReviewType.SIMPLE,
        val createdDate: LocalDate = LocalDate.now()
)

data class DocumentReviewMember(
        val no: String
)

data class DocumentReviewBook(
        val isbn: String,
        val title: String
)

data class DocumentReviewKeyword(
        val content: String,
        val informative: String,
        val readMore: String?,
        val selectables: Set<String>
)

enum class DocumentReviewType {
    SIMPLE
}