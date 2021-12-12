package dev.milzipmoza.review.domain.tag.model.name

import dev.milzipmoza.review.domain.Value

class TagName(
        val name: String
): Value<TagName> {

    init {
        if (name.length < 2) {
            throw TagNameException("태그 이름은 두 글자 이상으로 지정할 수 있습니다.")
        }

        if (name.length > 5) {
            throw TagNameException("태그 이름은 최대 5자까지 지정할 수 있습니다.")
        }
    }

    override fun sameAs(other: TagName) = this == other

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as TagName

        if (name != other.name) return false

        return true
    }

    override fun hashCode(): Int {
        return name.hashCode()
    }
}
