package com.example.hbapplicationgroupb.ui.tophotels

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.hbapplicationgroupb.R
import com.example.hbapplicationgroupb.adapter.TopHotelsAdapter
import com.example.hbapplicationgroupb.databinding.FragmentTopHotelsBinding
import com.example.hbapplicationgroupb.model.TopHotels
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TopHotelsFragment : Fragment(R.layout.fragment_top_hotels) {

    var binding: FragmentTopHotelsBinding? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentTopHotelsBinding.bind(view)

        //List of top hotels
        val topHotels =
            listOf(
                TopHotels(R.drawable.hotel2, "Sheraton Grand", "$599", "4 star hotel", "80%"),
                TopHotels(R.drawable.hotel3, "GitHub Grand", "$999", "5 star hotel", "90%"),
                TopHotels(R.drawable.hotel2, "Radison Blue", "$1299", "4 star hotel", "80%"),
                TopHotels(R.drawable.hotel3, "Jetro Hols", "$899", "5 star hotel", "100%")
            )

        //Inflate the top hotels recycler view layout to the fragment class
        val myAdapter = TopHotelsAdapter(topHotels)
        binding!!.topHotelsRecyclerView.adapter = myAdapter
        binding!!.topHotelsRecyclerView.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        binding?.topHotelsFragmentBackAroow?.setOnClickListener {
            findNavController()
                .navigate(R.id.action_topHotelsFragment_to_exploreFragment2)
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null

    }
}