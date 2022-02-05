package dev.milzipmoza.review.mongo.review

import dev.milzipmoza.review.domain.review.DraftReviewOperation
import dev.milzipmoza.review.domain.review.model.DraftReview
import dev.milzipmoza.review.domain.review.model.ReviewKeyword
import org.springframework.stereotype.Repository

@Repository
class MongoDraftReviewOperation(
        private val mongoDraftReviewRepository: MongoDraftReviewRepository
) : DraftReviewOperation {

    override fun saveOrUpdate(draftReview: DraftReview): Boolean {

        val documentDraftReview =
                when (val document = mongoDraftReviewRepository.findByNo(draftReview.no)) {
                    null -> DocumentDraftReview(
                            no = draftReview.no,
                            member = DocumentDraftReviewMember(
                                    no = draftReview.member.no,
                                    deviceId = draftReview.member.deviceId
                            ),
                            book = DocumentDraftReviewBook(
                                    isbn = draftReview.book.isbn,
                                    title = draftReview.book.title
                            ),
                            range = draftReview.range?.name,
                            keyword = draftReview.keyword?.let { map(it) }
                    )
                    else -> DocumentDraftReview(
                            id = document.id,
                            no = draftReview.no,
                            member = DocumentDraftReviewMember(
                                    no = draftReview.member.no,
                                    deviceId = draftReview.member.deviceId
                            ),
                            book = DocumentDraftReviewBook(
                                    isbn = draftReview.book.isbn,
                                    title = draftReview.book.title
                            ),
                            range = draftReview.range?.name,
                            keyword = draftReview.keyword?.let { map(it) },
                            createdDateTime = document.createdDateTime,
                            expiredDateTime = document.expiredDateTime
                    )
                }

        return try {
            mongoDraftReviewRepository.save(documentDraftReview)
            true
        } catch (e: Exception) {
            false
        }
    }

    private fun map(draftReviewKeyword: ReviewKeyword) =
            DocumentDraftReviewKeyword(
                    content = draftReviewKeyword.content.name,
                    informative = draftReviewKeyword.informative.name,
                    readMore = draftReviewKeyword.readMore?.name,
                    selectables = draftReviewKeyword.selectables.map { it.name }.toSet()

            )
}