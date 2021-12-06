package dev.milzipmoza.review.mongo.mark.mongo

import org.bson.types.ObjectId
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.index.Indexed
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "marks")
data class DocumentMark(
        @Id val id: ObjectId = ObjectId.get(),
        val type: String,
        val member: DocumentMarkMember,
        val book: DocumentMarkBook,
        val markedObjectId: ObjectId
)

data class DocumentMarkMember(
        @Indexed(unique = true) val no: String
)

data class DocumentMarkBook(
        @Indexed(unique = true) val no: String
)