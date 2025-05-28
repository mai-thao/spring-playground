package com.example.ordertrackingsystem.kafka.producer

import com.example.ordertrackingsystem.model.Order
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Service

/**
 * See the Spring Kafka docs on using Kafka Template to send messages: https://docs.spring.io/spring-kafka/reference/kafka/sending-messages.html
 */
@Service
class OrderProducer(private val kafkaTemplate: KafkaTemplate<String, Order>) {
    private val topicName: String = "order-tracking-topic"

    fun sendMessage(order: Order) {
        kafkaTemplate.send(topicName, order.id, order)
    }
}