package com.example.hbapplicationgroupb.ui.hoteldescription.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.hbapplicationgroupb.R
import com.example.hbapplicationgroupb.model.hotelDescriptionData.HotelDescriptionRoomType

class RoomsViewPagerAdapter : RecyclerView.Adapter<RoomsViewPagerAdapter.ViewPagerHolder>() {

    private val hotelRooms :  MutableList<HotelDescriptionRoomType> = mutableListOf()

    inner class ViewPagerHolder(viewItems: View): RecyclerView.ViewHolder(viewItems){
        val image: ImageView = viewItems.findViewById(R.id.bottom_viewPager_imageView)
        val title: TextView = viewItems.findViewById(R.id.placeName_onViewPager)
        val price: TextView = viewItems.findViewById(R.id.price_onViewPager)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewPagerHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.room_viewpager_design,parent,false)
        return ViewPagerHolder(view)
    }

    override fun onBindViewHolder(holder: ViewPagerHolder, position: Int) {
        val roomPosition = hotelRooms[position]

        Glide.with(holder.itemView)
            .load(roomPosition.thumbnail)
            .into(holder.image)
        holder.title.text = roomPosition.name
        holder.price.text = String.format("#${roomPosition.price}")
    }

    override fun getItemCount(): Int {
        return hotelRooms.size
    }

    fun populateHotelRooms(list:MutableList<HotelDescriptionRoomType>){

        hotelRooms.addAll(list)

        notifyDataSetChanged()
    }
}
