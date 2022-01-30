package dev.milzipmoza.review.domain.mark

import dev.milzipmoza.review.domain.PageEntities
import dev.milzipmoza.review.domain.PageQuery
import dev.milzipmoza.review.domain.category.model.Category

interface Marks {

    fun findBy(member: MarkMember, book: MarkBook, type: MarkType): Mark?

    fun findAllBy(member: MarkMember, book: MarkBook): List<Mark>

    fun findAllBy(pageQuery: PageQuery): PageEntities<Mark>

    fun findAllBy(member: MarkMember, pageQuery: PageQuery): PageEntities<Mark>

    fun findAllBy(book: MarkBook, pageQuery: PageQuery): PageEntities<Mark>

    fun findAllBy(member: MarkMember, book: MarkBook, pageQuery: PageQuery): PageEntities<Mark>

    fun count(book: MarkBook, type: MarkType): Long

    fun isMarked(member: MarkMember, book: MarkBook, type: MarkType): Boolean
}