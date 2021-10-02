package com.example.hbapplicationgroupb.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.hbapplicationgroupb.R
import com.example.hbapplicationgroupb.databinding.RecyclerviewRowBinding
import com.example.hbapplicationgroupb.model.Hotels

class ExploreHomeAdapter(var hotels: List<Hotels>) :
    RecyclerView.Adapter<ExploreHomeAdapter.HotelsViewHolder>() {

    class HotelsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding: RecyclerviewRowBinding = RecyclerviewRowBinding.bind(itemView)
        val hotelImage = binding.hotelImage
        val hotelName = binding.hotelName
        val hotelPrice = binding.hotelPrice

    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): ExploreHomeAdapter.HotelsViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.recyclerview_row, parent, false)
        return HotelsViewHolder(view)
    }

    override fun onBindViewHolder(holder: ExploreHomeAdapter.HotelsViewHolder, position: Int) {
        holder.itemView.apply {
            val currentItem = hotels[position]
            holder.hotelImage.setImageResource(currentItem.hotelImage)
            holder.hotelName.text = currentItem.name
            holder.hotelPrice.text = currentItem.price
        }

    }

    override fun getItemCount(): Int {
        return hotels.size
    }
}