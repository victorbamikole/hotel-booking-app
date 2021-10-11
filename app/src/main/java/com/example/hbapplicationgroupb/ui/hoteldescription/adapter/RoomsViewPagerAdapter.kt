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

class RoomsViewPagerAdapter : RecyclerView.Adapter<RoomsViewPagerAdapter.ViewPagerHolder>() {

    private var servicesListOfPlaces:MutableList<RoomImageAndDetailForViewPager> = mutableListOf()
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
        val imagePosition = servicesListOfPlaces[position]
        holder.image.setBackgroundResource(imagePosition.image)
        holder.title.text = imagePosition.placeTitle
        holder.price.text = imagePosition.placePrice

    }

    override fun getItemCount(): Int {
        return servicesListOfPlaces.size
    }

    fun getImagesFromExternalSource(list:List<RoomImageAndDetailForViewPager>){
        servicesListOfPlaces.clear()
        servicesListOfPlaces.addAll(list)
        notifyDataSetChanged()
    }
}
