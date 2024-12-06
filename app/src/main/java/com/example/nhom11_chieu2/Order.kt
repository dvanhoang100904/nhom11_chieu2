package com.example.nhom11_chieu2

data class Order(
    val ma: Int,
    val ten: String,
    val hinhAnh: Int,
    val gia: Double,
    var soLuong: Int,
    val moTa: String,
    val maDoUong: Int,
)