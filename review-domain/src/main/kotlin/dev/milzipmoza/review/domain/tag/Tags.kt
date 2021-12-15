package dev.milzipmoza.review.domain.tag

import dev.milzipmoza.review.domain.PageEntities
import dev.milzipmoza.review.domain.PageQuery
import dev.milzipmoza.review.domain.tag.model.Tag
import dev.milzipmoza.review.domain.tag.model.book.TagBook
import dev.milzipmoza.review.domain.tag.model.name.TagName

interface Tags {

    fun findBy(tagName: TagName, tagBook: TagBook): Tag

    fun findBy(tagName: TagName): Tag

    fun findAllBy(tagBook: TagBook, pageQuery: PageQuery): PageEntities<Tag>

    fun findAllBy(pageQuery: PageQuery): PageEntities<Tag>
}