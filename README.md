# spring-playground
Learning about the [Spring](https://spring.io/) ecosystem using a [multi-project Gradle](https://docs.gradle.org/current/userguide/multi_project_builds.html) setup. I will explore some of the core Spring modules like Spring Boot, Spring Data, Spring Kafka, Spring Security, etc. 

This project has a root module and submodule(s):
* `spring-playground/` is the root module. It contains the root `build.gradle.kts` that defines shared global configurations, 
plugins, repositories, and versions across all submodules

* `spring-playground/spring-data/` is a specific submodule focused on Spring Data and has its own `build.gradle.kts`. It explicitly 
applies the unique plugins and dependencies it needs (and inherits the rest from the root `build.gradle.kts` file)

* `settings.gradle.kts` defines all the submodules in the project, letting Gradle know so it can connect and build them together
