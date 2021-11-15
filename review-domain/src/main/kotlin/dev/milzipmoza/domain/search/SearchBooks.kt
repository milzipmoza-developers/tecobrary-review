package dev.milzipmoza.domain.search

import dev.milzipmoza.domain.PageEntities
import dev.milzipmoza.domain.PageQuery
import dev.milzipmoza.domain.search.model.SearchBook

interface SearchBooks {

    fun findAllBy(keyword: String, pageQuery: PageQuery): PageEntities<SearchBook>
}