package dev.milzipmoza.review.domain.review.model

class ReviewKeyword(
        val content: Content,
        val informative: Informative,
        val readMore: ReadMore?,
        val selectables: Set<Selectable>
) {

    enum class Content {
        VERY_EASY,
        EASY,
        SO_SO,
        HARD,
        VERY_HARD
    }

    enum class Informative {
        NEVER,
        NO,
        YES,
        MUCH
    }

    enum class ReadMore {
        NO_MORE,
        NEEDED,
        ALL
    }

    enum class Selectable {
        FUNDAMENTAL,
        TECHNOLOGY,
        GOOD_CODE,
        GOOD_EXPLANATION,
        GOOD_TRANSLATION,
        BAD_TYPO
    }
}
