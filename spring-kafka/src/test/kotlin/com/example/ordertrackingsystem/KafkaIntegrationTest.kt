package com.example.ordertrackingsystem

import com.example.ordertrackingsystem.model.Order
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.kafka.test.EmbeddedKafkaBroker
import org.springframework.kafka.test.context.EmbeddedKafka

@SpringBootTest
@EmbeddedKafka(partitions = 1, topics = ["order-tracking-topic"], brokerProperties = ["listeners=PLAINTEXT://localhost:9092", "port=9092"])
class KafkaIntegrationTest {
    @Autowired
    private lateinit var kafkaTemplate: KafkaTemplate<String, Order>

    @Autowired
    private lateinit var embeddedKafkaBroker: EmbeddedKafkaBroker
}
