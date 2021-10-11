package com.example.hbapplicationgroupb.ui.bottom_nav_screens

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.hbapplicationgroupb.R
import com.example.hbapplicationgroupb.databinding.FragmentExploreBinding
import com.example.hbapplicationgroupb.model.Hotels
import com.example.hbapplicationgroupb.viewModel.UIViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ExploreFragment : Fragment(R.layout.fragment_explore) {
    private lateinit var binding : FragmentExploreBinding
    private val uiViewModel : UIViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentExploreBinding.bind(view)

        //List of different hotels
//        val hotels = listOf(
//            Hotels(R.drawable.hotel2, "Sheraton Grand", "$599"),
//            Hotels(R.drawable.hotel3, "GitHub Grand", "$999"),
//            Hotels(R.drawable.hotel2, "Radison Blue", "$1299"),
//            Hotels(R.drawable.hotel3, "Jetro Hols", "$899")
//        )
        uiViewModel.getAllHotels()


        //Inflate the top hotels recycler view layout to the fragment class
        val myAdapter = ExploreHomeAdapter()
        binding.recyclerView.adapter = myAdapter
        binding.recyclerView.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)


        //Inflate the top deals recycler view layout to the fragment class
        val myAdapter2 = ExploreHomeAdapter()
        binding!!.recyclerView2.adapter = myAdapter2
        binding!!.recyclerView2.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)

        binding.tvViewAllHotel.setOnClickListener {
            findNavController().navigate(R.id.action_exploreFragment2_to_topHotelsFragment)
        }

        binding.viewButton.setOnClickListener {
            findNavController().navigate(R.id.action_exploreFragment2_to_topDealsFragment)
        }
        binding.viewAllTopDeals.setOnClickListener {
            findNavController().navigate(R.id.action_exploreFragment2_to_topDealsFragment)
        }


        uiViewModel.allHotels.observe(viewLifecycleOwner,{

            myAdapter.populateHotels(it)
        })

    }

}