package dev.milzipmoza.domain.category.model.description

import dev.milzipmoza.domain.Value

class CategoryDescription(
        val description: String
): Value<CategoryDescription> {

    init {
        if (description.length < LOWER_BOUND) {
            throw CategoryDescriptionOperationException("카테고리 설명은 최소 한 글자 이상이어야 합니다.")
        }
        if (description.length > UPPER_BOUND) {
            throw CategoryDescriptionOperationException("카테고리 설명은 최대 ${UPPER_BOUND}글자입니다. 입력한 글자 수는 ${description.length}자 입니다")
        }
    }

    override fun sameAs(other: CategoryDescription) = this == other

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as CategoryDescription

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