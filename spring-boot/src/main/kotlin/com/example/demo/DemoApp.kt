package com.example.demo

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

/**
* @SpringBootApplication tags the DemoApplication class as a source for bean definitions, enables bean auto-configurations
* based on your jar dependencies, and enables component scanning (e.g. auto scan for controllers, services, and repositories).
*/
@SpringBootApplication
class DemoApplication

/**
* The main() method uses Spring Boot’s runApplication() method to bootstrap and launch the app (aka the app's entry point).
* The args array is passed through to propogate any command-line arguments to Spring Boot.
*/
fun main(args: Array<String>) {
    runApplication<DemoApplication>(*args)
}

/**
* The HelloController class is flagged as a @RestController, marking it ready for use by Spring MVC to handle web requests.
* @GetMapping maps / to the hello() method. When invoked from a browser or by using curl on the command line, the method
* returns pure text (because the return type is a String).
*/
@RestController
class HelloController {
    @GetMapping("/")
    fun hello(): String = "Hello Mai from Kotlin Spring Boot!"
}
