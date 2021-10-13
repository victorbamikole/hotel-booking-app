package com.example.hbapplicationgroupb.ui.hoteldescription.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.hbapplicationgroupb.R
import com.example.hbapplicationgroupb.model.RoomImageAndDetailForViewPager
import com.example.hbapplicationgroupb.model.hotelDescriptionData.HotelDescriptionRoomType

class RoomsViewPagerAdapter : RecyclerView.Adapter<RoomsViewPagerAdapter.ViewPagerHolder>() {

    private lateinit var hotelRooms : List<HotelDescriptionRoomType>

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
<<<<<<< HEAD
        val imagePosition = servicesListOfPlaces[position]
        holder.image.setBackgroundResource(imagePosition.image)
        holder.title.text = imagePosition.placeTitle
        holder.price.text = imagePosition.placePrice

=======
        val imagePosition = hotelRooms[position]
        Glide.with(holder.itemView)
            .load(imagePosition.thumbnail)
            .into(holder.image)
        holder.title.text = imagePosition.name
        holder.price.text = imagePosition.price.toString()
>>>>>>> a383825c4d155602e1aed08b5d5d03c92eeb5e92
    }

    override fun getItemCount(): Int {
        return hotelRooms.size
    }

    fun populateHotelRooms(list:List<HotelDescriptionRoomType>?){
        if (list != null) {
            hotelRooms = list
        }
        notifyDataSetChanged()
    }
}
