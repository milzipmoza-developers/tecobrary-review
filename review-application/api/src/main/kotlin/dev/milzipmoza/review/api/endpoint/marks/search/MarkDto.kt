package dev.milzipmoza.review.api.endpoint.marks.search

import dev.milzipmoza.review.domain.mark.Mark

data class MarkDto(
        val no: String,
        val type: String,
        val marked: Boolean,
        val memberNo: String,
        val bookNo: String
) {
    companion object {
        fun of(mark: Mark): MarkDto {
            return MarkDto(
                    no = mark.no,
                    type = mark.type.toString(),
                    marked = mark.marked,
                    memberNo = mark.member.no,
                    bookNo = mark.book.no
            )
        }
    }
}