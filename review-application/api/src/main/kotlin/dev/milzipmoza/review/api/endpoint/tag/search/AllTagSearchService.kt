package dev.milzipmoza.review.api.endpoint.tag.search

import dev.milzipmoza.review.annotation.ApplicationService
import dev.milzipmoza.review.api.PageData
import dev.milzipmoza.review.domain.PageQuery
import dev.milzipmoza.review.domain.tag.Tags
import dev.milzipmoza.review.domain.tag.model.book.TagBook
import dev.milzipmoza.review.domain.tag.model.name.TagName

@ApplicationService
class AllTagSearchService(
        private val tags: Tags
) {
    fun search(page: Int, size: Int, bookNo: String?, tagName: String?): PageData<TagDto> {
        if (tagName != null) {
            return when (bookNo) {
                null -> tags.findBy(TagName(tagName))
                else -> tags.findBy(TagName(tagName), TagBook(bookNo))
            }.run { PageData.of(this, TagDto::of) }
        }

        val pageQuery = PageQuery(page, size)

        return when (bookNo) {
            null -> tags.findAllBy(pageQuery)
            else -> tags.findAllBy(TagBook(bookNo), pageQuery)
        }.run { PageData.of(this, TagDto::of) }
    }
}