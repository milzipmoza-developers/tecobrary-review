package dev.milzipmoza.review.mongo.extension

import dev.milzipmoza.review.domain.PageQuery
import org.springframework.data.domain.PageRequest

object PageRequest {
    fun of(pageQuery: PageQuery) = PageRequest.of(pageQuery.page, pageQuery.size)
}