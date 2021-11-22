package dev.milzipmoza.review.mongo.category.mongo

import dev.milzipmoza.review.domain.category.model.Category
import dev.milzipmoza.review.domain.category.model.color.CategoryColor
import dev.milzipmoza.review.domain.category.model.description.CategoryDescription
import dev.milzipmoza.review.domain.category.model.name.CategoryName
import dev.milzipmoza.review.domain.category.model.url.CategoryImageUrl
import org.bson.types.ObjectId

object DocumentCategoryMapper {

    fun map(document: DocumentCategory): Category {
        return Category(
                no = document.id.toHexString(),
                color = CategoryColor(document.colorCode),
                name = CategoryName(document.name),
                description = CategoryDescription(document.description),
                imageUrl = CategoryImageUrl(
                        host = document.image.host,
                        path = document.image.path
                )
        )
    }

    fun map(model: Category): DocumentCategory {
        return DocumentCategory(
                colorCode = model.color.code,
                name = model.name.name,
                description = model.description.description,
                image = DocumentCategoryImage(
                        host = model.imageUrl.host,
                        path = model.imageUrl.path
                )
        )
    }

    fun map(id: ObjectId, model: Category): DocumentCategory {
        return DocumentCategory(
                id = id,
                colorCode = model.color.code,
                name = model.name.name,
                description = model.description.description,
                image = DocumentCategoryImage(
                        host = model.imageUrl.host,
                        path = model.imageUrl.path
                )
        )
    }
}