package com.example.demo

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Flux

@SpringBootApplication
class DemoApplication

fun main(args: Array<String>) {
    runApplication<DemoApplication>(*args)
}

@RestController
class ExampleController {
    @GetMapping("/works", produces = [MediaType.APPLICATION_STREAM_JSON_VALUE])
    fun worksWithoutCache() = generateSimpleSequence()

    @GetMapping("/doesNot", produces = [MediaType.APPLICATION_STREAM_JSON_VALUE])
    fun doesNotWorkWithCache() = generateSimpleSequence().cache(1)

    @GetMapping("/worksWithFlatMap", produces = [MediaType.APPLICATION_STREAM_JSON_VALUE])
    fun worksWithFlatMap() = generateSimpleSequence()
			.cache(1)
            .flatMap { Flux.just(it) }

    @GetMapping("/worksWithJsonResult", produces = [MediaType.APPLICATION_JSON_VALUE])
    fun worksWithJsonResult() = generateSimpleSequence().cache(1)

    private fun generateSimpleSequence() = Flux.create<Int> {
        (1..10).forEach { v -> it.next(v) }
        it.complete()
    }
}
