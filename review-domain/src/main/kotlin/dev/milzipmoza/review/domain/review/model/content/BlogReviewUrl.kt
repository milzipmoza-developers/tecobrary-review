package dev.milzipmoza.review.domain.review.model.content

import dev.milzipmoza.review.domain.Url

class BlogReviewUrl : Url {

    constructor(host: String, path: String) : super(host, path)

    constructor(fullUrl: String) : super(fullUrl)
}
