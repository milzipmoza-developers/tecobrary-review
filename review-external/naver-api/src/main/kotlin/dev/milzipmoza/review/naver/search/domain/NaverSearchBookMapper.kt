package dev.milzipmoza.review.naver.search.domain

import dev.milzipmoza.review.domain.search.model.SearchBook
import dev.milzipmoza.review.domain.search.model.description.SearchBookDescription
import dev.milzipmoza.review.domain.search.model.image.SearchBookImage
import dev.milzipmoza.review.naver.extensions.removeHtmlTags
import dev.milzipmoza.review.naver.search.api.NaverSearchBookItemDto
import dev.milzipmoza.review.naver.search.parser.IsbnParser
import java.time.LocalDate
import java.time.format.DateTimeFormatter

object NaverSearchBookMapper {

    fun map(item: NaverSearchBookItemDto): SearchBook {
        return SearchBook(
                isbn = IsbnParser.parse(item.isbn.removeHtmlTags()),
                title = item.title.removeHtmlTags(),
                publisher = item.publisher.removeHtmlTags(),
                author = item.author.removeHtmlTags(),
                publishDate = item.pubdate.toLocalDate(),
                image = SearchBookImage(item.image),
                description = SearchBookDescription(content = item.description.removeHtmlTags())
        )
    }
}

private val formatter = DateTimeFormatter.ofPattern("yyyyMMdd")

private fun String.toLocalDate(): LocalDate {
    return LocalDate.parse(this, formatter)
}
