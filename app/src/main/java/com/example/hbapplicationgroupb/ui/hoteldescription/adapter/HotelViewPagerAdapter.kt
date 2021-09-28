package com.example.hbapplicationgroupb.ui.hoteldescription.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.hbapplicationgroupb.R
import com.example.hbapplicationgroupb.model.HotelImagesForViewPager

class HotelViewPagerAdapter : RecyclerView.Adapter<HotelViewPagerAdapter.ViewPagerHolder>() {
    private var contents:MutableList<HotelImagesForViewPager> = mutableListOf()
    inner class ViewPagerHolder(viewItems: View): RecyclerView.ViewHolder(viewItems){
        val image: ImageView = viewItems.findViewById(R.id.fragment_details_page_rv_imageView)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewPagerHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.hotel_view_pager_design,parent,false)
        return ViewPagerHolder(view)
    }

    override fun onBindViewHolder(holder: ViewPagerHolder, position: Int) {
        val imagePosition = contents[position]
        holder.image.setBackgroundResource(imagePosition.imageData)

    }

    override fun getItemCount(): Int {
        return contents.size
    }

    fun getImagesFromExternalSource(list:List<HotelImagesForViewPager>){
        contents.clear()
        contents.addAll(list)
        notifyDataSetChanged()
    }


}