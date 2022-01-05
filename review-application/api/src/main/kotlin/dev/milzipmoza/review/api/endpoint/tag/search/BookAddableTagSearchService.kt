package dev.milzipmoza.review.api.endpoint.tag.search

import dev.milzipmoza.review.annotation.ApplicationService
import dev.milzipmoza.review.api.PageData
import dev.milzipmoza.review.domain.PageQuery
import dev.milzipmoza.review.domain.book.Books
import dev.milzipmoza.review.domain.tag.Tags
import dev.milzipmoza.review.domain.tag.model.name.TagName

@ApplicationService
class BookAddableTagSearchService(
        private val books: Books,
        private val tags: Tags
) {

    fun search(page: Int, size: Int, tagName: String?, isbn: String): PageData<TagDto> {
        val book = books.findBy(isbn)

        val bookTags = book.tags.map { it.no }

        if (tagName.isNullOrEmpty()) {
            val pageQuery = PageQuery(page, size)

            return tags.findAllBy(pageQuery, bookTags)
                    .run { PageData.of(this, TagDto::of) }
        }

        return tags.findBy(TagName(tagName), bookTags)
                .run { PageData.of(this, TagDto::of) }
    }
}
