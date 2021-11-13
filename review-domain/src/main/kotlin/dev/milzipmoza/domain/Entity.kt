package dev.milzipmoza.domain

/**
 * 식별자로 object 의 동등성 비교가 가능한 객체를 Entity 라고 한다
 */
interface Entity<T> {

    fun getId(): String

    fun <E : Entity<T>> sameAs(other: E) = this.getId() == other.getId()
}