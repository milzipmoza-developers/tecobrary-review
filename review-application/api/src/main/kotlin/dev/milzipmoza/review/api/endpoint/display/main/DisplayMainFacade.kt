package dev.milzipmoza.review.api.endpoint.display.main

import dev.milzipmoza.review.annotation.ApplicationService
import dev.milzipmoza.review.api.endpoint.display.main.dto.DisplayMainDto
import dev.milzipmoza.review.api.endpoint.display.main.dto.DisplayMainInterestBookSectionDto
import dev.milzipmoza.review.api.endpoint.display.main.dto.DisplayMainNewBookSectionDto
import java.time.LocalDate

@ApplicationService
class DisplayMainFacade(
        private val displayMainCategoryService: DisplayMainCategoryService
) {

    fun get(): DisplayMainDto {
        return DisplayMainDto(
                news = DisplayMainNewBookSectionDto(
                        updateDate = LocalDate.now(),
                        books = listOf()
                ),
                interests = DisplayMainInterestBookSectionDto(
                        updateDate = LocalDate.now(),
                        books = listOf()
                ),
                categories = displayMainCategoryService.getRandomCategories()

        )
    }

    companion object {
        private val BASE_DATE = LocalDate.now().minusMonths(6)
    }
}
