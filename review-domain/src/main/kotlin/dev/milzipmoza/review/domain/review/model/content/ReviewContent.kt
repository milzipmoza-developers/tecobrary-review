package dev.milzipmoza.review.domain.review.model.content

import dev.milzipmoza.review.domain.review.model.ReviewDisplay

sealed interface ReviewContent {

    var display: ReviewDisplay

    fun changeDisplay(display: ReviewDisplay): ReviewContent

    fun editContent(content: String): ReviewContent

    fun fullContent(): String

    fun summarizeLong(): String

    fun extractInline(): String
}