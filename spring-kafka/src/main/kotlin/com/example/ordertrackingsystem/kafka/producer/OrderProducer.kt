package com.example.ordertrackingsystem.kafka.producer

import com.example.ordertrackingsystem.model.Order
import org.springframework.beans.factory.annotation.Value
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Service

/**
 * See the Spring Kafka docs on using Kafka Template to send messages: https://docs.spring.io/spring-kafka/reference/kafka/sending-messages.html
 *
 * @Value is used to inject externalized properties from application.yml config file
 */
@Service
class OrderProducer(
    private val kafkaTemplate: KafkaTemplate<String, Order>,
    @Value("\${spring.kafka.template.default-topic}") private val topicName: String
) {
    fun sendMessage(order: Order) {
        kafkaTemplate.send(topicName, order.id, order)
        println("Message with Order ID ${order.id} successfully sent to topic: $topicName")
    }
}