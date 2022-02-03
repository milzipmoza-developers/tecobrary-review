package dev.milzipmoza.review.mongo.book.mongo

import java.time.LocalDate

data class DocumentBookDetail(
        val title: String,
        val publisher: String,
        val author: String,
        val image: DocumentBookDetailImage,
        val locale: String,
        val publishDate: LocalDate,
        val description: String
)

class DocumentBookDetailImage(
        val host: String,
        val path: String
)