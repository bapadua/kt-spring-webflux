package io.github.bapadua.kt.webflux.controller

import io.github.bapadua.kt.webflux.domain.LightComment
import io.github.bapadua.kt.webflux.domain.Response
import io.github.bapadua.kt.webflux.service.APIService
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Mono
import reactor.core.scheduler.Schedulers
import reactor.kotlin.core.publisher.toMono

@RestController
@RequestMapping(path = ["/api"], produces = [MediaType.APPLICATION_JSON_VALUE])
class APIController(
    private val apiService: APIService
) {

    @GetMapping
    fun getData(): Mono<ResponseEntity<List<Response>>> {
        return apiService.fetchPosts()
            .filter { it -> it.userId % 2 == 0 }
            .take(20)
            .parallel(4)
            .runOn(Schedulers.parallel())
            .map { post ->
                apiService.fetchComments(post.id)
                    .map { comment -> LightComment(email = comment.email, body = comment.body) }
                    .collectList()
                    .zipWith(post.toMono())
            }
            .flatMap { it -> it }
            .map { result ->
                Response(
                    postId = result.t2.id,
                    userId = result.t2.id,
                    title = result.t2.title,
                    comments = result.t1
                )
            }
            .sequential()
            .collectList()
            .map { body -> ResponseEntity.ok().body(body) }
            .toMono()
    }
}