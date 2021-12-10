package dev.milzipmoza.review.domain.mark

interface MarkOperation {

    fun create(mark: Mark): Boolean

    fun update(no: String, mark: Mark): Boolean
}