package com.example.hbapplicationgroupb.ui.bookinghistory.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.hbapplicationgroupb.R
import com.example.hbapplicationgroupb.model.bookinghistory.PageItem

class BookingHistoryAdapter : RecyclerView.Adapter<BookingHistoryAdapter.MyViewHolder>() {
    inner class MyViewHolder(var listItemView : View) : RecyclerView.ViewHolder(listItemView) {
        var checkInTime : TextView = listItemView.findViewById(R.id.fragment_booking_date_tv)
        var hotelName : TextView = listItemView.findViewById(R.id.fragment_booking_hotel_name_tv)
        var price: TextView = listItemView.findViewById(R.id.fragment_booking_hotel_amount_tv)
    }

    private val diffCallBack = object : DiffUtil.ItemCallback<PageItem>(){
        override fun areItemsTheSame(oldItem: PageItem, newItem: PageItem): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: PageItem, newItem: PageItem): Boolean {
            return newItem == oldItem
        }
    }

    private val differ = AsyncListDiffer(this, diffCallBack)
    var bookingHistoryData : List<PageItem>
        get() = differ.currentList
        set(value) {
            differ.submitList(value)
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            LayoutInflater.from(parent.context).inflate(
            R.layout.booking_history_item , parent, false
        ))
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentHistoryList = bookingHistoryData[position]

        holder.checkInTime.text = currentHistoryList.checkIn
        holder.price.text = currentHistoryList.price.toString()
        holder.hotelName.text = currentHistoryList.hotel
    }

    override fun getItemCount(): Int {
        return bookingHistoryData.size
    }
}