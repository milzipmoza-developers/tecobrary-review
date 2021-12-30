package dev.milzipmoza.review.api.endpoint.book.action

import dev.milzipmoza.review.domain.book.model.category.BookCategory
import dev.milzipmoza.review.domain.book.model.category.BookCategoryImageUrl

data class BookAddCategoryDto(
        val no: String = "",
        val name: String = "",
        val imageUrl: String = ""
)

object BookAddCategoryDtoMapper {

    fun map(dto: BookAddCategoryDto): BookCategory.EnrolledBookCategory {
        return BookCategory.EnrolledBookCategory(
                no = dto.no,
                name = dto.name,
                imageUrl = BookCategoryImageUrl(dto.imageUrl)
        )
    }
}