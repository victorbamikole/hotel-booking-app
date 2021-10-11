package com.example.hbapplicationgroupb.ui.bottom_nav_screens

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.hbapplicationgroupb.R
import com.example.hbapplicationgroupb.databinding.RecyclerviewRowBinding
import com.example.hbapplicationgroupb.model.Hotels
import com.example.hbapplicationgroupb.model.allHotels.Data

class ExploreHomeAdapter() :
    RecyclerView.Adapter<ExploreHomeAdapter.HotelsViewHolder>() {

    private var hotels =  listOf<Data>()
    class HotelsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding: RecyclerviewRowBinding = RecyclerviewRowBinding.bind(itemView)
        val hotelImage = binding.hotelImage
        val hotelName = binding.hotelName
        val hotelPrice = binding.hotelPrice

    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): HotelsViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.recyclerview_row, parent, false)
        return HotelsViewHolder(view)
    }

    override fun onBindViewHolder(holder: HotelsViewHolder, position: Int) {
        holder.itemView.apply {
            val currentItem = hotels[position]

            Glide.with(holder.itemView)
                .load(currentItem.featuredImage)
                .into(holder.hotelImage)
            holder.hotelName.text = currentItem.name
            holder.hotelPrice.text = currentItem.city//.price
        }

    }

    override fun getItemCount(): Int {
        return hotels.size
    }

    fun populateHotels(hotelList : List<Data>){
        hotels = hotelList
    }
}