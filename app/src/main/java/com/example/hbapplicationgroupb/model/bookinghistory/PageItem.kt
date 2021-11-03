package com.example.hbapplicationgroupb.model.bookinghistory

data class PageItem(
    val bookingReference: String,
    val checkIn: String,
    val checkOut: String,
    val hotel: String,
    val id: String,
    val paymentStatus: Boolean,
    val price: Double,
    val roomNumber: Int,
    val roomType: Any
)