package dev.milzipmoza.review.domain.category.model.name

import dev.milzipmoza.review.domain.Value

class CategoryName(
        val name: String
): Value<CategoryName> {

    init {
        if (name.length < 2) {
            throw CategoryNameOperationException("카테고리 이름은 두 글자 이상으로 지정할 수 있습니다.")
        }

        if (name.length > 10) {
            throw CategoryNameOperationException("카테고리 이름은 최대 10자까지 지정할 수 있습니다.")
        }
    }

    override fun sameAs(other: CategoryName) = this == other

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as CategoryName

        if (name != other.name) return false

        return true
    }

    override fun hashCode(): Int {
        return name.hashCode()
    }
}