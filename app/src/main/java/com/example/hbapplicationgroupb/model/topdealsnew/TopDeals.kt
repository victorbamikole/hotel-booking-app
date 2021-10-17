package com.example.hbapplicationgroupb.model.topdealsnew

data class TopDeals(
    val `data`: List<TopDealData>,
    val message: String,
    val statusCode: Int,
    val succeeded: Boolean
)