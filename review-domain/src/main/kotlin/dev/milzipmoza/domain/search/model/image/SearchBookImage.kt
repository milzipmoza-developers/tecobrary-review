package dev.milzipmoza.domain.search.model.image

import dev.milzipmoza.domain.Url

class SearchBookImage : Url {

    constructor(host: String, path: String) : super(host, path)

    constructor(fullUrl: String) : super(fullUrl)
}
