package com.example.ordertrackingsystem

import com.example.ordertrackingsystem.model.Order
import org.apache.kafka.common.serialization.StringDeserializer
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.kafka.core.DefaultKafkaConsumerFactory
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.kafka.support.serializer.JsonDeserializer
import org.springframework.kafka.test.EmbeddedKafkaBroker
import org.springframework.kafka.test.context.EmbeddedKafka
import org.springframework.kafka.test.utils.KafkaTestUtils

@SpringBootTest
@EmbeddedKafka(partitions = 1, topics = ["order-tracking-topic"])
class KafkaIntegrationTest(
    @Autowired private var kafkaTemplate: KafkaTemplate<String, Order>,
    @Autowired private var embeddedKafkaBroker: EmbeddedKafkaBroker
) {

    private val topic: String = "order-tracking-topic"

    @Test
    fun `should send and receive order object`() {
        // Arrange
        val order = Order(id = "123", productName = "Monopoly Boardgame", totalAmount = 19.99, totalQuantity = 1)

        val consumerProps = KafkaTestUtils.consumerProps("test-group-id", "true", embeddedKafkaBroker)
        consumerProps["key.deserializer"] = StringDeserializer::class.java
        consumerProps["value.deserializer"] = JsonDeserializer::class.java
        consumerProps["spring.json.trusted.packages"] = "*"

        val consumer = DefaultKafkaConsumerFactory<String, Order>(consumerProps).createConsumer()
        embeddedKafkaBroker.consumeFromAnEmbeddedTopic(consumer, topic)

        // Act
        kafkaTemplate.send(topic, order)

        // Assert
        val record = KafkaTestUtils.getSingleRecord(consumer, topic)
        assertThat(record.value()).isEqualTo(order)
    }
}
