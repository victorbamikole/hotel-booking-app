package com.example.hbapplicationgroupb.model.topdealsdata

data class ListOfTopDealsResponse(
    val statusCode: String,
    val success: Boolean,
    val data: List<ListOfTopDealsItem>,
    val message: String,
    val errors: String? = null

)