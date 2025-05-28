package com.example.ordertrackingsystem.controller

import com.example.ordertrackingsystem.kafka.producer.OrderProducer
import com.example.ordertrackingsystem.model.Order
import com.example.ordertrackingsystem.model.OrderRequest
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.*

@RestController
@RequestMapping("/orders")
class OrderTrackingController (private val producer: OrderProducer) {
    @PostMapping
    fun createOrder(@RequestBody orderRequest: OrderRequest): String {
        val order = Order(
            id = UUID.randomUUID().toString(),
            productName = orderRequest.productName,
            totalQuantity = orderRequest.totalQuantity,
            totalAmount = orderRequest.totalAmount
        )
        producer.sendMessage(order)
        return "Order successfully processed with ID: ${order.id}!"
    }
}