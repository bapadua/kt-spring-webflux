package io.github.bapadua.kt.webflux

import org.springframework.boot.SpringApplication
import org.springframework.boot.WebApplicationType
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.web.reactive.config.EnableWebFlux

@EnableWebFlux
@SpringBootApplication
class DemoWebfluxApplication

fun main(args: Array<String>) {
	val app = SpringApplication(DemoWebfluxApplication::class.java)
	app.webApplicationType = WebApplicationType.REACTIVE
	app.run(*args)
}
