package dev.milzipmoza.review.domain.mark

import dev.milzipmoza.review.domain.Entity

class Mark(
        val no: String = "",
        val type: MarkType,
        val marked: Boolean = true,
        val member: MarkMember,
        val book: MarkBook
) : Entity<Mark> {

    override fun getId() = when(no.isNotBlank()) {
        true -> no
        false -> throw MarkOperationException("비교가 불가능합니다.")
    }

    fun unmark() = Mark(
          no = this.no,
          type = this.type,
          marked = false,
          member = this.member,
          book = this.book
    )
}