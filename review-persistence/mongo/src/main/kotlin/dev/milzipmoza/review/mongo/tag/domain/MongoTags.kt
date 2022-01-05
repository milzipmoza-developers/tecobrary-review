package dev.milzipmoza.review.mongo.tag.domain

import dev.milzipmoza.review.domain.PageEntities
import dev.milzipmoza.review.domain.PageQuery
import dev.milzipmoza.review.domain.tag.Tags
import dev.milzipmoza.review.domain.tag.model.Tag
import dev.milzipmoza.review.domain.tag.model.name.TagName
import dev.milzipmoza.review.domain.unwrap
import dev.milzipmoza.review.mongo.DocumentNotFoundException
import dev.milzipmoza.review.mongo.extension.PageRequest
import dev.milzipmoza.review.mongo.tag.mongo.MongoTagRepository
import org.bson.types.ObjectId
import org.springframework.stereotype.Repository

@Repository
class MongoTags(
        private val mongoTagRepository: MongoTagRepository
) : Tags {

    override fun findBy(no: String): Tag {
        val documentTag = mongoTagRepository.findById(ObjectId(no)).unwrap()
                ?: throw DocumentNotFoundException("조건에 맞는 결과를 찾을 수 없어요.")

        return DocumentTagMapper.map(documentTag)
    }

    override fun findBy(tagName: TagName): Tag {
        val documentTag = mongoTagRepository.findByName(tagName.name)
                ?: throw DocumentNotFoundException("조건에 맞는 결과를 찾을 수 없어요.")

        return DocumentTagMapper.map(documentTag)
    }

    override fun findBy(tagName: TagName, exceptNos: List<String>): Tag {
        val documentTag = mongoTagRepository.findByNameExceptTagNos(tagName.name, exceptNos)
                ?: throw DocumentNotFoundException("조건에 맞는 결과를 찾을 수 없어요.")

        return DocumentTagMapper.map(documentTag)
    }

    override fun findAllBy(pageQuery: PageQuery): PageEntities<Tag> {
        val tags = mongoTagRepository.findAll(PageRequest.of(pageQuery))

        return PageEntities(
                total = tags.totalElements,
                size = tags.size,
                isFirst = tags.isFirst,
                isLast = tags.isLast,
                items = tags.content
                        .map { DocumentTagMapper.map(it) }
                        .toList()
        )
    }

    override fun findAllBy(nos: List<String>): List<Tag> {
        val ids = nos.map { ObjectId(it) }

        val documents = mongoTagRepository.findAllByIdIn(ids)

        return documents
                .map { DocumentTagMapper.map(it) }
                .toList()
    }

    override fun findAllBy(pageQuery: PageQuery, exceptNos: List<String>): PageEntities<Tag> {
        val tags = mongoTagRepository.findAllExceptTagNos(PageRequest.of(pageQuery), exceptNos)

        return PageEntities(
                total = tags.totalElements,
                size = tags.size,
                isFirst = tags.isFirst,
                isLast = tags.isLast,
                items = tags.content
                        .map { DocumentTagMapper.map(it) }
                        .toList()
        )
    }
}