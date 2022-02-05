package dev.milzipmoza.review.mongo.book.mongo

import org.bson.types.ObjectId
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.index.Indexed
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "books")
data class DocumentBook(
        @Id val id: ObjectId = ObjectId(),
        @Indexed(unique = true) val isbn: String,
        var category: DocumentBookCategory?,
        var tags: MutableSet<DocumentBookTag>,
        val detail: DocumentBookDetail,
)

data class DocumentBookCategory(
        val no: String,
        val name: String,
        val image: DocumentBookCategoryImage
)

data class DocumentBookCategoryImage(
        val host: String,
        val path: String
)