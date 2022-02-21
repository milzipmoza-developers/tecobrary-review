package dev.milzipmoza.review.mongo.mark.domain

import dev.milzipmoza.review.domain.mark.Bookmark
import dev.milzipmoza.review.domain.mark.Bookmarks
import dev.milzipmoza.review.domain.mark.MarkMember
import dev.milzipmoza.review.mongo.mark.mongo.DocumentMarkMapper
import dev.milzipmoza.review.mongo.mark.mongo.MongoMarkCompoundRepository
import org.springframework.stereotype.Repository
import java.time.LocalDateTime

@Repository
class MongoBookmarks(
        private val mongoMarkCompoundRepository: MongoMarkCompoundRepository
) : Bookmarks {

    override fun getRecentBookmarkAfter(markMember: MarkMember, size: Long, before: LocalDateTime?): List<Bookmark> {
        return mongoMarkCompoundRepository.getRecentFavoriteAfter(DocumentMarkMapper.map(markMember), size, before)
                .map { Bookmark(
                        no = it.id.toHexString(),
                        member = DocumentMarkMapper.map(it.member),
                        book = DocumentMarkMapper.map(it.book),
                        markDateTime = it.txDateTime
                ) }
    }
}