package com.example.hbapplicationgroupb.ui.allHotel

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.hbapplicationgroupb.R
import com.example.hbapplicationgroupb.databinding.FragmentAllHotelsBinding
import com.example.hbapplicationgroupb.model.allhotel.AllHotel
import com.example.hbapplicationgroupb.model.allhotel.PageItem
import com.example.hbapplicationgroupb.util.resource.Resource
import com.example.hbapplicationgroupb.viewModel.RoomViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AllHotelsFragment : Fragment(R.layout.fragment_all_hotels) {

    private var _binding: FragmentAllHotelsBinding? = null
    private val binding get() = _binding!!
    private val roomViewModel: RoomViewModel by viewModels()
    var arrayList = listOf<PageItem>()

    private val myAdapter = AllHotelAdapter()

    override fun onResume() {
        super.onResume()
        // locations filters
        val listOfStateNames = resources.getStringArray(R.array.filter_by)
        val filterAdapter = ArrayAdapter(requireContext(),R.layout.array_adapter_of_state, listOfStateNames)
        binding.fragmentAllHotelsActv.setAdapter(filterAdapter)
//        val stateName = binding.fragmentAllHotelsActv.text.toString()

        binding.fragmentAllHotelsActv.onItemClickListener = object : AdapterView.OnItemClickListener{
            override fun onItemClick(p0: AdapterView<*>?, p1: View?, position: Int, p3: Long) {
                val selectedState = listOfStateNames[position].toString()
                binding.fragmentAllHotelsStateName.text = selectedState
                for (i in arrayList){
                    if (i.state == selectedState){

                    }
                }
                Log.d("SELECTED STATE", selectedState)
            }

        }

//        binding.fragmentAllHotelsActv.onItemSelectedListener = object: AdapterView.OnItemSelectedListener{
//            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, position: Int, p3: Long) {
//                Log.d("BEFORE STATE NAME", stateName[position].toString())
//                binding.fragmentAllHotelsStateName.text = stateName[position].toString()
//                Log.d("AFTER STATE NAME", stateName[position].toString())
//            }
//
//            override fun onNothingSelected(p0: AdapterView<*>?) {
//                TODO("Not yet implemented")
//            }
//
//        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentAllHotelsBinding.bind(view)

            fetchAllHotels()
//        roomViewModel.getAllHotels()

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


//            Toast.makeText(requireContext(),it.data!![0].email,Toast.LENGTH_SHORT).show()
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