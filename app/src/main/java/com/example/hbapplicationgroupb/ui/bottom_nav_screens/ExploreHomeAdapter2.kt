package com.example.hbapplicationgroupb.ui.bottom_nav_screens

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.hbapplicationgroupb.R
import com.example.hbapplicationgroupb.databinding.RecyclerviewRowBinding
import com.example.hbapplicationgroupb.model.topdealsnew.Data
import com.example.hbapplicationgroupb.model.topdealsnew.TopDeals

class ExploreHomeAdapter2() :
    RecyclerView.Adapter<ExploreHomeAdapter2.HotelsViewHolder>() {

    private var hotels: List<Data> = listOf()
    fun populateTopDeals(list: List<Data>) {
        this.hotels = list
        notifyDataSetChanged()
    }


    class HotelsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding: RecyclerviewRowBinding = RecyclerviewRowBinding.bind(itemView)
        val hotelImage = binding.hotelImage
        val hotelName = binding.hotelName
        val hotelPrice = binding.hotelPrice

        fun populateTopDeals(topDeals: Data) {
            Glide.with(itemView)
                .load(topDeals.thumbnail)
                .into(hotelImage)
            hotelName.text = topDeals.hotelName
            hotelPrice.text = topDeals.price.toString()//.price
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


