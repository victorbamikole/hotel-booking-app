package com.example.hbapplicationgroupb.ui.tophotels

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.hbapplicationgroupb.R
import com.example.hbapplicationgroupb.databinding.TopDealRecyclerViewLayoutBinding
import com.example.hbapplicationgroupb.model.topDealAndHotel.TopDealAndHotelData


class TopHotelsAdapter() :
    RecyclerView.Adapter<TopHotelsAdapter.HotelsViewHolder>() {
    private lateinit var listener: TopHotelClickListener

    class HotelsViewHolder(itemView: View,private val topHotelClickListener: TopHotelClickListener) : RecyclerView.ViewHolder(itemView){
        private val binding: TopDealRecyclerViewLayoutBinding =
            TopDealRecyclerViewLayoutBinding.bind(itemView)
        private val topImage = binding.topDealsRecyclerViewImage
        private val topName = binding.topDealtopDealNameHotelRecyclerViewSaveText
        private val topPrice = binding.topDealRecyclerViewPrice
        private val description = binding.topDealRecyclerViewtopDealRating
        private val topPercent = binding.topDealRecyclerViewtopDealPercent
        val bookTopHotelNow = binding.topDealRecyclerviewBookNowButton
        val saveText = binding.topDealSaveHotelRecyclerViewSaveText
        val saveImage = binding.topDealSaveHotelRecyclerViewImage
        val layoutForToggle = binding.layoutForSaving

        fun bind(topHotel : TopDealAndHotelData){
            itemView.setOnClickListener {
                topHotelClickListener.onItemSelected(adapterPosition,topHotel)
            }
            Glide.with(itemView)
                .load(topHotel.thumbnail)
                .into(topImage)
            "${topHotel.percentageRating}%".also { topPercent.text = it }
            topPrice.text = "$${topHotel.price}"
            topHotel.price.toString() .also { topPrice.text = it }
            description.text = topHotel.description
            topName.text = topHotel.name
        }
    }
    private val differCallBack = object : DiffUtil.ItemCallback<TopDealAndHotelData>(){
        override fun areItemsTheSame(oldItem: TopDealAndHotelData, newItem: TopDealAndHotelData) = oldItem.id == newItem.id
        override fun areContentsTheSame(oldItem: TopDealAndHotelData, newItem: TopDealAndHotelData) = oldItem == newItem
    }
    val differ = AsyncListDiffer(this,differCallBack)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HotelsViewHolder {
        val view =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.top_hotels_recyclerview, parent, false)
        return HotelsViewHolder(view,listener)
    }

    override fun onBindViewHolder(holder: HotelsViewHolder, position: Int) {
        val topHotel = differ.currentList[position]
        holder.bind(topHotel)
        holder.itemView.apply {
            holder.bookTopHotelNow.setOnClickListener {
                listener.bookNow(position, topHotel)
            }
        }
        holder.itemView.apply {
            holder.layoutForToggle.setOnClickListener {
                listener.toggleSaveItemToWishList(position, holder.saveText,holder.saveImage,topHotel)
            }
        }
    }
    fun submitList(list: List<TopDealAndHotelData>) = differ.submitList(list)
    override fun getItemCount() = differ.currentList.size

    fun topHotelClickListener(topHotelClickListener: TopHotelClickListener){
        listener = topHotelClickListener
    }
}
interface TopHotelClickListener{
    fun onItemSelected(position: Int, item:TopDealAndHotelData)
    fun bookNow(position: Int, item:TopDealAndHotelData)
    fun toggleSaveItemToWishList(position: Int, saveItemTextBox: TextView, saveItemImage: ImageView, item: TopDealAndHotelData)

}