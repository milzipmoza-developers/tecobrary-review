package dev.milzipmoza.review.domain.tag

import dev.milzipmoza.review.domain.PageEntities
import dev.milzipmoza.review.domain.PageQuery
import dev.milzipmoza.review.domain.tag.model.Tag
import dev.milzipmoza.review.domain.tag.model.name.TagName

interface Tags {

    fun findBy(no: String): Tag

    fun findBy(tagName: TagName): Tag

    fun findAllBy(pageQuery: PageQuery): PageEntities<Tag>
}