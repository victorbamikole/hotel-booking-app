package com.example.hbapplicationgroupb.model.getDataModel.getCustomerDataModel.getCustomerWishListData

data class GetCustomerWishListData(
    val statusCode: String,
    val success: Boolean,
    val Data: List<GetCustomerWishListDataItem>,
    val Message: String,
    val errors: String? = null
)
