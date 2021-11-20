package dev.milzipmoza.domain.review.model.content

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class ReviewContentSummarizePolicyTest {

    @Test
    fun `500자가 넘지 않으면 요약하지 않는다`() {
        val length500Content = LENGTH_20_TEXT.repeat(REPEAT)

        val result = ReviewContentSummarizePolicy.applyLongContent(length500Content)

        assertFalse(result.contains("..."))
    }

    @Test
    fun `500자가 넘으면 요약한다`() {
        val length500Content = LENGTH_20_TEXT.repeat(REPEAT) + "1"

        val result = ReviewContentSummarizePolicy.applyLongContent(length500Content)

        assertTrue(result.contains("..."))
    }

    @Test
    fun `첫 마침표가 끝나는 곳을 첫 문장이 끝나는 지점으로 보고 끊어 추출한다`() {
        val sentencesOf25 = LENGTH_20_TEXT.repeat(REPEAT)

        val result = ReviewContentSummarizePolicy.applyInlineContent(sentencesOf25)

        assertEquals(LENGTH_20_TEXT, result)
    }

    @Test
    fun `마침표가 존재하지 않으면 강제로 30글자로 추출하고 요약 표시를 한다`() {
        val noDotContent = LENGTH_20_TEXT.repeat(REPEAT).replace(".", "")

        val result = ReviewContentSummarizePolicy.applyInlineContent(noDotContent)

        assertEquals(30 + "...".length, result.length)
        assertTrue(result.contains("..."))
    }

    companion object {
        private const val LENGTH_20_TEXT = "이 문자열은 20자의 문자열 입니다."
        private const val REPEAT = 25
    }
}