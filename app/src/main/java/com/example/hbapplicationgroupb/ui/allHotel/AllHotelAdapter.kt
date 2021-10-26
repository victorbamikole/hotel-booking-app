package com.example.hbapplicationgroupb.ui.allHotel

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
import com.example.hbapplicationgroupb.databinding.AllHotelsRecyclerviewLayoutBinding
import com.example.hbapplicationgroupb.model.allhotel.PageItem
import com.example.hbapplicationgroupb.model.topDealAndHotel.TopDealAndHotelData
import com.example.hbapplicationgroupb.ui.tophotels.TopHotelClickListener

class AllHotelAdapter(): RecyclerView.Adapter<AllHotelAdapter.AllHotelViewHolder>() {
       private lateinit var listener: AllHotelClickListener


    class AllHotelViewHolder(itemView:View, val allHotelClickListener: AllHotelClickListener): RecyclerView.ViewHolder(itemView) {
        private val binding: AllHotelsRecyclerviewLayoutBinding =
            AllHotelsRecyclerviewLayoutBinding.bind(itemView)

        private val topImage = binding.topDealsRecyclerViewImage
        private val topName = binding.topDealtopDealNameHotelRecyclerViewSaveText
        private val topPrice = binding.topDealRecyclerViewPrice
        private val description = binding.topDealRecyclerViewtopDealRating
        private val topPercent = binding.topDealRecyclerViewtopDealPercent
        val bookTopHotelNow = binding.topDealRecyclerviewBookNowButton
        val saveText = binding.topDealSaveHotelRecyclerViewSaveText
        val saveImage = binding.topDealSaveHotelRecyclerViewImage
        val layoutForToggle = binding.layoutForSaving

        fun bind(allhotel: PageItem) {
                itemView.setOnClickListener {
                    allHotelClickListener.onItemSelected(adapterPosition, allhotel)
                }

                    Glide.with(itemView)
                        .load(allhotel.featuredImage)
                        .into(topImage)


                topName.text = allhotel.name

                topPrice.text = "$${allhotel.roomTypes[0].price}"


                description.text = allhotel.description
                topPercent.text = allhotel.rating.toString()
            }
        }


    private val differCallBack = object : DiffUtil.ItemCallback<PageItem>(){
        override fun areItemsTheSame(oldItem: PageItem, newItem: PageItem): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: PageItem, newItem: PageItem): Boolean {
            return  oldItem==newItem
        }
    }

    val differ = AsyncListDiffer(this,differCallBack)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AllHotelViewHolder {
        val view =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.all_hotels_recyclerview_layout, parent, false)
        return AllHotelViewHolder(view,listener)
    }

    override fun onBindViewHolder(holder: AllHotelViewHolder, position: Int) {
        val allhotel = differ.currentList[position]

            holder.bind(allhotel)

        holder.itemView.apply {
            holder.layoutForToggle.setOnClickListener {
                listener.toggleSaveItemToWishList(position, holder.saveText,holder.saveImage,allhotel)
            }
        }

        holder.itemView.apply {
            holder.bookTopHotelNow.setOnClickListener {
                listener.bookNow(position, allhotel)
            }
        }


    }

    fun submitList(list: List<PageItem>) = differ.submitList(list)

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    fun allHotelClickListener(allHotelClickListener: AllHotelClickListener){
        listener = allHotelClickListener
    }
}

interface AllHotelClickListener{
    fun onItemSelected(position: Int, item: PageItem)
    fun bookNow(position: Int, item: PageItem)
    fun toggleSaveItemToWishList(position: Int, saveItemTextBox:TextView, saveItemImage:ImageView, item: PageItem)
}