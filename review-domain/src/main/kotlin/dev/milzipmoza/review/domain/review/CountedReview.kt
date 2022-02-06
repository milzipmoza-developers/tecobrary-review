package dev.milzipmoza.review.domain.review

class CountedReview(
        val bookIsbn: String,
        val count: Int
) : Comparable<CountedReview> {

    override fun compareTo(other: CountedReview): Int {
        return this.count.compareTo(other.count)
    }
}