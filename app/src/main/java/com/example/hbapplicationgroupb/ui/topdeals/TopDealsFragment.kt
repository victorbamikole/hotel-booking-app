package com.example.hbapplicationgroupb.ui.topdeals

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.hbapplicationgroupb.R
import com.example.hbapplicationgroupb.adapter.TopDealsAdapter
import com.example.hbapplicationgroupb.adapter.TopHotelsAdapter
import com.example.hbapplicationgroupb.databinding.FragmentTopDealsBinding
import com.example.hbapplicationgroupb.databinding.FragmentTopHotelsBinding
import com.example.hbapplicationgroupb.model.TopHotels

class TopDealsFragment : Fragment(R.layout.fragment_top_deals) {

    var binding: FragmentTopDealsBinding? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentTopDealsBinding.bind(view)

        val topDeals =
            listOf(
                TopHotels(R.drawable.hotel2, "Sheraton Grand", "$599", "4 star hotel", "80%"),
                TopHotels(R.drawable.hotel3, "GitHub Grand", "$999", "5 star hotel", "90%"),
                TopHotels(R.drawable.hotel2, "Radison Blue", "$1299", "4 star hotel", "80%"),
                TopHotels(R.drawable.hotel3, "Jetro Hols", "$899", "5 star hotel", "100%")
            )

        //Inflate the top hotels recycler view layout to the fragment class
        val myAdapter = TopDealsAdapter(topDeals)
        binding!!.topDealsRecyclerView.adapter = myAdapter
        binding!!.topDealsRecyclerView.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

    }


}