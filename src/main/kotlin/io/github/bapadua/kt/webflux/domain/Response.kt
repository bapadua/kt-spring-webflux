package io.github.bapadua.kt.webflux.domain

data class Response(
    val postId: Int,
    val userId: Int,
    val title: String,
    val comments: List<LightComment>
)