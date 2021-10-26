package com.example.hbapplicationgroupb.ui.topdeals

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.hbapplicationgroupb.R
import com.example.hbapplicationgroupb.databinding.TopDealRecyclerViewLayoutBinding
import com.example.hbapplicationgroupb.model.allhotel.PageItem
import com.example.hbapplicationgroupb.model.topDealAndHotel.TopDealAndHotelData

class TopDealsAdapter():
    RecyclerView.Adapter<TopDealsAdapter.DealsViewHolder>(){

    lateinit var listener :SetItemClickListener

    private var topDeals: List<TopDealAndHotelData> = listOf()
    fun populateTopDeals(list: List<TopDealAndHotelData>) {
        this.topDeals = list
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
        val saveText = binding.topDealSaveHotelRecyclerViewSaveText
        val saveImage = binding.topDealSaveHotelRecyclerViewImage
        val layoutForToggle = binding.layoutForSaving


        init {
            itemView.setOnClickListener {
                listener.setOnItemClick(adapterPosition,itemView)
            }
        }

        fun populateTopDeals(topDealsAndHotel: TopDealAndHotelData) {
            Glide.with(itemView)
                .load(topDealsAndHotel.thumbnail)
                .into(topDealImage)


            topDealName.text = topDealsAndHotel.name
            topDealPrice.text = "${topDealsAndHotel.price}"
            "${topDealsAndHotel.percentageRating}%".also { topDealPercent.text = it }
            topDealRating.text = topDealsAndHotel.description


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
                    val name = topDeals[position].name
                    val action = TopDealsFragmentDirections
                        .actionTopDealsFragmentToBookingDetailsScreenFragment2(name)
                    findNavController().navigate(action)
                }

            }
        holder.itemView.apply {
            holder.layoutForToggle.setOnClickListener {
                listener.toggleSaveItemToWishList(position, holder.saveText,holder.saveImage,topDeals[position])
            }
        }
        }

        override fun getItemCount(): Int {
            return topDeals.size
        }

    interface SetItemClickListener{
        fun setOnItemClick(position: Int,myView: View?)
        fun toggleSaveItemToWishList(position: Int, saveItemTextBox: TextView, saveItemImage: ImageView, item: TopDealAndHotelData)
    }

    fun setOnItemClickListener(itemListener : SetItemClickListener){
        listener = itemListener
    }
}

