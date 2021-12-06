package dev.milzipmoza.review.mongo.mark.domain

import dev.milzipmoza.review.domain.mark.Mark
import dev.milzipmoza.review.domain.mark.MarkBook
import dev.milzipmoza.review.domain.mark.MarkMember
import dev.milzipmoza.review.domain.mark.MarkType
import dev.milzipmoza.review.mongo.mark.mongo.DocumentMarkBook
import dev.milzipmoza.review.mongo.mark.mongo.DocumentMarkMember
import dev.milzipmoza.review.mongo.mark.mongo.MongoMarkRepository
import dev.milzipmoza.review.mongo.mark.mongo.MongoMarkedRepository
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
internal class MongoMarkOperationTest {

    @Autowired
    private lateinit var mongoMarkOperation: MongoMarkOperation

    @Autowired
    private lateinit var mongoMarkRepository: MongoMarkRepository

    @Autowired
    private lateinit var mongoMarkedRepository: MongoMarkedRepository

    @AfterEach
    internal fun tearDown() {
        mongoMarkRepository.deleteAll()
        mongoMarkedRepository.deleteAll()
    }

    @Test
    fun `데이터를 성공적으로 저장한다`() {
        val type = MarkType.LIKE
        val memberNo = "930705"
        val bookNo = "9788960776128"
        val mark = Mark(
                type = type,
                member = MarkMember(no = memberNo),
                book = MarkBook(no = bookNo)
        )

        mongoMarkOperation.create(mark)

        val savedDocumentMark = mongoMarkRepository.findByMemberAndBookAndType(
                DocumentMarkMember(memberNo),
                DocumentMarkBook(bookNo),
                type.toString()
        )!!

        val savedDocumentMarked = mongoMarkedRepository.findById(savedDocumentMark.markedObjectId)
                .orElse(null)!!

        assertThat(savedDocumentMark.book.no).isEqualTo(bookNo)
        assertThat(savedDocumentMark.member.no).isEqualTo(memberNo)
        assertThat(MarkType.valueOf(savedDocumentMark.type)).isEqualTo(type)

        assertThat(savedDocumentMarked.marked).isEqualTo(true)
    }

    @Test
    fun `데이터를 업데이트 할 수 있다`() {
        val type = MarkType.LIKE
        val memberNo = "930705"
        val bookNo = "9788960776128"
        val mark = Mark(
                type = type,
                member = MarkMember(no = memberNo),
                book = MarkBook(no = bookNo)
        )

        mongoMarkOperation.create(mark)

        val updateTarget = mongoMarkRepository.findByMemberAndBookAndType(
                DocumentMarkMember(memberNo),
                DocumentMarkBook(bookNo),
                type.toString()
        )!!

        val newMark = Mark(
                type = type,
                member = MarkMember(no = memberNo),
                book = MarkBook(no = bookNo)
        )

        val unmark = newMark.unmark()

        mongoMarkOperation.update(updateTarget.id.toHexString(), unmark)

        val savedDocumentMark = mongoMarkRepository.findByMemberAndBookAndType(
                DocumentMarkMember(memberNo),
                DocumentMarkBook(bookNo),
                type.toString()
        )!!

        val savedDocumentMarked = mongoMarkedRepository.findById(savedDocumentMark.markedObjectId)
                .orElse(null)!!

        assertThat(savedDocumentMark.book.no).isEqualTo(updateTarget.book.no)
        assertThat(savedDocumentMark.member.no).isEqualTo(updateTarget.member.no)
        assertThat(MarkType.valueOf(savedDocumentMark.type)).isEqualTo(MarkType.valueOf(updateTarget.type))

        assertThat(savedDocumentMarked.marked).isEqualTo(false)
    }
}