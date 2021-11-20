package dev.milzipmoza.review.domain.search

import dev.milzipmoza.review.domain.PageEntities
import dev.milzipmoza.review.domain.PageQuery
import dev.milzipmoza.review.domain.search.model.SearchBook

interface SearchBooks {

    fun findAllBy(keyword: String, pageQuery: PageQuery): PageEntities<SearchBook>
}