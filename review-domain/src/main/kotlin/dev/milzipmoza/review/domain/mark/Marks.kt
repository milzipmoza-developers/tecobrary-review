package dev.milzipmoza.review.domain.mark

interface Marks {

    fun findByMemberAndBookAndType(member: MarkMember, book: MarkBook, type: MarkType): Mark?

    fun findAllByMemberAndBook(member: MarkMember, book: MarkBook): List<Mark>
}