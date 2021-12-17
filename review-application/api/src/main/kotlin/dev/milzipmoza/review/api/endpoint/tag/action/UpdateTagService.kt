package dev.milzipmoza.review.api.endpoint.tag.action

import dev.milzipmoza.review.annotation.ApplicationService
import dev.milzipmoza.review.domain.tag.TagOperation
import dev.milzipmoza.review.domain.tag.Tags
import dev.milzipmoza.review.domain.tag.model.TagUpdate

@ApplicationService
class UpdateTagService(
        private val tags: Tags,
        private val tagOperation: TagOperation
) {

    fun doUpdate(no: String, update: UpdateTagDto): Boolean {
        val tag = tags.findBy(no)

        val command = TagUpdate(tag)

        val updatedTag = command.doUpdate(
                colorCode = update.colorCode,
                description = update.description
        )

        return tagOperation.update(no, updatedTag)
    }
}
