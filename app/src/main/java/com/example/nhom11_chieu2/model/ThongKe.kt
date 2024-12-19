package com.example.nhom11_chieu2.model

data class ThongKe(
    val doanhThu: Double,
    val soLuong: Int,
    val ngayThanhToan: String = System.currentTimeMillis().toString()
)
