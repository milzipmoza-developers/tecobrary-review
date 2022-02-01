package dev.milzipmoza.review.mongo.mark.mongo

import dev.milzipmoza.review.domain.mark.Mark
import dev.milzipmoza.review.domain.mark.MarkBook
import dev.milzipmoza.review.domain.mark.MarkMember
import dev.milzipmoza.review.domain.mark.MarkType
import dev.milzipmoza.review.mongo.extension.Logger
import dev.milzipmoza.review.mongo.mark.domain.MongoMarkOperation
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
internal class MongoMarkAggregatorTest {

    private val log = Logger.of(this)

    @Autowired
    private lateinit var sut: MongoMarkAggregator

    @Autowired
    private lateinit var mongoMarkOperation: MongoMarkOperation

    @Autowired
    private lateinit var mongoMarkRepository: MongoMarkRepository

    @Autowired
    private lateinit var mongoMarkedRepository: MongoMarkedRepository

    @BeforeEach
    internal fun setUp() {
        listOf(
                Mark(type = MarkType.LIKE, member = MarkMember("1"), book = MarkBook("1")),
                Mark(type = MarkType.LIKE, member = MarkMember("2"), book = MarkBook("1")),
                Mark(type = MarkType.LIKE, member = MarkMember("3"), book = MarkBook("1")),
                Mark(type = MarkType.LIKE, member = MarkMember("4"), book = MarkBook("1")).unmark(),
                Mark(type = MarkType.LIKE, member = MarkMember("5"), book = MarkBook("2")),
                Mark(type = MarkType.LIKE, member = MarkMember("6"), book = MarkBook("2")),
                Mark(type = MarkType.FAVORITE, member = MarkMember("6"), book = MarkBook("1")),
        ).forEach { mongoMarkOperation.create(it) }
    }

    @Test
    fun findTop() {
        val findTop = sut.findTop(MarkType.LIKE.name, 3)

        assertThat(findTop.size).isEqualTo(2)

        assertThat(findTop[0].bookNo).isEqualTo("1")
        assertThat(findTop[0].count).isEqualTo(3)
        assertThat(findTop[0].marked).isEqualTo(true)

        assertThat(findTop[1].bookNo).isEqualTo("2")
        assertThat(findTop[1].count).isEqualTo(2)
        assertThat(findTop[1].marked).isEqualTo(true)
    }
}