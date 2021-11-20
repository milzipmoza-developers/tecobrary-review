package dev.milzipmoza.review.domain

/**
 * 모든 필드로 object 의 동등성 비교가 가능한 객체를 Value 라고 한다.
 */
interface Value<T> {

    fun sameAs(other: T): Boolean
}