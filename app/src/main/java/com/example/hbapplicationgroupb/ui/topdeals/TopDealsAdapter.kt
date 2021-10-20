package com.example.hbapplicationgroupb.ui.topdeals

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.hbapplicationgroupb.R
import com.example.hbapplicationgroupb.databinding.TopDealRecyclerViewLayoutBinding
import com.example.hbapplicationgroupb.model.topDealAndHotel.TopDealAndHotelData

class TopDealsAdapter():
    RecyclerView.Adapter<TopDealsAdapter.DealsViewHolder>(){

    lateinit var listener :SetItemClickListener

    private var topDealAndHotels: List<TopDealAndHotelData> = listOf()
    fun populateTopDeals(list: List<TopDealAndHotelData>) {
        this.topDealAndHotels = list
        notifyDataSetChanged()
    }


    class DealsViewHolder(itemView: View, listener : SetItemClickListener): RecyclerView.ViewHolder(itemView) {
        private val binding: TopDealRecyclerViewLayoutBinding = TopDealRecyclerViewLayoutBinding.bind(itemView)
        private val topDealImage = binding.topDealsRecyclerViewImage
        private val topDealName = binding.topDealtopDealNameHotelRecyclerViewSaveText
        private val topDealPrice = binding.topDealRecyclerViewPrice
        private val topDealRating = binding.topDealRecyclerViewtopDealRating
        private val topDealPercent = binding.topDealRecyclerViewtopDealPercent
        val topDealButton = binding.topDealRecyclerviewBookNowButton


        init {
            itemView.setOnClickListener {
                listener.setOnItemClick(adapterPosition,itemView)
            }
        }

        fun populateTopDeals(topDealsAndHotel: TopDealAndHotelData) {
            Glide.with(itemView)
                .load(topDealsAndHotel.thumbnail)
                .into(topDealImage)

            if (topDealsAndHotel.name.length > 15){
                topDealName.text = String.format("${topDealsAndHotel.name.substring(0,15)}...")
            } else{
                topDealName.text = topDealsAndHotel.name
            }
            topDealPrice.text = String.format("$${topDealsAndHotel.price}")
            topDealRating.text = topDealsAndHotel.name
            topDealPercent.text = topDealsAndHotel.percentageRating.toString()


        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DealsViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.top_deal_recycler_view_layout, parent, false)
        return DealsViewHolder(view,listener)
    }

    override fun onBindViewHolder(holder: DealsViewHolder, position: Int) {
        holder.populateTopDeals(topDealAndHotels[position])

            holder.itemView.apply {
                holder.topDealButton.setOnClickListener {
                    findNavController().navigate(R.id.action_topDealsFragment_to_bookingDetailsScreenFragment2)
                }

            }
        }

        override fun getItemCount(): Int {
            return topDealAndHotels.size
        }

    interface SetItemClickListener{
        fun setOnItemClick(position: Int,myView: View?)
    }

    fun setOnItemClickListener(itemListener : SetItemClickListener){
        listener = itemListener
    }
}

