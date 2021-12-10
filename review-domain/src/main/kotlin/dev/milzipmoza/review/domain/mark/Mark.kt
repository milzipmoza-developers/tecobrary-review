package dev.milzipmoza.review.domain.mark

import dev.milzipmoza.review.domain.Entity

class Mark(
        val no: String = "",
        val type: MarkType,
        val marked: Boolean = true,
        val member: MarkMember,
        val book: MarkBook
) : Entity<Mark> {

    val unmarked = !marked

    constructor(type: String, memberNo: String, bookNo: String) : this(type = MarkType.valueOf(type), member = MarkMember(memberNo), book = MarkBook(bookNo))

    override fun getId() = when (no.isNotBlank()) {
        true -> no
        false -> throw MarkOperationException("비교가 불가능합니다.")
    }

    fun mark() = Mark(
            no = this.no,
            type = this.type,
            marked = true,
            member = this.member,
            book = this.book
    )

    fun unmark() = Mark(
            no = this.no,
            type = this.type,
            marked = false,
            member = this.member,
            book = this.book
    )
}