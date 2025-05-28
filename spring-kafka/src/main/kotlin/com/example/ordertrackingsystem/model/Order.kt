package com.example.ordertrackingsystem.model

data class Order(
    val id: String,
    val productName: String,
    val totalQuantity: Int,
    val totalAmount: Double
)
