package com.bytecodehq.demo

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class KafkaCosumerToggleDemoApplication

fun main(args: Array<String>) {
    runApplication<KafkaCosumerToggleDemoApplication>(*args)
}
