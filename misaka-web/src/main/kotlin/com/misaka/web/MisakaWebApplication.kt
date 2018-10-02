package com.misaka.web

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class MisakaWebApplication

fun main(args: Array<String>) {
    runApplication<MisakaWebApplication>(*args)
}
