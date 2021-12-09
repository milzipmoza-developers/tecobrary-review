package dev.milzipmoza.review.mongo.mark.domain

import dev.milzipmoza.review.domain.mark.Mark
import dev.milzipmoza.review.domain.mark.MarkBook
import dev.milzipmoza.review.domain.mark.MarkMember
import dev.milzipmoza.review.domain.mark.MarkType
import dev.milzipmoza.review.mongo.mark.mongo.MongoMarkRepository
import dev.milzipmoza.review.mongo.mark.mongo.MongoMarkedRepository
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
internal class MongoMarksTest {

    @Autowired
    private lateinit var mongoMarks: MongoMarks

    @Autowired
    private lateinit var mongoMarkOperation: MongoMarkOperation

    @Autowired
    private lateinit var mongoMarkRepository: MongoMarkRepository

    @Autowired
    private lateinit var mongoMarkedRepository: MongoMarkedRepository

    val type = MarkType.LIKE
    val member = MarkMember(no = "930705")
    val book = MarkBook(no = "9788960776128")

    @BeforeEach
    internal fun setUp() {
        val type = MarkType.LIKE
        val mark = Mark(
                type = type,
                member = member,
                book = book
        )

        mongoMarkOperation.create(mark)
    }

    @AfterEach
    internal fun tearDown() {
        mongoMarkRepository.deleteAll()
        mongoMarkedRepository.deleteAll()
    }

    @Test
    fun findByMemberAndBookAndType() {
        val mark = mongoMarks.findBy(member, book, type)!!

        assertThat(mark.book).isEqualTo(book)
        assertThat(mark.member).isEqualTo(member)
        assertThat(mark.type).isEqualTo(type)
        assertThat(mark.marked).isEqualTo(true)
    }
}