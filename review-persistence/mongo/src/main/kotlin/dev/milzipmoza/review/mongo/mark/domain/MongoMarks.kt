package dev.milzipmoza.review.mongo.mark.domain

import dev.milzipmoza.review.domain.PageEntities
import dev.milzipmoza.review.domain.PageQuery
import dev.milzipmoza.review.domain.mark.Mark
import dev.milzipmoza.review.domain.mark.MarkBook
import dev.milzipmoza.review.domain.mark.MarkMember
import dev.milzipmoza.review.domain.mark.MarkType
import dev.milzipmoza.review.domain.mark.Marks
import dev.milzipmoza.review.mongo.extension.PageRequest
import dev.milzipmoza.review.mongo.mark.mongo.DocumentMark
import dev.milzipmoza.review.mongo.mark.mongo.DocumentMarkMapper
import dev.milzipmoza.review.mongo.mark.mongo.MongoMarkRepository
import dev.milzipmoza.review.mongo.mark.mongo.MongoMarkedRepository
import org.springframework.data.domain.Page
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
        return mongoMarkRepository.findAll(PageRequest.of(pageQuery))
                .toPageEntities()
    }

    override fun findAllBy(member: MarkMember, pageQuery: PageQuery): PageEntities<Mark> {
        return mongoMarkRepository.findAllByMember(DocumentMarkMapper.map(member), PageRequest.of(pageQuery))
                .toPageEntities()
    }

    override fun findAllBy(book: MarkBook, pageQuery: PageQuery): PageEntities<Mark> {
        return mongoMarkRepository.findAllByBook(DocumentMarkMapper.map(book), PageRequest.of(pageQuery))
                .toPageEntities()
    }

    override fun findAllBy(member: MarkMember, book: MarkBook, pageQuery: PageQuery): PageEntities<Mark> {
        return mongoMarkRepository.findAllByMemberAndBook(DocumentMarkMapper.map(member), DocumentMarkMapper.map(book), PageRequest.of(pageQuery))
                .toPageEntities()
    }

    private fun Page<DocumentMark>.toPageEntities(): PageEntities<Mark> {

        val markedIds = this.content.map { it -> it.markedObjectId }

        val markeds = mongoMarkedRepository.findAllById(markedIds)

        return PageEntities(
                total = this.totalElements,
                size = this.size,
                isFirst = this.isFirst,
                isLast = this.isLast,
                items = this.content
                        .map { DocumentMarkMapper.map(it, markeds.find { marked -> marked.id == it.markedObjectId }) }
                        .toList()
        )
    }
}