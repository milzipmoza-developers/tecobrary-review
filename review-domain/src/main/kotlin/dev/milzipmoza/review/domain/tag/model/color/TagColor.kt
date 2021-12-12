package dev.milzipmoza.review.domain.tag.model.color

import dev.milzipmoza.review.domain.Value

class TagColor(
        val code: String
) : Value<TagColor> {

    init {
        if (!code.startsWith(CODE_PREFIX)) {
            throw TagColorException("태그 색상은 컬러코드로 설정할 수 있습니다.")
        }
        if (code.length != CODE_LENGTH) {
            throw TagColorException("컬러코드는 #000000 형식으로 설정할 수 있습니다.")
        }
    }

    override fun sameAs(other: TagColor) = this == other

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as TagColor

        if (code != other.code) return false

        return true
    }

    override fun hashCode(): Int {
        return code.hashCode()
    }

    companion object {
        private const val CODE_PREFIX = "#"
        private const val CODE_LENGTH = 7
    }
}
