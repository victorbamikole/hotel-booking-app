package com.example.hbapplicationgroupb.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.hbapplicationgroupb.R
import com.example.hbapplicationgroupb.databinding.TopHotelsRecyclerviewBinding
import com.example.hbapplicationgroupb.model.TopHotels

class TopHotelsAdapter(var tophotels: List<TopHotels>) :
    RecyclerView.Adapter<TopHotelsAdapter.HotelsViewHolder>() {


    class HotelsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding: TopHotelsRecyclerviewBinding =
            TopHotelsRecyclerviewBinding.bind(itemView)
        val topImage = binding.topHotelImage
        val topName = binding.topHotelName
        val topPrice = binding.topHotelPrice
        val topRating = binding.topHotelRating
        val topPercent = binding.topHotelPercent
        val bookTopHotel = binding.bookTopHotel
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HotelsViewHolder {
        val view =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.top_hotels_recyclerview, parent, false)
        return HotelsViewHolder(view)
    }

    override fun onBindViewHolder(holder: HotelsViewHolder, position: Int) {
        holder.itemView.apply {
            val currentItem = tophotels[position]
            holder.topImage.setImageResource(currentItem.hotelImage)
            holder.topName.text = currentItem.name
            holder.topPrice.text = currentItem.price
            holder.topRating.text = currentItem.rating
            holder.topPercent.text = currentItem.percent
            holder.bookTopHotel.setOnClickListener {
                findNavController().navigate(R.id.action_topHotelsFragment_to_bookingDetailsScreenFragment2)
            }
            setOnClickListener {
                findNavController().navigate(R.id.hotelDescriptionFragment)
            }
        }
    }

    override fun getItemCount(): Int {
        return tophotels.size
    }
}