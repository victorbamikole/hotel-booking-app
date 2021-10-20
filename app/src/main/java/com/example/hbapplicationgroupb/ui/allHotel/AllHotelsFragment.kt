package com.example.hbapplicationgroupb.ui.allHotel

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.hbapplicationgroupb.R
import com.example.hbapplicationgroupb.databinding.FragmentAllHotelsBinding
import com.example.hbapplicationgroupb.model.allhotel.PageItem
import com.example.hbapplicationgroupb.viewModel.RoomViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AllHotelsFragment : Fragment(R.layout.fragment_all_hotels) {

    private var _binding: FragmentAllHotelsBinding? = null
    private val binding get() = _binding!!
    private val roomViewModel: RoomViewModel by viewModels()

    private val myAdapter = AllHotelAdapter()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentAllHotelsBinding.bind(view)

        fetchAllHotels()
        roomViewModel.getAllHotels()

        binding.apply {
            allHotelsRecyclerView.adapter = myAdapter
            allHotelsRecyclerView.layoutManager =
                LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            allHotelsFragmentBackAroow.setOnClickListener {
                findNavController()
                    .navigate(R.id.action_topHotelsFragment_to_exploreFragment2)
            }


        }


    }

    private fun fetchAllHotels() {
        roomViewModel.fetchAllHotelResponse.observe(viewLifecycleOwner, Observer {
            if (it != null){
                myAdapter.submitList(it.body()?.data!!.pageItems)
                myAdapter.notifyDataSetChanged()
                binding.allHotelsRecyclerView.adapter = myAdapter
            }
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