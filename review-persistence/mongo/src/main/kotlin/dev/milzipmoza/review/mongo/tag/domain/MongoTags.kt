package dev.milzipmoza.review.mongo.tag.domain

import dev.milzipmoza.review.domain.PageEntities
import dev.milzipmoza.review.domain.PageQuery
import dev.milzipmoza.review.domain.tag.Tags
import dev.milzipmoza.review.domain.tag.model.Tag
import dev.milzipmoza.review.domain.tag.model.book.TagBook
import dev.milzipmoza.review.domain.tag.model.book.TagBooks
import dev.milzipmoza.review.domain.tag.model.color.TagColor
import dev.milzipmoza.review.domain.tag.model.description.TagDescription
import dev.milzipmoza.review.domain.tag.model.name.TagName
import dev.milzipmoza.review.domain.unwrap
import dev.milzipmoza.review.mongo.DocumentNotFoundException
import dev.milzipmoza.review.mongo.extension.PageRequest
import dev.milzipmoza.review.mongo.tag.mongo.DocumentTag
import dev.milzipmoza.review.mongo.tag.mongo.DocumentTagBooks
import dev.milzipmoza.review.mongo.tag.mongo.MongoTagBooksRepository
import dev.milzipmoza.review.mongo.tag.mongo.MongoTagRepository
import org.bson.types.ObjectId
import org.springframework.stereotype.Repository

@Repository
class MongoTags(
        private val mongoTagRepository: MongoTagRepository,
        private val mongoTagBooksRepository: MongoTagBooksRepository
) : Tags {

    override fun findBy(no: String): Tag {
        val documentTag = mongoTagRepository.findById(ObjectId(no)).unwrap()
                ?: throw DocumentNotFoundException("조건에 맞는 결과를 찾을 수 없어요.")

        val documentTagBooks = mongoTagBooksRepository.findById(documentTag.bookMappingId).unwrap()

        return DocumentTagMapper.map(documentTag, documentTagBooks)
    }

    override fun findBy(tagName: TagName): Tag {
        val documentTag = mongoTagRepository.findByName(tagName.name)
                ?: throw DocumentNotFoundException("조건에 맞는 결과를 찾을 수 없어요.")

        val documentTagBooks = mongoTagBooksRepository.findById(documentTag.bookMappingId).unwrap()

        return DocumentTagMapper.map(documentTag, documentTagBooks)
    }

    override fun findBy(tagName: TagName, tagBook: TagBook): Tag {
        val documentTag = mongoTagRepository.findByName(tagName.name)
                ?: throw DocumentNotFoundException("조건에 맞는 결과를 찾을 수 없어요.")

        val documentTagBooks = mongoTagBooksRepository.findById(documentTag.bookMappingId).unwrap()
                ?: throw DocumentNotFoundException("조건에 맞는 결과를 찾을 수 없어요.")

        if (!documentTagBooks.books.contains(tagBook.isbn)) {
            throw DocumentNotFoundException("조건에 맞는 결과를 찾을 수 없어요.")
        }

        return DocumentTagMapper.map(documentTag, documentTagBooks)
    }

    override fun findAllBy(tagBook: TagBook, pageQuery: PageQuery): PageEntities<Tag> {
        val pageRequest = PageRequest.of(pageQuery)

        val pageTagBooks = mongoTagBooksRepository.findAllOfContainBook(tagBook.isbn, pageRequest)
        val tagBooks = pageTagBooks.content
        val documentTagBooksIds = tagBooks.map { it.id }

        val tags = mongoTagRepository.findAllByBookMappingIdIn(documentTagBooksIds, pageRequest)

        return PageEntities(
                total = tags.totalElements,
                size = tags.size,
                isFirst = tags.isFirst,
                isLast = tags.isLast,
                items = tags.content
                        .map { DocumentTagMapper.map(it, tagBooks.find { tagBook -> tagBook.id == it.bookMappingId }) }
                        .toList()
        )
    }

    override fun findAllBy(pageQuery: PageQuery): PageEntities<Tag> {
        val tags = mongoTagRepository.findAll(PageRequest.of(pageQuery))

        val tagBookIds = tags.content.map { it.bookMappingId }

        val tagBooks = mongoTagBooksRepository.findAllById(tagBookIds).toList()

        return PageEntities(
                total = tags.totalElements,
                size = tags.size,
                isFirst = tags.isFirst,
                isLast = tags.isLast,
                items = tags.content
                        .map { DocumentTagMapper.map(it, tagBooks.find { tagBook -> tagBook.id == it.bookMappingId }) }
                        .toList()
        )
    }
}