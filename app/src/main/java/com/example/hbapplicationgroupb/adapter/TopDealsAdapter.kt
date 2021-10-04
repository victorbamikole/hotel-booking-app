package com.example.hbapplicationgroupb.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.hbapplicationgroupb.R
import com.example.hbapplicationgroupb.databinding.TopDealsRecyclerviewBinding
import com.example.hbapplicationgroupb.databinding.TopHotelsRecyclerviewBinding
import com.example.hbapplicationgroupb.model.TopHotels

class TopDealsAdapter(var topDeals: List<TopHotels>):
    RecyclerView.Adapter<TopDealsAdapter.DealsViewHolder>(){
    class DealsViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        private val binding: TopDealsRecyclerviewBinding =
            TopDealsRecyclerviewBinding.bind(itemView)
        val topDealImage = binding.topDealImage
        val topDealName = binding.topDealName
        val topDealPrice = binding.topDealPrice
        val topDealRating = binding.topDealRating
        val topDealPercent = binding.topDealPercent
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DealsViewHolder {
        val view =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.top_deals_recyclerview, parent, false)
        return TopDealsAdapter.DealsViewHolder(view)
    }

    override fun onBindViewHolder(holder: DealsViewHolder, position: Int) {
        holder.itemView.apply {
            val currentItem = topDeals[position]
            holder.topDealImage.setImageResource(currentItem.hotelImage)
            holder.topDealName.text = currentItem.name
            holder.topDealPrice.text = currentItem.price
            holder.topDealRating.text = currentItem.rating
            holder.topDealPercent.text = currentItem.percent
            setOnClickListener {
                findNavController().navigate(R.id.hotelDescriptionFragment)
            }
        }
    }

    override fun getItemCount(): Int {
        return topDeals.size
    }
}