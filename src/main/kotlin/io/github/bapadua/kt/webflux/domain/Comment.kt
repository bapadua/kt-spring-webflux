package io.github.bapadua.kt.webflux.domain

data class Comment(
    val postId: Int,
    val id: Int,
    val name: String,
    val email: String,
    val body: String
)