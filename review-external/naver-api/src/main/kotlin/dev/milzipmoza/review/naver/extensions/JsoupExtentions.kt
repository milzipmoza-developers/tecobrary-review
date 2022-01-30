package dev.milzipmoza.review.naver.extensions

import org.jsoup.Jsoup

fun String.removeHtmlTags(): String {
    return Jsoup.parse(this).text()
}

fun String.removeNaverSearchDelimiters(): String {
    return this.replace("|", ", ")
}