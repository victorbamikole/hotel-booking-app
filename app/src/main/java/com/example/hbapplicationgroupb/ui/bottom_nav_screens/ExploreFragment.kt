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
import com.example.hbapplicationgroupb.di.application.HotelApplication
import com.example.hbapplicationgroupb.di.application.HotelApplication.Companion.application
import com.example.hbapplicationgroupb.model.topDealAndHotel.TopDealAndHotelData
import com.example.hbapplicationgroupb.util.resource.ConnectivityLiveData
import com.example.hbapplicationgroupb.util.resource.observeNetworkConnection
import com.example.hbapplicationgroupb.viewModel.UIViewModel
import com.example.hbapplicationgroupb.viewModel.RoomViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ExploreFragment : Fragment(R.layout.fragment_explore) {
    private lateinit var binding : FragmentExploreBinding
    private val roomViewModel : RoomViewModel by viewModels()
    val myAdapter = ExploreHomeAdapter()
    val myAdapter2 = ExploreHomeAdapter2()
    private val UIViewModel: UIViewModel by viewModels()
    private lateinit var connectivityLiveData: ConnectivityLiveData

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        connectivityLiveData = ConnectivityLiveData(application)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentExploreBinding.bind(view)



        initViewModel()
        initViewModel2()

        observeNetworkConnection(connectivityLiveData,viewLifecycleOwner,
            { doThisWhenNetworkIsAvailable() }, { doThisWhenNetworkIsLost() })


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
        binding.linkToAllHotelFragmment.setOnClickListener {
            findNavController().navigate(R.id.action_exploreFragment2_to_allHotelsFragment)
        }

        binding.viewButton.setOnClickListener {
            findNavController().navigate(R.id.action_exploreFragment2_to_allHotelsFragment)
        }
        binding.viewAllTopDeals.setOnClickListener {
            findNavController().navigate(R.id.action_exploreFragment2_to_topDealsFragment)
        }
//        UIViewModel.listOfHotels.observe(viewLifecycleOwner,{
//
//        })
    }

    private fun doThisWhenNetworkIsLost() {
        binding.networkErrorMessage.visibility= View.VISIBLE
    }

    private fun doThisWhenNetworkIsAvailable() {
        binding.networkErrorMessage.visibility=View.GONE
        //Fetch All Hotels From APi
        roomViewModel.getTopHotels()
        //Fetch Top Deals From APi
        roomViewModel.getTopDeals()
    }

    //This function observes the TopHotels LiveData and populates the RecyclerView UI
    private fun initViewModel() {
        roomViewModel.topHotels.observe(viewLifecycleOwner,{
            if (it != null){
                myAdapter.populateHotels(it.data)
                Log.d("Homefrag", "${it.data}")
                myAdapter.notifyDataSetChanged()
                binding.recyclerView.adapter = myAdapter
//               UIViewModel.insertAllHotelsToDb(it.data as ArrayList<TopDealAndHotelData>)
//                Log.d("DATABASE", " ${it.data} ")
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