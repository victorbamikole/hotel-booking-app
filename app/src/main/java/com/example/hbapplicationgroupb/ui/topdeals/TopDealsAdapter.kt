package com.example.hbapplicationgroupb.ui.topdeals

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.hbapplicationgroupb.R
import com.example.hbapplicationgroupb.databinding.TopDealRecyclerViewLayoutBinding
import com.example.hbapplicationgroupb.model.topdealsnew.Data

class TopDealsAdapter():
    RecyclerView.Adapter<TopDealsAdapter.DealsViewHolder>(){

    lateinit var listener :SetItemClickListener

    private var topDeals: List<Data> = listOf()
    fun populateTopDeals(list: List<Data>) {
        this.topDeals = list
        notifyDataSetChanged()
    }


    class DealsViewHolder(itemView: View, listener : SetItemClickListener): RecyclerView.ViewHolder(itemView) {
        private val binding: TopDealRecyclerViewLayoutBinding = TopDealRecyclerViewLayoutBinding.bind(itemView)
        val topDealImagei = binding.topDealsRecyclerViewImage
        val topDealName = binding.topDealtopDealNameHotelRecyclerViewSaveText
        val topDealPrice = binding.topDealRecyclerViewPrice
        val topDealRating = binding.topDealRecyclerViewtopDealRating
        val topDealPercent = binding.topDealRecyclerViewtopDealPercent
        val topDealButton = binding.topDealRecyclerviewBookNowButton


        init {
            itemView.setOnClickListener {
                listener.setOnItemClick(adapterPosition,itemView)
            }
        }

        fun populateTopDeals(topDeals: Data) {
            Glide.with(itemView)
                .load(topDeals.thumbnail)
                .into(topDealImagei)

            topDealName.text = topDeals.name
            topDealPrice.text = String.format("$${topDeals.price}")
            topDealRating.text = topDeals.name
            topDealPercent.text = topDeals.percentageRating.toString()


        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DealsViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.top_deal_recycler_view_layout, parent, false)
        return DealsViewHolder(view,listener)
    }

    override fun onBindViewHolder(holder: DealsViewHolder, position: Int) {
        holder.populateTopDeals(topDeals[position])

            holder.itemView.apply {
                holder.topDealButton.setOnClickListener {
                    findNavController().navigate(R.id.action_topDealsFragment_to_bookingDetailsScreenFragment2)
                }

            }
        }

        override fun getItemCount(): Int {
            return topDeals.size
        }

    interface SetItemClickListener{
        fun setOnItemClick(position: Int,myView: View?)
    }

    fun setOnItemClickListener(itemListener : SetItemClickListener){
        listener = itemListener
    }
}

