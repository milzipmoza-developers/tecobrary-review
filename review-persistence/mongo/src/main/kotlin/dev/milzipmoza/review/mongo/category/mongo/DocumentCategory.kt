package dev.milzipmoza.review.mongo.category.mongo

import org.bson.types.ObjectId
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.index.Indexed
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "categories")
data class DocumentCategory(
        @Id val id: ObjectId = ObjectId.get(),
        @Indexed(unique = true) val name: String,
        val description: String,
        val image: DocumentCategoryImage
)