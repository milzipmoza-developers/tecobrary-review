package dev.milzipmoza.review.api.endpoint.tag.search

import dev.milzipmoza.review.annotation.ApplicationService
import dev.milzipmoza.review.api.PageData
import dev.milzipmoza.review.domain.PageQuery
import dev.milzipmoza.review.domain.tag.Tags
import dev.milzipmoza.review.domain.tag.model.name.TagName

@ApplicationService
class AllTagSearchService(
        private val tags: Tags
) {
    fun search(page: Int, size: Int, tagName: String?): PageData<TagDto> {
        if (tagName.isNullOrEmpty()) {
            val pageQuery = PageQuery(page, size)

            return tags.findAllBy(pageQuery)
                    .run { PageData.of(this, TagDto::of) }
        }

        return tags.findBy(TagName(tagName))
                .run { PageData.of(this, TagDto::of) }
    }
}