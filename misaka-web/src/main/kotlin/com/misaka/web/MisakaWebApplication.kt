package com.misaka.web

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.ComponentScan

@SpringBootApplication
@ComponentScan("com.misaka")
class MisakaWebApplication

fun main(args: Array<String>) {
    runApplication<MisakaWebApplication>(*args)
}
