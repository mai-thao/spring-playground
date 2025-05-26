package com.example.demo

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

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