package com.example.hbapplicationgroupb.ui.bottom_nav_screens

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.hbapplicationgroupb.R
import com.example.hbapplicationgroupb.databinding.RecyclerviewRowBinding
import com.example.hbapplicationgroupb.model.topDealAndHotel.TopDealAndHotelData

class ExploreHomeAdapter2() :
    RecyclerView.Adapter<ExploreHomeAdapter2.HotelsViewHolder>() {

    private var hotels: List<TopDealAndHotelData> = listOf()
    fun populateTopDeals(list: List<TopDealAndHotelData>) {
        this.hotels = list
        notifyDataSetChanged()
    }


    class HotelsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding: RecyclerviewRowBinding = RecyclerviewRowBinding.bind(itemView)
        val hotelImage = binding.hotelImage
        val hotelName = binding.hotelName
        val hotelPrice = binding.hotelPrice

        fun populateTopDeals(topDealsAndHotel: TopDealAndHotelData) {
            Glide.with(itemView)
                .load(topDealsAndHotel.thumbnail)
                .into(hotelImage)
            hotelName.text = topDealsAndHotel.name
            hotelPrice.text = topDealsAndHotel.price.toString()//.price
        }

    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): HotelsViewHolder {
        val view =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.recyclerview2_explore_screen, parent, false)
        return HotelsViewHolder(view)
    }

    override fun onBindViewHolder(holder: HotelsViewHolder, position: Int) {
        holder.populateTopDeals(hotels[position])
    }

    override fun getItemCount(): Int {
        return hotels.size
    }
}


