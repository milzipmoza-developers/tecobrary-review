package dev.milzipmoza.review.domain.mark

import dev.milzipmoza.review.domain.Entity
import java.time.LocalDateTime

class Bookmark(
        val no: String = "",
        val member: MarkMember,
        val book: MarkBook,
        val markDateTime: LocalDateTime
) : Entity<Bookmark> {

    override fun getId() = when (no.isNotBlank()) {
        true -> no
        false -> throw MarkOperationException("비교가 불가능합니다.")
    }
}