package dev.milzipmoza.review.mongo.mark.mongo

import org.bson.types.ObjectId
import java.time.LocalDateTime

data class DocumentBookmark(
        val id: ObjectId = ObjectId.get(),
        val member: DocumentMarkMember,
        val book: DocumentMarkBook,
        val txDateTime: LocalDateTime
)