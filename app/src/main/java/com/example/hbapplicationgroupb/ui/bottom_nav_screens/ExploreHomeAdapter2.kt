package com.example.hbapplicationgroupb.ui.bottom_nav_screens

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.hbapplicationgroupb.R
import com.example.hbapplicationgroupb.databinding.RecyclerviewRowBinding
import com.example.hbapplicationgroupb.model.topDealAndHotel.TopDealAndHotelData

class ExploreHomeAdapter2(val click: OnItemClickListener) :
    RecyclerView.Adapter<ExploreHomeAdapter2.HotelsViewHolder>() {


    private var hotels: List<TopDealAndHotelData> = listOf()
    fun populateTopDeals(list: List<TopDealAndHotelData>) {
        this.hotels = list
        notifyDataSetChanged()
    }


    inner class HotelsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val binding: RecyclerviewRowBinding =
            RecyclerviewRowBinding.bind(itemView)
        private val hotelImage = binding.hotelImage
        val hotelName = binding.hotelName
        private val hotelRatingStar = binding.exploreFragmentHotelRatingStar
        private val hotelAddress = binding.hotelAddress
        val layout2 = binding.topHotelLayout

        init {
            itemView.setOnClickListener {
                val position = adapterPosition
                val data: TopDealAndHotelData = hotels[position]
                click.onItemClick(position, data)
            }
        }

        fun populateTopDeals(topDealsAndHotel: TopDealAndHotelData) {
            Glide.with(itemView)
                .load(topDealsAndHotel.thumbnail)
                .into(hotelImage)
            hotelName.text = topDealsAndHotel.name
            hotelAddress.text = topDealsAndHotel.address
            hotelRatingStar.rating = ((topDealsAndHotel.percentageRating/100) * 5).toFloat()
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): HotelsViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.recyclerview_row, parent, false)
        return HotelsViewHolder(view)
    }

    override fun onBindViewHolder(holder: HotelsViewHolder, position: Int) {
        holder.populateTopDeals(hotels[position])

    }

    override fun getItemCount(): Int {
        return hotels.size
    }

}

interface OnItemClickListener {
    fun onItemClick(position: Int, objectData: TopDealAndHotelData)//

}


