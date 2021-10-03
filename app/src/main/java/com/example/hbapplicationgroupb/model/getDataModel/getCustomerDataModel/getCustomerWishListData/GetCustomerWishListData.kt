package com.example.hbapplicationgroupb.model.getDataModel.getCustomerDataModel.getCustomerWishListData

data class GetCustomerWishListData(
    val statusCode: String,
    val success: Boolean,
    val data: List<GetCustomerWishListDataItem>,
    val message: String,
    val errors: String? = null
)
