package dev.milzipmoza.review.domain.search.model

import dev.milzipmoza.review.domain.Entity
import dev.milzipmoza.review.domain.search.model.image.SearchBookImage
import java.time.LocalDate

class SearchBook(
        val isbn: String,
        val title: String,
        val publisher: String,
        val author: String,
        val publishDate: LocalDate,
        private val image: SearchBookImage,
        val description: String
) : Entity<SearchBook> {

    override fun getId() = isbn

    val imageUrl: String
        get() = image.toUrl()
}