package com.status.board

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.scheduling.annotation.EnableScheduling
import org.springframework.web.bind.annotation.CrossOrigin

@SpringBootApplication
@EnableScheduling
@CrossOrigin
class BoardApplication

fun main(args: Array<String>) {
	runApplication<BoardApplication>(*args)
}
