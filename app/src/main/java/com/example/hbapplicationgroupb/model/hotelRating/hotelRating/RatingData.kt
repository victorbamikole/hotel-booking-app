package com.example.hbapplicationgroupb.model.hotelRating.hotelRating

data class RatingData(
    val currentPage: Int,
    val numberOfPages: Int,
    val pageItems: List<PageItems>,
    val pageSize: Int,
    val previousPage: Int
)