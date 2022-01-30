package dev.milzipmoza.review.mongo.mark.mongo

import dev.milzipmoza.review.domain.mark.Mark
import dev.milzipmoza.review.domain.mark.MarkBook
import dev.milzipmoza.review.domain.mark.MarkMember
import dev.milzipmoza.review.domain.mark.MarkType
import dev.milzipmoza.review.mongo.extension.Logger
import dev.milzipmoza.review.mongo.mark.domain.MongoMarkOperation
import java.util.stream.IntStream
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
internal class MongoMarkCompoundRepositoryTest {

    private val log = Logger.of(this)

    @Autowired
    private lateinit var sut: MongoMarkCompoundRepository

    @Autowired
    private lateinit var mongoMarkOperation: MongoMarkOperation

    @Autowired
    private lateinit var mongoMarkRepository: MongoMarkRepository

    @Autowired
    private lateinit var mongoMarkedRepository: MongoMarkedRepository

    val type = MarkType.LIKE

    @BeforeEach
    internal fun setUp() {
        val marks = IntRange(1,2500).map {
            Mark(
                    type = type,
                    member = MarkMember(no = "$it"),
                    book = MarkBook(no = "11111")
            )
        } + IntRange(2501, 5000).map {
            Mark(
                    type = type,
                    member = MarkMember(no = "$it"),
                    book = MarkBook(no = "33333")
            )
        } + IntRange(5001, 7500).map {
            Mark(
                    type = MarkType.FAVORITE,
                    member = MarkMember(no = "$it"),
                    book = MarkBook(no = "11111")
            )
        }

        val map = marks.map { DocumentMarkMapper.map(it) }

        val keys = map.map { it.first }.toList()
        val values = map.map { it.second }.toList()

        mongoMarkRepository.saveAll(keys)
        mongoMarkedRepository.saveAll(values)
    }

    @AfterEach
    internal fun tearDown() {
        mongoMarkRepository.deleteAll()
        mongoMarkedRepository.deleteAll()
    }

    @Test
    fun countMarked() {
        val start = System.currentTimeMillis()
        val countMarked = sut.countMarked(DocumentMarkBook("11111"), type.name)
        log.info("elapsed=${System.currentTimeMillis() - start}")

        assertThat(countMarked).isEqualTo(5000)
    }
}