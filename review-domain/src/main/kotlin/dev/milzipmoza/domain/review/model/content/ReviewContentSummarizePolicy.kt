package dev.milzipmoza.domain.review.model.content

object ReviewContentSummarizePolicy {

    private const val SUMMARIZE_CONTENT_LENGTH = 500
    private const val EXTRACT_CONTENT_LENGTH = 30
    private const val LENGTH_EXCEED_SUFFIX = "..."
    private const val START_INDEX = 0
    private const val END_OF_SENTENCE = "."
    private const val NO_END_OF_SENTENCE = -1
    private const val INCLUSIVE_LAST_INDEX = 1

    fun applyLongContent(content: String) =
            when (content.length > SUMMARIZE_CONTENT_LENGTH) {
                true -> content.appendSuffixAfterRemove(SUMMARIZE_CONTENT_LENGTH, LENGTH_EXCEED_SUFFIX)
                false -> content
            }

    fun applyInlineContent(content: String) =
            when (val endOrFirstSentence = content.indexOf(END_OF_SENTENCE)) {
                NO_END_OF_SENTENCE -> content.appendSuffixAfterRemove(EXTRACT_CONTENT_LENGTH, LENGTH_EXCEED_SUFFIX)
                else -> content.substring(START_INDEX, endOrFirstSentence + INCLUSIVE_LAST_INDEX)
            }

    private fun String.appendSuffixAfterRemove(targetContentLength: Int, suffix: String): String =
            this.removeRange(targetContentLength, this.length) + suffix
}
