package dev.milzipmoza.domain.search.model

import dev.milzipmoza.domain.Entity
import dev.milzipmoza.domain.search.model.description.SearchBookDescription
import dev.milzipmoza.domain.search.model.image.SearchBookImage
import java.time.LocalDate

class SearchBook(
        val no: String,
        val title: String,
        val publisher: String,
        val author: String,
        val publishDate: LocalDate,
        private val image: SearchBookImage,
        private val description: SearchBookDescription
) : Entity<SearchBook> {

    override fun getId() = no

    val imageUrl: String
        get() = image.toUrl()

    val descriptionContent: String
        get() = description.summarizeContent()
}