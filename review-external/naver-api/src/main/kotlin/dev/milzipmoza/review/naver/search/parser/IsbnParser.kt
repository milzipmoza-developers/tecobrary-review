package dev.milzipmoza.review.naver.search.parser

/**
 * naver 의 isbn 은 8979148860 9788979148862 형식이다.
 * isbn 은 공백으로 구분된 뒷자리인 9788979148862 이다.
 */
object IsbnParser {

    fun parse(naverIsbn: String): String {
        return naverIsbn.split(" ").last()
    }
}