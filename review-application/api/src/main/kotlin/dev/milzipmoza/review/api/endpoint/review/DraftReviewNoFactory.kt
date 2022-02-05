package dev.milzipmoza.review.api.endpoint.review

import java.util.UUID

object DraftReviewNoFactory {

    fun create(): String {
        return UUID.randomUUID().toString()
    }
}