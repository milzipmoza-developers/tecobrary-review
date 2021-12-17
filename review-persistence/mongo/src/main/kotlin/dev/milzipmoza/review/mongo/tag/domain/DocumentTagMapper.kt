package dev.milzipmoza.review.mongo.tag.domain

import dev.milzipmoza.review.domain.tag.model.Tag
import dev.milzipmoza.review.domain.tag.model.book.TagBook
import dev.milzipmoza.review.domain.tag.model.book.TagBooks
import dev.milzipmoza.review.domain.tag.model.color.TagColor
import dev.milzipmoza.review.domain.tag.model.description.TagDescription
import dev.milzipmoza.review.domain.tag.model.name.TagName
import dev.milzipmoza.review.mongo.tag.mongo.DocumentTag
import dev.milzipmoza.review.mongo.tag.mongo.DocumentTagBooks
import org.bson.types.ObjectId

object DocumentTagMapper {

    fun map(documentTag: DocumentTag, documentTagBooks: DocumentTagBooks?): Tag {
        return Tag(
                no = documentTag.id.toHexString(),
                color = TagColor(code = documentTag.colorCode),
                name = TagName(name = documentTag.name),
                description = TagDescription(description = documentTag.description),
                books = TagBooks(books = documentTagBooks?.books?.map { TagBook(it) }?.toMutableList()
                        ?: mutableListOf())
        )
    }

    fun map(documentTag: DocumentTag): Tag {
        return Tag(
                no = documentTag.id.toHexString(),
                color = TagColor(code = documentTag.colorCode),
                name = TagName(name = documentTag.name),
                description = TagDescription(description = documentTag.description),
        )
    }

    fun map(objectId: ObjectId, domainTag: Tag, documentTag: DocumentTag): Pair<DocumentTag, DocumentTagBooks> {
        val tag = DocumentTag(
                id = objectId,
                colorCode = domainTag.color.code,
                name = domainTag.name.name,
                description = domainTag.description.description,
                bookMappingId = documentTag.bookMappingId
        )
        val tagBooks = DocumentTagBooks(
                id = tag.bookMappingId,
                books = domainTag.books.map { it.isbn }.toList()
        )
        return Pair(tag, tagBooks)
    }
}