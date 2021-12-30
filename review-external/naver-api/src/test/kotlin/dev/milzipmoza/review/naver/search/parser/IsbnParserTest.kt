package dev.milzipmoza.review.naver.search.parser

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class IsbnParserTest {

    @Test
    fun example() {
        val naverIsbnFormat = "8979148860 9788979148862"

        val isbn = IsbnParser.parse(naverIsbnFormat)

        assertThat(isbn).isEqualTo("9788979148862")
    }
}