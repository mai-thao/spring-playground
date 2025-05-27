plugins {
    kotlin("jvm") version "1.9.25"
    kotlin("plugin.spring") version "1.9.25"
    id("org.springframework.boot") version "3.4.5"
    id("io.spring.dependency-management") version "1.1.7"
}

allprojects {
    repositories {
        mavenCentral()
    }
}

subprojects {
    group = "com.example"
    version = "0.0.1-SNAPSHOT"

    apply(plugin = "org.springframework.boot")
    apply(plugin = "io.spring.dependency-management")
    apply(plugin = "org.jetbrains.kotlin.jvm")
    apply(plugin = "org.jetbrains.kotlin.plugin.spring")

    // Gradle and all submodules will target JDK and bytecode to Java 21 (current LTS)
    // For more info on Java support roadmap, see: https://www.oracle.com/java/technologies/java-se-support-roadmap.html
    kotlin {
        jvmToolchain(21)
    }

    dependencies {
        implementation("org.springframework.boot:spring-boot-starter-web")
        implementation("org.springframework.boot:spring-boot-starter-data-jpa")
        implementation("org.jetbrains.kotlin:kotlin-reflect") // Kotlin doesn't support reflection by default so explicitly force it to

        testImplementation("org.springframework.boot:spring-boot-starter-test") {
            exclude(module = "mockito-core") // Using Mockk so don't need the default Mockito mock framework
        }
        testImplementation("io.mockk:mockk:1.14.2")
        testImplementation("com.ninja-squad:springmockk:4.0.2")
    }

    // All submodules will use the JUnit testing framework and log all test outputs
    tasks.withType<Test> {
        useJUnitPlatform()

        testLogging {
            events("passed", "skipped", "failed") // Log all tests and their outputs
            outputs.upToDateWhen { false } // Auto force tests to rerun every time without specifying --rerun-tasks flag
        }
    }
}
