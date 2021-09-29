package com.example.hbapplicationgroupb.util

import com.example.hbapplicationgroupb.R
import com.example.hbapplicationgroupb.model.HotelImagesForViewPager
import com.example.hbapplicationgroupb.model.RoomImageAndDetailForViewPager
import com.example.hbapplicationgroupb.model.UserReview

fun getListOfHotelImages():List<HotelImagesForViewPager>{
    return listOf(
        HotelImagesForViewPager(R.drawable.image_hotel_6),
        HotelImagesForViewPager(R.drawable.image_hotel_5),
        HotelImagesForViewPager(R.drawable.image_hotel_4),
        HotelImagesForViewPager(R.drawable.image_hotel_3),
        HotelImagesForViewPager(R.drawable.image_hotel_2),
        HotelImagesForViewPager(R.drawable.image_hotel_1)
    )
}

fun getListForBottomViewPager():List<RoomImageAndDetailForViewPager>{
    return listOf(
        RoomImageAndDetailForViewPager(R.drawable.image_hotel_6,"Single Deluxe Room","$69900"),
        RoomImageAndDetailForViewPager(R.drawable.image_hotel_5,"Double Deluxe Room","$90000"),
        RoomImageAndDetailForViewPager(R.drawable.image_hotel_4,"Triple Deluxe Room","$100000"),
        RoomImageAndDetailForViewPager(R.drawable.image_hotel_3,"Johnson Deluxe Room","$700000"),
        RoomImageAndDetailForViewPager(R.drawable.image_hotel_2,"Yeah Deluxe Room","$30"),
        RoomImageAndDetailForViewPager(R.drawable.image_hotel_1,"Require Deluxe Room","$700")
    )
}
fun getListOfUserReview():List<UserReview>{
    return listOf(
        UserReview("Johnson",4,R.drawable.image_hotel_6,"good product"),
        UserReview("Johnson",4,R.drawable.image_hotel_5,"good product"),
        UserReview("Johnson",4,R.drawable.image_hotel_4,"good product"),
        UserReview("Johnson",4,R.drawable.image_hotel_3,"good product"),
        UserReview("Johnson",4,R.drawable.image_hotel_2,"good product"),
        UserReview("Johnson",4,R.drawable.image_hotel_1,"good product")
    )
}