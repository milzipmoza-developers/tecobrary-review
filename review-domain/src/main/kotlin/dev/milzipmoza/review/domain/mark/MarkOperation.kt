package dev.milzipmoza.review.domain.mark

interface MarkOperation {

    fun create(mark: Mark)

    fun update(no: String, mark: Mark)
}