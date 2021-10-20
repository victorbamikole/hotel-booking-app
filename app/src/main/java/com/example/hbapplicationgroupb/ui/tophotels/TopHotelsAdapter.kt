package com.example.hbapplicationgroupb.ui.tophotels

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.hbapplicationgroupb.R
import com.example.hbapplicationgroupb.databinding.TopHotelsRecyclerviewBinding
import com.example.hbapplicationgroupb.model.topDealAndHotel.TopDealAndHotelData
import com.example.hbapplicationgroupb.model.tophotelresponse.Data

class TopHotelsAdapter() :
    RecyclerView.Adapter<TopHotelsAdapter.HotelsViewHolder>() {
    var tophotels: List<TopDealAndHotelData> = listOf()
    fun populateHotels(list: List<TopDealAndHotelData>) {
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
        fun populateHotels(hotelList : TopDealAndHotelData){
            Glide.with(itemView)
                .load(hotelList.thumbnail)
                .into(topImage)
            topName.text = hotelList.name
            topRating.text = hotelList.description
    "${hotelList.percentageRating}$".also { topPercent.text = it }

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