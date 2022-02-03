package dev.milzipmoza.review.domain.book

import dev.milzipmoza.review.domain.PageEntities
import dev.milzipmoza.review.domain.PageQuery
import dev.milzipmoza.review.domain.book.model.Book

interface Books {

    fun findBy(isbn: String): Book

    fun findBy(pageQuery: PageQuery): PageEntities<Book>

    fun findAllBy(categoryNo: String, pageQuery: PageQuery): PageEntities<Book>

    fun findAllIn(isbns: List<String>): List<Book>

    fun getRecentPublished(recentMonths: Long, pageQuery: PageQuery): PageEntities<Book>
}