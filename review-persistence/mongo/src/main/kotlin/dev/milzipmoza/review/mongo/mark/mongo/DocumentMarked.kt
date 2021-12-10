package dev.milzipmoza.review.mongo.mark.mongo

import org.bson.types.ObjectId
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.time.LocalDateTime

@Document(collection = "marked_marks")
data class DocumentMarked(
        @Id val id: ObjectId = ObjectId.get(),
        var marked: Boolean,
        var txDateTime: LocalDateTime
)