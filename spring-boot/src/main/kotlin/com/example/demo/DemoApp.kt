package com.example.demo

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

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
