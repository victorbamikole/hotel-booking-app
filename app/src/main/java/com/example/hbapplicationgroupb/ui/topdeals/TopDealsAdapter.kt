package com.example.hbapplicationgroupb.ui.topdeals

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.hbapplicationgroupb.R
import com.example.hbapplicationgroupb.databinding.TopDealRecyclerViewLayoutBinding
import com.example.hbapplicationgroupb.databinding.TopDealsRecyclerviewBinding
import com.example.hbapplicationgroupb.model.TopHotels
import com.example.hbapplicationgroupb.model.topdealsnew.Data

class TopDealsAdapter():
    RecyclerView.Adapter<TopDealsAdapter.DealsViewHolder>(){

    private var topDeals: List<Data> = listOf()
    fun populateTopDeals(list: List<Data>) {
        this.topDeals = list
        notifyDataSetChanged()
    }


    class DealsViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        private val binding: TopDealRecyclerViewLayoutBinding = TopDealRecyclerViewLayoutBinding.bind(itemView)
        val topDealImagei = binding.topDealsRecyclerViewImage
        val topDealName = binding.topDealtopDealNameHotelRecyclerViewSaveText
        val topDealPrice = binding.topDealRecyclerViewPrice
        val topDealRating = binding.topDealRecyclerViewtopDealRating
        val topDealPercent = binding.topDealRecyclerViewtopDealPercent
        val topDealButton = binding.topDealRecyclerviewBookNowButton

        fun populateTopDeals(topDeals: Data) {
            Glide.with(itemView)
                .load(topDeals.thumbnail)
                .into(topDealImagei)

            topDealName.text = topDeals.hotelName
            topDealPrice.text = String.format("$${topDeals.price}")
            topDealRating.text = topDeals.name
            topDealPercent.text = topDeals.discountPrice.toString()


        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DealsViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.top_deal_recycler_view_layout, parent, false)
        return DealsViewHolder(view)
    }

    override fun onBindViewHolder(holder: DealsViewHolder, position: Int) {
        holder.populateTopDeals(topDeals[position])
        val hotelId = topDeals[position].hotelId
        val hotelPrice = topDeals[position].price


        holder.itemView.apply {
            holder.topDealButton.setOnClickListener {
                findNavController().navigate(R.id.action_topDealsFragment_to_bookingDetailsScreenFragment2)
            }
            setOnClickListener {
                val action = TopDealsFragmentDirections
                    .actionTopDealsFragmentToHotelDescriptionFragment(hotelId)
                findNavController().navigate(action)
            }
        }
        }

        override fun getItemCount(): Int {
            return topDeals.size
        }
    }

