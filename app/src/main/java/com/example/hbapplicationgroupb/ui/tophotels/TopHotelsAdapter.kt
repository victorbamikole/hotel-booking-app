package com.example.hbapplicationgroupb.ui.tophotels

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.hbapplicationgroupb.R
import com.example.hbapplicationgroupb.databinding.TopHotelsRecyclerviewBinding
import com.example.hbapplicationgroupb.model.tophoteldata.HotelTopDealItems
import com.example.hbapplicationgroupb.model.tophotelresponse.AllTopHotels
import com.example.hbapplicationgroupb.model.tophotelresponse.Data

class TopHotelsAdapter() :
    RecyclerView.Adapter<TopHotelsAdapter.HotelsViewHolder>() {
    var tophotels: List<Data> = listOf()
    fun populateHotels(list: List<Data>) {
        this.tophotels = list
        notifyDataSetChanged()
    }

    class HotelsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding: TopHotelsRecyclerviewBinding =
            TopHotelsRecyclerviewBinding.bind(itemView)
        val topImage = binding.topHotelImage
        val topName = binding.topHotelName
        val topPrice = binding.topHotelPrice
        val topRating = binding.topHotelRating
        val topPercent = binding.topHotelPercent
        val bookTopHotel = binding.bookTopHotel
//
        fun populateHotels(hotelList : Data){
            Glide.with(itemView)
                .load(hotelList.thumbnail)
                .into(topImage)
            topName.text = hotelList.name
            topRating.text = hotelList.rating.toString()
            topPercent.text = hotelList.numberOfReviews.toString()

//            topPrice.text = hotelList.
//            topPrice.text = hotelList.roomTypes[0].price.toString()//.price
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HotelsViewHolder {
        val view =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.top_hotels_recyclerview, parent, false)
        return HotelsViewHolder(view)
    }

    override fun onBindViewHolder(holder: HotelsViewHolder, position: Int) {
        holder.populateHotels(tophotels[position])

    }

    override fun getItemCount(): Int {
        return tophotels.size
    }
}