package com.example.hbapplicationgroupb.ui.hoteldescription.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.hbapplicationgroupb.R
import com.example.hbapplicationgroupb.model.HotelImagesForViewPager

class HotelViewPagerAdapter : RecyclerView.Adapter<HotelViewPagerAdapter.ViewPagerHolder>() {
    private var hotelImages:ArrayList<String> = arrayListOf()
    inner class ViewPagerHolder(viewItems: View): RecyclerView.ViewHolder(viewItems){
        val image: ImageView = viewItems.findViewById(R.id.fragment_details_page_rv_imageView)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewPagerHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.hotel_view_pager_design,parent,false)
        return ViewPagerHolder(view)
    }

    override fun onBindViewHolder(holder: ViewPagerHolder, position: Int) {
        Glide.with(holder.itemView)
            .load(hotelImages[position])
            .into(holder.image)
    }

    override fun getItemCount(): Int {
        return hotelImages.size
    }

    fun getImagesFromExternalSource(list:List<String>){
        hotelImages.clear()
        hotelImages.addAll(list)
        notifyDataSetChanged()
    }


}