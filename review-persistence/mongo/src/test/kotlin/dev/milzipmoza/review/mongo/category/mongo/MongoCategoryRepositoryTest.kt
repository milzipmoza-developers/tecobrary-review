package dev.milzipmoza.review.mongo.category.mongo

import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.autoconfigure.mongo.embedded.EmbeddedMongoAutoConfiguration
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest
import org.springframework.boot.test.context.SpringBootTest

@DataMongoTest
class MongoCategoryRepositoryTest {

    private val logger: Logger = LoggerFactory.getLogger(this.javaClass)

    @Autowired
    private lateinit var mongoCategoryRepository: MongoCategoryRepository

    @DisplayName("DocumentCategory 데이터 맵핑 구조를 확인한다.")
    @Test
    internal fun name() {
        val document = DocumentCategory(
                name = "스프링",
                description = "아무튼 스프링임",
                image = DocumentCategoryImage(
                        host = "https://www.naver.com",
                        path = "/image.png"
                )
        )

        val savedDocument = mongoCategoryRepository.save(document)

        val id = savedDocument.id

        logger.info("id={}", id.toHexString())

        val findById = mongoCategoryRepository.findById(id)

        logger.info("result={}", findById)
    }
}