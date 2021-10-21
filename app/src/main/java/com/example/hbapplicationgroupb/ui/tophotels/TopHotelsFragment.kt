package com.example.hbapplicationgroupb.ui.tophotels

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.ArrayAdapter
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.hbapplicationgroupb.R
import com.example.hbapplicationgroupb.databinding.FragmentTopHotelsBinding
import com.example.hbapplicationgroupb.model.topDealAndHotel.TopDealAndHotelData
import com.example.hbapplicationgroupb.model.tophotelresponse.TopHotelData
import com.example.hbapplicationgroupb.util.resource.Resource
import com.example.hbapplicationgroupb.viewModel.RoomViewModel
import com.example.hbapplicationgroupb.viewModel.UIViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TopHotelsFragment : Fragment(R.layout.fragment_top_hotels) {

   private var _binding: FragmentTopHotelsBinding? = null
    private val binding  get() = _binding!!
    private val roomViewModel : RoomViewModel by viewModels()
//    private val uiViewModel: UIViewModel by viewModels()
    val myAdapter = TopHotelsAdapter()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentTopHotelsBinding.bind(view)

        // locations
        var listOfStateNames = resources.getStringArray(R.array.filter_by)
        var filterAdapter = ArrayAdapter(requireContext(),R.layout.support_simple_spinner_dropdown_item, listOfStateNames)

        initializeViewModel()

        roomViewModel.getTopHotels()

        binding.topHotelsRecyclerView.layoutManager = LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false)



        //Inflate the top hotels recycler view layout to the fragment class
//        val myAdapter = TopHotelsAdapter(topHotels)
        binding.topHotelsRecyclerView.adapter = myAdapter
        binding.topHotelsRecyclerView.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        binding.topHotelsFragmentBackAroow.setOnClickListener {
            findNavController()
                .navigate(R.id.action_topHotelsFragment_to_exploreFragment2)
        }

    }

    private fun initializeViewModel() {
        roomViewModel.topHotels.observe(viewLifecycleOwner,{
            if (it != null){
                myAdapter.submitList(it.data)
                myAdapter.notifyDataSetChanged()
                binding.topHotelsRecyclerView.adapter = myAdapter
                Log.d("TTT", "initializeViewModel:${it.data} ")
            }
        })


        myAdapter.topHotelClickListener(object : TopHotelClickListener{
            override fun onItemSelected(position: Int, item: TopDealAndHotelData) {
                val id = item.id
                val price = item.price.toString()
                findNavController()
                    .navigate(
                        TopHotelsFragmentDirections
                            .actionTopHotelsFragmentToHotelDescriptionFragment(
                                id,price
                            )
                    )



            }

            override fun bookNow(position: Int, item: TopDealAndHotelData) {

                val name = item.name
                val action =TopHotelsFragmentDirections
                    .actionTopHotelsFragmentToBookingDetailsScreenFragment2(name)
                findNavController().navigate(action)
            }

        })
    }

//    private fun initializeViewModel() {
//        roomViewModel.topHotels.observe(viewLifecycleOwner,{ response ->
//            when (response){
//              is Resource.Success -> {
//                  //hide progress bar
//                  //add to recyclerView
//                  response.data?.let { topHotelResponse ->
//                      myAdapter.submitList(topHotelResponse.data)
//                      roomViewModel.saveTopHotels(topHotelResponse.data)
//                  }
//              }
//                is Resource.Error -> {
//                    //hide progress bar
//                    response.exception?.let { message ->
//                        Log.d("TOP HOTEL", "error occured: $message")
//                    }
//                }
//                is Resource.Loading -> {
//                    //show progress bar
//                }
//            }
//        })
//    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null

    }
}