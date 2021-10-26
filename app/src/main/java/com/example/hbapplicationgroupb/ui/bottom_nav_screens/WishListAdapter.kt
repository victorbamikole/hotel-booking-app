package com.example.hbapplicationgroupb.ui.bottom_nav_screens

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.hbapplicationgroupb.databinding.AllHotelsRecyclerviewLayoutBinding
import com.example.hbapplicationgroupb.databinding.WishListListItemLayoutBinding
import com.example.hbapplicationgroupb.model.dataclass.WishListDataClass

class WishListAdapter:RecyclerView.Adapter<WishListAdapter.MyViewHolder>() {
    inner class MyViewHolder(var binding: AllHotelsRecyclerviewLayoutBinding)
        :RecyclerView.ViewHolder(binding.root)

    private val diffCallBack = object : DiffUtil.ItemCallback<WishListDataClass>(){
        override fun areItemsTheSame(
            oldItem: WishListDataClass,
            newItem: WishListDataClass
        ): Boolean {
            return oldItem.hotelName == newItem.hotelName
        }

        override fun areContentsTheSame(
            oldItem: WishListDataClass,
            newItem: WishListDataClass
        ): Boolean {
            return newItem == oldItem
        }
    }

    private val differ = AsyncListDiffer(this, diffCallBack)
    var hotels : List<WishListDataClass>
        get() = differ.currentList
        set(value) {
            differ.submitList(value)
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            AllHotelsRecyclerviewLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        var currentHotel = hotels[position]

        holder.binding.apply {
            topDealtopDealNameHotelRecyclerViewSaveText.text = currentHotel.hotelName
            topDealRecyclerViewPrice.text = currentHotel.hotelPrice.toString()
            topDealRecyclerViewtopDealRating.text = currentHotel.description
            topDealRecyclerViewtopDealPercent.text = currentHotel.percentage
        }
    }

    override fun getItemCount(): Int {
        return hotels.size
    }
}