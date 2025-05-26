plugins {
    kotlin("jvm") version "1.9.25"
    kotlin("plugin.spring") version "1.9.25"
    id("org.springframework.boot") version "3.4.5"
    id("io.spring.dependency-management") version "1.1.7"
}

subprojects {
    group = "com.example"
    version = "0.0.1-SNAPSHOT"

    repositories {
        mavenCentral()
    }

    apply(plugin = "org.springframework.boot")
    apply(plugin = "io.spring.dependency-management")
    apply(plugin = "org.jetbrains.kotlin.jvm")
    apply(plugin = "org.jetbrains.kotlin.plugin.spring")

    // All submodules will use Java 21 (current LTS)
    tasks.withType<JavaCompile> {
        sourceCompatibility = JavaVersion.VERSION_21.toString()
        targetCompatibility = JavaVersion.VERSION_21.toString()
    }

    dependencies {
        implementation("org.springframework.boot:spring-boot-starter-web")
        implementation("org.springframework.boot:spring-boot-starter-data-jpa")
        implementation("org.jetbrains.kotlin:kotlin-reflect") // Kotlin doesn't support reflection by default so explicitly force it to

        testImplementation("org.springframework.boot:spring-boot-starter-test") {
            exclude(module = "mockito-core") // Using Mockk so don't need the default Mockito mock framework
        }
        testImplementation("io.mockk:mockk:1.14.2")
    }

    // All submodules will use the JUnit testing framework
    tasks.withType<Test> {
        useJUnitPlatform()
    }
}
