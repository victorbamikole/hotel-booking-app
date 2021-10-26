package com.example.hbapplicationgroupb.ui.bottom_nav_screens

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
import com.example.hbapplicationgroupb.model.wishlistdataclass.WishListDataClass
import com.example.hbapplicationgroupb.ui.allHotel.AllHotelAdapter
import com.example.hbapplicationgroupb.ui.allHotel.AllHotelClickListener

class WishListAdapter:RecyclerView.Adapter<WishListAdapter.MyViewHolder>() {
    private lateinit var listener: AllWishesClickListener

    inner class MyViewHolder(itemView: View, private val allWishesClickListener: AllWishesClickListener)
        :RecyclerView.ViewHolder(itemView){
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

        fun bind(wishList: WishListDataClass) {

            itemView.setOnClickListener {
                allWishesClickListener.onItemSelected(adapterPosition, wishList)
            }

            Glide.with(itemView)
                .load(wishList.featureImage)
                .into(topImage)


            topName.text = wishList.hotelName

            topPrice.text = "$${wishList.hotelPrice}"


            description.text = wishList.description
            topPercent.text = wishList.percentage
        }
    }
    private val differCallBack = object : DiffUtil.ItemCallback<WishListDataClass>(){
        override fun areItemsTheSame(
            oldItem: WishListDataClass,
            newItem: WishListDataClass
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: WishListDataClass,
            newItem: WishListDataClass
        ): Boolean {
            return  oldItem==newItem
        }

    }
    val differ = AsyncListDiffer(this,differCallBack)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.all_hotels_recyclerview_layout, parent, false)
        return MyViewHolder(view,listener)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentWish = differ.currentList[position]
        holder.bind(currentWish)

        holder.itemView.apply {
            holder.layoutForToggle.setOnClickListener {
                listener.toggleSaveItemToWishList(position, holder.saveText,holder.saveImage,currentWish)
            }
        }

        holder.itemView.apply {
            holder.bookTopHotelNow.setOnClickListener {
                listener.bookNow(position, currentWish)
            }
        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }
    fun allWishClickListener(allWishesClickListener: AllWishesClickListener){
        listener = allWishesClickListener
    }


    fun submitList(list: List<WishListDataClass>) = differ.submitList(list)
    interface AllWishesClickListener{
        fun onItemSelected(position: Int, item: WishListDataClass)
        fun bookNow(position: Int, item: WishListDataClass)
        fun toggleSaveItemToWishList(position: Int, saveItemTextBox: TextView, saveItemImage: ImageView, item: WishListDataClass)
    }


}