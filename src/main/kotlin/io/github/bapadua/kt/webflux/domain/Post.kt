package io.github.bapadua.kt.webflux.domain

data class Post(
    val userId: Int,
    val id: Int,
    val title: String,
    val body: String
)