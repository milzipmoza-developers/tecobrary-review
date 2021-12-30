package dev.milzipmoza.review.naver.search.domain

import dev.milzipmoza.review.domain.PageQuery

data class NaverSearchBookPageQuery(
        val start: Int,
        val display: Int
) {
    companion object {
        fun of(pageQuery: PageQuery) = NaverSearchBookPageQuery(
                start = ((pageQuery.page - 1) * pageQuery.size) + 1,
                display = pageQuery.size
        )
    }
}
