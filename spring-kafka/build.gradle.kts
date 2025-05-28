dependencies {
    implementation("org.springframework.kafka:spring-kafka")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin") // For Kafka serdes

    testImplementation("org.springframework.kafka:spring-kafka-test")
}
