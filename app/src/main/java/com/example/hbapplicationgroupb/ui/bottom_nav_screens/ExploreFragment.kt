package com.example.hbapplicationgroupb.ui.bottom_nav_screens

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.hbapplicationgroupb.R
import com.example.hbapplicationgroupb.databinding.FragmentExploreBinding
import com.example.hbapplicationgroupb.viewModel.UIViewModel
import com.example.hbapplicationgroupb.viewModel.RoomViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ExploreFragment : Fragment(R.layout.fragment_explore) {
    private lateinit var binding : FragmentExploreBinding
    private val roomViewModel : RoomViewModel by viewModels()
    val myAdapter = ExploreHomeAdapter()
    val myAdapter2 = ExploreHomeAdapter2()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentExploreBinding.bind(view)

        initViewModel()
        initViewModel2()
        //Fetch All Hotels From APi
        roomViewModel.getAllHotels(10,1)
        //Fetch Top Deals From APi
        roomViewModel.getTopDeals(10, 1)

        //Inflate the top hotels recycler view layout to the fragment class
        binding.recyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)


        //Inflate the top deals recycler view layout to the fragment class
        val myAdapter2 = ExploreHomeAdapter()
        binding.recyclerView2.adapter = myAdapter2
        binding.recyclerView2.layoutManager =
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
    }

    //This function observes the TopHotels LiveData and populates the RecyclerView UI
    private fun initViewModel() {
        roomViewModel.allHotelsList.observe(viewLifecycleOwner,{
            if (it != null){
                myAdapter.populateHotels(it.data)
                Log.d("Homefrag", "${it.data}")
                myAdapter.notifyDataSetChanged()
                binding.recyclerView.adapter = myAdapter

            }
             })
    }

    //This function observes the TopDeals LiveData and populates the RecyclerView UI
    private fun initViewModel2() {
        roomViewModel.allTopDeals.observe(viewLifecycleOwner,{
            if (it != null){
                myAdapter2.populateTopDeals(it.data)
                Log.d("Homefrag", "${it.data}")
                myAdapter2.notifyDataSetChanged()
                binding.recyclerView2.adapter = myAdapter2

            }
        })
    }

}