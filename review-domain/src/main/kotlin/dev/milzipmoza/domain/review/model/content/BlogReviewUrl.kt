package dev.milzipmoza.domain.review.model.content

import dev.milzipmoza.domain.Url
import dev.milzipmoza.domain.UrlExtractor

class BlogReviewUrl : Url {

    constructor(host: String, path: String) : super(host, path)

    constructor(fullUrl: String) : super(fullUrl)
}
