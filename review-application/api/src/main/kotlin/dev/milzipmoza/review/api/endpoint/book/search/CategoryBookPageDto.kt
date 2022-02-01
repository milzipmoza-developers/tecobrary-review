package dev.milzipmoza.review.api.endpoint.book.search

import dev.milzipmoza.review.api.PageData
import dev.milzipmoza.review.domain.category.model.Category

data class CategoryBookPageDto(
        val category: CategoryBookCategoryDto,
        val pageData: PageData<BookDto>
)

data class CategoryBookCategoryDto(
        val no: String,
        val name: String,
        val imageUrl: String
) {
    companion object {
        fun of(category: Category) = CategoryBookCategoryDto(
                no = category.no,
                name = category.name.name,
                imageUrl = category.fullImageUrl
        )
    }
}