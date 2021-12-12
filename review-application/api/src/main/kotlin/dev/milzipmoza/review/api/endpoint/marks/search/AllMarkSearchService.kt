package dev.milzipmoza.review.api.endpoint.marks.search

import dev.milzipmoza.review.annotation.ApplicationService
import dev.milzipmoza.review.api.PageData
import dev.milzipmoza.review.domain.PageQuery
import dev.milzipmoza.review.domain.mark.MarkBook
import dev.milzipmoza.review.domain.mark.MarkMember
import dev.milzipmoza.review.domain.mark.Marks

@ApplicationService
class AllMarkSearchService(
        private val marks: Marks
) {

    fun search(page: Int, size: Int, bookNo: String?, memberNo: String?): PageData<MarkDto> {
        val pageQuery = PageQuery(page, size)
        val pageEntities = when {
            bookNo == null && memberNo != null -> marks.findAllBy(MarkMember(memberNo), pageQuery)
            bookNo != null && memberNo == null -> marks.findAllBy(MarkBook(bookNo), pageQuery)
            bookNo != null && memberNo != null -> marks.findAllBy(MarkMember(memberNo), MarkBook(bookNo), pageQuery)
            else -> marks.findAllBy(pageQuery)
        }
        return PageData.of(pageEntities, MarkDto.Companion::of)
    }
}