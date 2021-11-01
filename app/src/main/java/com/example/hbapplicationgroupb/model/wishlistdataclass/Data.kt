package com.example.hbapplicationgroupb.model.wishlistdataclass

data class Data(
    val currentPage: Int,
    val numberOfPages: Int,
    val pageItems: List<PageItem>,
    val pageSize: Int,
    val previousPage: Int
)