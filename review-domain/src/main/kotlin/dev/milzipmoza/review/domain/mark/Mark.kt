package dev.milzipmoza.review.domain.mark

import dev.milzipmoza.review.domain.Entity

class Mark(
        val no: String,
        val type: MarkType,
        val marked: Boolean,
        val member: MarkMember
) : Entity<Mark> {

    override fun getId() = no
}