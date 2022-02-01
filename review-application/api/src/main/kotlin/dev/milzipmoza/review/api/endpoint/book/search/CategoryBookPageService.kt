package dev.milzipmoza.review.api.endpoint.book.search

import dev.milzipmoza.review.annotation.ApplicationService
import dev.milzipmoza.review.api.PageData
import dev.milzipmoza.review.domain.PageQuery
import dev.milzipmoza.review.domain.book.Books
import dev.milzipmoza.review.domain.category.Categories

@ApplicationService
class CategoryBookPageService(
        private val categories: Categories,
        private val books: Books
){

    fun findAll(page: Int, size: Int, categoryNo: String): CategoryBookPageDto {
        val category = categories.findBy(categoryNo)

        val pageQuery = PageQuery(page, size)
        val pageData = books.findAllBy(category.no, pageQuery)
                .run { PageData.of(this, BookDtoMapper::map) }
        return CategoryBookPageDto(
                category = CategoryBookCategoryDto.of(category),
                pageData = pageData
        )
    }

}
