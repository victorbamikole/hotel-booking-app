package com.example.hbapplicationgroupb.model.emailconfirmation

data class ConfirmEmailAddressResponse(
    val data: String,
    val message: String,
    val statusCode: Int,
    val succeeded: Boolean
)