package dev.milzipmoza.review.mongo.tag.domain

import dev.milzipmoza.review.domain.tag.model.Tag
import dev.milzipmoza.review.domain.tag.model.color.TagColor
import dev.milzipmoza.review.domain.tag.model.description.TagDescription
import dev.milzipmoza.review.domain.tag.model.name.TagName
import dev.milzipmoza.review.mongo.tag.mongo.DocumentTag

object DocumentTagMapper {

    fun map(documentTag: DocumentTag): Tag {
        return Tag(
                no = documentTag.id.toHexString(),
                color = TagColor(code = documentTag.colorCode),
                name = TagName(name = documentTag.name),
                description = TagDescription(description = documentTag.description),
        )
    }
}