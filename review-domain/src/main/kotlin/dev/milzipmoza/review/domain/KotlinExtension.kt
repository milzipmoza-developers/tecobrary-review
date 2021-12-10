package dev.milzipmoza.review.domain

import java.util.Optional

fun <T> Optional<T>.unwrap(): T? = orElse(null)
