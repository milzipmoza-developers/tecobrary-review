package dev.milzipmoza.review.domain.tag.model.description

import dev.milzipmoza.review.domain.Value

class TagDescription(
        val description: String
) : Value<TagDescription> {

    init {
        if (description.length < LOWER_BOUND) {
            throw TagDescriptionException("태그 설명은 최소 한 글자 이상이어야 합니다.")
        }
        if (description.length > UPPER_BOUND) {
            throw TagDescriptionException("태그 설명은 최대 ${UPPER_BOUND}글자입니다. 입력한 글자 수는 ${description.length}자 입니다")
        }
    }

    override fun sameAs(other: TagDescription) = this == other

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as TagDescription

        if (description != other.description) return false

        return true
    }

    override fun hashCode(): Int {
        return description.hashCode()
    }

    companion object {
        private const val LOWER_BOUND = 2
        private const val UPPER_BOUND = 25
    }
}
