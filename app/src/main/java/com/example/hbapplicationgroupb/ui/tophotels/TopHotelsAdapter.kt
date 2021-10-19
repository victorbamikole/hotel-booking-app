package com.example.hbapplicationgroupb.ui.tophotels

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.navArgument
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.hbapplicationgroupb.R
import com.example.hbapplicationgroupb.databinding.TopDealRecyclerViewLayoutBinding
import com.example.hbapplicationgroupb.databinding.TopDealsRecyclerviewBinding
import com.example.hbapplicationgroupb.databinding.TopHotelsRecyclerviewBinding
import com.example.hbapplicationgroupb.model.tophotelresponse.TopHotelData
import com.example.hbapplicationgroupb.ui.topdeals.TopDealsFragmentDirections

class TopHotelsAdapter() :
    RecyclerView.Adapter<TopHotelsAdapter.HotelsViewHolder>() {

    class HotelsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        private val binding: TopDealRecyclerViewLayoutBinding =
            TopDealRecyclerViewLayoutBinding.bind(itemView)

        val topImage = binding.topDealsRecyclerViewImage
        val topName = binding.topDealtopDealNameHotelRecyclerViewSaveText
        val topPrice = binding.topDealRecyclerViewPrice
        val topRating = binding.topDealRecyclerViewtopDealRating
        val topPercent = binding.topDealRecyclerViewtopDealPercent
        val bookTopHotelNow = binding.topDealRecyclerviewBookNowButton

        fun bind(topHotel : TopHotelData){
            Glide.with(itemView)
                .load(topHotel.thumbnail)
                .into(topImage)

            topName.text = topHotel.name
            "${topHotel.percentageRating}%".also { topPercent.text = it }
           topHotel.price.toString() .also { topPrice.text = it }

            topName.text = topHotel.name
            topRating.text = topHotel.percentageRating.toString()
            topPercent.text = topHotel.price.toString()


        }
    }


    private val differCallBack = object : DiffUtil.ItemCallback<TopHotelData>(){
        override fun areItemsTheSame(oldItem: TopHotelData, newItem: TopHotelData) = oldItem.id == newItem.id


        override fun areContentsTheSame(oldItem: TopHotelData, newItem: TopHotelData) = oldItem == newItem

    }

 val differ = AsyncListDiffer(this,differCallBack)



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HotelsViewHolder {
        val view =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.top_deal_recycler_view_layout, parent, false)
        return HotelsViewHolder(view)
    }

    override fun onBindViewHolder(holder: HotelsViewHolder, position: Int) {

        val topHotel = differ.currentList[position]
        holder.bind(topHotel)

        holder.itemView.apply {
            holder.bookTopHotelNow.setOnClickListener {
                val name = topHotel.name
                val action =TopHotelsFragmentDirections
                    .actionTopHotelsFragmentToBookingDetailsScreenFragment2(name)
                findNavController().navigate(action)
            }
            setOnClickListener {
                val id = topHotel.id
                val price = topHotel.price.toString()
                findNavController()
                    .navigate(
                        TopHotelsFragmentDirections
                            .actionTopHotelsFragmentToHotelDescriptionFragment(
                                id,price
                            )
                    )
            }

        }

    }

    fun submitList(list: List<TopHotelData>) = differ.submitList(list)

    override fun getItemCount() = differ.currentList.size


}











//package com.example.hbapplicationgroupb.ui.tophotels
//
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import androidx.recyclerview.widget.RecyclerView
//import com.bumptech.glide.Glide
//import com.example.hbapplicationgroupb.R
//import com.example.hbapplicationgroupb.databinding.TopHotelsRecyclerviewBinding
//import com.example.hbapplicationgroupb.model.tophotelresponse.TopHotelData
//
//class TopHotelsAdapter() :
//    RecyclerView.Adapter<TopHotelsAdapter.HotelsViewHolder>() {
//    var tophotels: List<TopHotelData> = listOf()
//    fun populateHotels(list: List<TopHotelData>) {
//        this.tophotels = list
//        notifyDataSetChanged()
//    }
//
//    class HotelsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
//        private val binding: TopHotelsRecyclerviewBinding =
//            TopHotelsRecyclerviewBinding.bind(itemView)
//        val topImage = binding.topHotelImage
//        val topName = binding.topHotelName
//        val topPrice = binding.topHotelPrice
//        val topRating = binding.topHotelRating
//        val topPercent = binding.topHotelPercent
//        val bookTopHotel = binding.bookTopHotel
//        //
//        fun populateHotels(hotelList : TopHotelData){
//            Glide.with(itemView)
//                .load(hotelList.thumbnail)
//                .into(topImage)
//            topName.text = hotelList.name
//            topRating.text = hotelList.rating.toString()
//            topPercent.text = hotelList.numberOfReviews.toString()
//
////            topPrice.text = hotelList.
////            topPrice.text = hotelList.roomTypes[0].price.toString()//.price
//        }
//    }
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HotelsViewHolder {
//        val view =
//            LayoutInflater.from(parent.context)
//                .inflate(R.layout.top_hotels_recyclerview, parent, false)
//        return HotelsViewHolder(view)
//    }
//
//    override fun onBindViewHolder(holder: HotelsViewHolder, position: Int) {
//        holder.populateHotels(tophotels[position])
//
//    }
//
//    override fun getItemCount(): Int {
//        return tophotels.size
//    }
//}