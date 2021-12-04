package dev.milzipmoza.review.naver.search.domain

import dev.milzipmoza.review.domain.search.model.SearchBook
import dev.milzipmoza.review.domain.search.model.description.SearchBookDescription
import dev.milzipmoza.review.domain.search.model.image.SearchBookImage
import dev.milzipmoza.review.naver.search.api.NaverSearchBookItemDto
import java.time.LocalDate

object NaverSearchBookMapper {

    fun map(item: NaverSearchBookItemDto): SearchBook {
        return SearchBook(
                isbn = item.isbn,
                title = item.title,
                publisher = item.publisher,
                author = item.author,
                publishDate = LocalDate.now(),
                image = SearchBookImage(item.image),
                description = SearchBookDescription(content = item.description)
        )
    }
}