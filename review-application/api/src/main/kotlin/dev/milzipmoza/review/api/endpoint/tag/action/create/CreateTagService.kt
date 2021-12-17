package dev.milzipmoza.review.api.endpoint.tag.action.create

import dev.milzipmoza.review.annotation.ApplicationService
import dev.milzipmoza.review.domain.tag.TagOperation
import dev.milzipmoza.review.domain.tag.model.Tag
import dev.milzipmoza.review.domain.tag.model.color.TagColor
import dev.milzipmoza.review.domain.tag.model.description.TagDescription
import dev.milzipmoza.review.domain.tag.model.name.TagName

@ApplicationService
class CreateTagService(
        private val tagOperation: TagOperation
) {

    fun doCreate(tagDto: CreateTagDto): Boolean {
        val newTag = Tag(
                color = TagColor(tagDto.colorCode),
                name = TagName(tagDto.name),
                description = TagDescription(tagDto.description)
        )
        return tagOperation.save(newTag)
    }
}