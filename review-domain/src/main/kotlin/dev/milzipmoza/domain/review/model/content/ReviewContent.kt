package dev.milzipmoza.domain.review.model.content

interface ReviewContent {

    fun editContent(content: String): ReviewContent

    fun fullContent(): String

    fun summarizeLong(): String

    fun extractInline(): String
}