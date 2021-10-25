package com.example.hbapplicationgroupb.ui.allHotel

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.hbapplicationgroupb.R
import com.example.hbapplicationgroupb.databinding.FragmentAllHotelsBinding
import com.example.hbapplicationgroupb.model.allhotel.PageItem
import com.example.hbapplicationgroupb.util.resource.Resource
import com.example.hbapplicationgroupb.viewModel.RoomViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AllHotelsFragment : Fragment(R.layout.fragment_all_hotels) {

    private var _binding: FragmentAllHotelsBinding? = null
    private val binding get() = _binding!!
    private val roomViewModel: RoomViewModel by viewModels()
    var stateList = mutableListOf<PageItem>()

    private val myAdapter = AllHotelAdapter()

    override fun onResume() {
        super.onResume()

        // locations filters
        val listOfStateNames = resources.getStringArray(R.array.filter_by)
        val filterAdapter = ArrayAdapter(requireContext(),R.layout.array_adapter_of_state, listOfStateNames)
        binding.fragmentAllHotelsActv.setAdapter(filterAdapter)

        binding.fragmentAllHotelsActv.onItemClickListener = object : AdapterView.OnItemClickListener{
            override fun onItemClick(p0: AdapterView<*>?, p1: View?, position: Int, id: Long) {
                val selectedState = listOfStateNames[position].toString()
                if (selectedState != "All Hotels") {
                    binding.fragmentAllHotelsStateName.text = selectedState
                    binding.fragmentAllHotelsActv.text.clear()
                    var temptList = mutableListOf<PageItem>()
                    for (i in stateList) {
                        if (i.state == selectedState) {
                            temptList.add(i)
                        }
                    }
                    myAdapter.submitList(temptList)
                    myAdapter.notifyDataSetChanged()
                    binding.allHotelsRecyclerView.adapter = myAdapter
                    Log.d("NewList ", "NewList = $temptList")
                }else{
                    binding.fragmentAllHotelsStateName.text = ""
                    binding.fragmentAllHotelsActv.text.clear()
                    fetchAllHotels()
                }
            }

        }

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentAllHotelsBinding.bind(view)

            fetchAllHotels()

        binding.apply {
            allHotelsRecyclerView.adapter = myAdapter
            allHotelsRecyclerView.layoutManager =
                LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            allHotelsFragmentBackAroow.setOnClickListener {
                findNavController()
                    .navigate(R.id.action_allHotelsFragment_to_exploreFragment2)
            }


        }


    }

    private fun fetchAllHotels() {
        roomViewModel.getAllHotel.observe(viewLifecycleOwner, Observer {

                it.data?.let { it1 -> stateList.addAll(it1) }
                Log.d("STATE LIST", "$stateList")
                myAdapter.submitList(it.data!!)
                binding.allHotelProgressBar.isVisible = it is Resource.Loading && it.data.isNullOrEmpty()
                binding.allHotelTextViewError.isVisible = it is Resource.Error && it.data.isNullOrEmpty()
                binding.allHotelTextViewError.text = it.error?.localizedMessage
                myAdapter.notifyDataSetChanged()
                binding.allHotelsRecyclerView.adapter = myAdapter



        })

        myAdapter.allHotelClickListener(object : AllHotelClickListener{
            override fun onItemSelected(position: Int, item: PageItem) {
                val id = item.id
                val price = item.roomTypes[0].price.toString()
                findNavController()
                    .navigate(
                        AllHotelsFragmentDirections
                            .actionAllHotelsFragmentToHotelDescriptionFragment(
                                id,price
                            )
                    )
            }

            override fun bookNow(position: Int, item: PageItem) {
                val name = item.name
                val action =AllHotelsFragmentDirections
                    .actionAllHotelsFragmentToBookingDetailsScreenFragment2(name)
                findNavController().navigate(action)
            }

        })
    }

}