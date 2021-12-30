package dev.milzipmoza.review.naver.search.domain

import dev.milzipmoza.review.domain.PageEntities
import dev.milzipmoza.review.domain.PageQuery
import dev.milzipmoza.review.domain.search.SearchBooks
import dev.milzipmoza.review.domain.search.model.SearchBook
import dev.milzipmoza.review.naver.search.api.NaverApiClient
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Repository

@Repository
class NaverSearchBooks(
        private val naverApiClient: NaverApiClient
) : SearchBooks {

    override fun findAllBy(keyword: String, pageQuery: PageQuery): PageEntities<SearchBook> {
        val naverSearchBookPageQuery = NaverSearchBookPageQuery.of(pageQuery)

        val response = naverApiClient.searchBooks(
                query = keyword,
                display = naverSearchBookPageQuery.display,
                start = naverSearchBookPageQuery.start
        )

        return PageEntities(
                total = response.total,
                size = response.display,
                isFirst = false,
                isLast = false,
                items = response.items
                        .filter { it.image.isNotBlank() }
                        .map { NaverSearchBookMapper.map(it) }
                        .toList()
        )
    }
}