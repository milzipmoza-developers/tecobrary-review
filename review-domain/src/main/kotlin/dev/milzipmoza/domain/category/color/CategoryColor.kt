package dev.milzipmoza.domain.category.color

import dev.milzipmoza.domain.Value

class CategoryColor(
        val code: String
): Value<CategoryColor> {

    init {
        if (!code.startsWith(CODE_PREFIX)) {
            throw CategoryColorOperationException("카테고리 색상은 컬러코드로 설정할 수 있습니다.")
        }
        if (code.length != CODE_LENGTH) {
            throw CategoryColorOperationException("컬러코드는 #000000 형식으로 설정할 수 있습니다.")
        }
    }

    override fun sameAs(other: CategoryColor) = this == other

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as CategoryColor

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