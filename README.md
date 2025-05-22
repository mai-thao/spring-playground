# spring-playground
Learning about the [Spring](https://spring.io/) ecosystem using a [multi-module Gradle](https://docs.gradle.org/current/userguide/multi_project_builds.html) project

This project has a root module and submodule(s):
* `spring-playground/` is the root module. It contains the root `build.gradle.kts` that defines shared global configurations, 
plugins, repositories, and versions across all submodules

* `spring-playground/spring-boot/` is a specific submodule focused on Spring Boot and has its own `build.gradle.kts`. It applies
the plugins and dependencies needed for the Spring Boot app (and inherits the versions specified from the root `build.gradle.kts` file)

* `settings.gradle.kts` defines all the submodules in the project, letting Gradle know so it can connect and build them together
