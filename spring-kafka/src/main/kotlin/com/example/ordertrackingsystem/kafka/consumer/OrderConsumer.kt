package com.example.ordertrackingsystem.kafka.consumer

import com.example.ordertrackingsystem.model.Order
import org.slf4j.LoggerFactory
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Component

/**
 * @KafkaListener registers the consume() method as a kafka listener consuming messages from the specified topic
 *
 * This consumer simply consume and log messages and does not contain business logic so it's not a @Service
 */
@Component
class OrderConsumer {
    // Create a Logger instance named after the class so logs are categorized by class names
    private val logger = LoggerFactory.getLogger(OrderConsumer::class.java)

    @KafkaListener(topics = ["\${spring.kafka.template.default-topic}"])
    fun consume(order: Order) {
        logger.info("Consumed message: $order")
    }
}