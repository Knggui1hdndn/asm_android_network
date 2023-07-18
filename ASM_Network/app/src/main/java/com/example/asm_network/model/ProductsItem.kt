package com.example.asm_network.model

data class ProductsItem(
    val _id: String,
    val company: Company,
    val image: String,
    val import_date: String,
    val name: String,
    val price: Double,
    val quantity: Int
)