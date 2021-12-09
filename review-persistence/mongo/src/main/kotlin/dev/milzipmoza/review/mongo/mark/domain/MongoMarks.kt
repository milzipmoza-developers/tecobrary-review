package dev.milzipmoza.review.mongo.mark.domain

import dev.milzipmoza.review.domain.PageEntities
import dev.milzipmoza.review.domain.PageQuery
import dev.milzipmoza.review.domain.mark.*
import dev.milzipmoza.review.mongo.category.mongo.DocumentCategoryMapper
import dev.milzipmoza.review.mongo.mark.mongo.*
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Repository

@Repository
class MongoMarks(
        private val mongoMarkRepository: MongoMarkRepository,
        private val mongoMarkedRepository: MongoMarkedRepository
) : Marks {

    override fun findBy(member: MarkMember, book: MarkBook, type: MarkType): Mark? {
        val documentMark = mongoMarkRepository.findByMemberAndBookAndType(DocumentMarkMapper.map(member), DocumentMarkMapper.map(book), type.toString())
                ?: return null

        val documentMarked = mongoMarkedRepository.findById(documentMark.markedObjectId)
                .orElse(null)
                ?: return DocumentMarkMapper.map(documentMark)

        return DocumentMarkMapper.map(documentMark, documentMarked)
    }

    override fun findAllBy(member: MarkMember, book: MarkBook): List<Mark> {
        val documentMarks = mongoMarkRepository.findAllByMemberAndBook(DocumentMarkMapper.map(member), DocumentMarkMapper.map(book))

        val documentMarkeds = mongoMarkedRepository.findAllById(documentMarks.map { it.markedObjectId }).toList()

        return documentMarks.map { DocumentMarkMapper.map(it, documentMarkeds) }
    }

    override fun findAllBy(pageQuery: PageQuery): PageEntities<Mark> {
        val documents = mongoMarkRepository.findAll(PageRequest.of(pageQuery.page, pageQuery.size))

        return toPageEntities(documents)
    }

    override fun findAllBy(member: MarkMember, pageQuery: PageQuery): PageEntities<Mark> {
        val documents = mongoMarkRepository.findAllByMember(DocumentMarkMapper.map(member), PageRequest.of(pageQuery.page, pageQuery.size))

        return toPageEntities(documents)
    }

    override fun findAllBy(book: MarkBook, pageQuery: PageQuery): PageEntities<Mark> {
        val documents = mongoMarkRepository.findAllByBook(DocumentMarkMapper.map(book), PageRequest.of(pageQuery.page, pageQuery.size))

        return toPageEntities(documents)
    }

    private fun toPageEntities(documents: Page<DocumentMark>): PageEntities<Mark> {
        val markedIds = documents.content.map { it -> it.markedObjectId }

        val markeds = mongoMarkedRepository.findAllById(markedIds)

        return PageEntities(
                total = documents.totalElements,
                size = documents.size,
                isFirst = documents.isFirst,
                isLast = documents.isLast,
                items = documents.content
                        .map { DocumentMarkMapper.map(it, markeds.find { marked -> marked.id == it.markedObjectId }) }
                        .toList()
        )
    }
}