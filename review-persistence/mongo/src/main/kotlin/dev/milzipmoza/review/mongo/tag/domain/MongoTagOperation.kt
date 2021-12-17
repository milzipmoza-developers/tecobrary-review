package dev.milzipmoza.review.mongo.tag.domain

import dev.milzipmoza.review.domain.tag.TagOperation
import dev.milzipmoza.review.domain.tag.model.Tag
import dev.milzipmoza.review.domain.unwrap
import dev.milzipmoza.review.mongo.DocumentNotFoundException
import dev.milzipmoza.review.mongo.extension.Logger
import dev.milzipmoza.review.mongo.tag.mongo.DocumentTag
import dev.milzipmoza.review.mongo.tag.mongo.DocumentTagBooks
import dev.milzipmoza.review.mongo.tag.mongo.MongoTagBooksRepository
import dev.milzipmoza.review.mongo.tag.mongo.MongoTagRepository
import org.bson.types.ObjectId
import org.springframework.stereotype.Repository

@Repository
class MongoTagOperation(
        private val mongoTagRepository: MongoTagRepository,
        private val mongoTagBooksRepository: MongoTagBooksRepository
) : TagOperation {

    private val log = Logger.of(this)

    override fun save(newTag: Tag): Boolean {
        val documentTagBooks = DocumentTagBooks()
        val savedTagBooks = mongoTagBooksRepository.save(documentTagBooks)
        log.info("[MongoTagOperation] succeed saving tag books={}", savedTagBooks)

        val documentTag = DocumentTag(colorCode = newTag.color.code, name = newTag.name.name, description = newTag.description.description, bookMappingId = savedTagBooks.id)
        val savedTag = mongoTagRepository.save(documentTag)
        log.info("[MongoTagOperation] succeed saving tag={}", savedTag)

        return true
    }

    override fun update(tag: Tag): Boolean {
        val objectId = ObjectId(tag.no)

        val documentTag = mongoTagRepository.findById(objectId).unwrap()
                ?: throw DocumentNotFoundException("조건에 맞는 결과를 찾을 수 없어요.")

        val newDocumentTag = DocumentTagMapper.map(objectId, tag, documentTag)

        val savedTag = mongoTagRepository.save(newDocumentTag)
        log.info("[MongoTagOperation] succeed updating tag={}", savedTag)

        return true
    }
}