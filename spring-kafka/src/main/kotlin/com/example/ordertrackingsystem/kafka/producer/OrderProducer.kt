package com.example.ordertrackingsystem.kafka.producer

import com.example.ordertrackingsystem.kafka.consumer.OrderConsumer
import com.example.ordertrackingsystem.model.Order
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Component

/**
 * @Component tells Spring to treat this class as a bean so it can scan and initialize it
 * @Value is used to inject externalized properties from application.yml config file
 *
 * This producer handles simple infrastructure-level message dispatching and does not contain business logic so it's not a @Service
 * See the Spring Kafka docs on using Kafka Template to send messages: https://docs.spring.io/spring-kafka/reference/kafka/sending-messages.html
 */
@Component
class OrderProducer(
    private val kafkaTemplate: KafkaTemplate<String, Order>,
    @Value("\${spring.kafka.template.default-topic}") private val topicName: String
) {
    private val logger = LoggerFactory.getLogger(OrderProducer::class.java)

    fun sendMessage(order: Order) {
        kafkaTemplate.send(topicName, order.id, order)
        logger.info("Message with Order ID: ${order.id} successfully sent to topic: $topicName")
    }
}