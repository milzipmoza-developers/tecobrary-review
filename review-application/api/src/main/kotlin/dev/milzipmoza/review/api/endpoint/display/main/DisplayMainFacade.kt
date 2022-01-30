package dev.milzipmoza.review.api.endpoint.display.main

import dev.milzipmoza.review.annotation.ApplicationService
import dev.milzipmoza.review.api.endpoint.display.main.dto.DisplayMainDto
import dev.milzipmoza.review.api.endpoint.display.main.dto.DisplayMainInterestBookSectionDto
import java.time.LocalDate

@ApplicationService
class DisplayMainFacade(
        private val displayMainNewBookService: DisplayMainNewBookService,
        private val displayMainCategoryService: DisplayMainCategoryService
) {

    fun get(): DisplayMainDto {
        return DisplayMainDto(
                news = displayMainNewBookService.getRecentPublished(),
                interests = DisplayMainInterestBookSectionDto(
                        updateDate = LocalDate.now(),
                        books = listOf()
                ),
                categories = displayMainCategoryService.getRandomCategories()

        )
    }
}
