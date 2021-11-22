package dev.milzipmoza.review.mongo.category.mongo

import org.bson.types.ObjectId
import org.springframework.data.annotation.Id

data class DocumentCategory(
        @Id val id: ObjectId = ObjectId.get(),
        val colorCode: String,
        val name: String,
        val description: String,
        val image: DocumentCategoryImage
)