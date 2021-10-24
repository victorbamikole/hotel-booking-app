package com.example.hbapplicationgroupb.ui.topdeals

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.hbapplicationgroupb.R
import com.example.hbapplicationgroupb.databinding.FragmentTopDealsBinding
import com.example.hbapplicationgroupb.viewModel.RoomViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TopDealsFragment : Fragment(R.layout.fragment_top_deals) {
    var binding: FragmentTopDealsBinding? = null
    private val roomViewModel : RoomViewModel by viewModels()
    private val myAdapter = TopDealsAdapter()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentTopDealsBinding.bind(view)

        initViewModel()
        //Fetch Top Deals From APi
        roomViewModel.getTopDeals()

        binding!!.topDealsRecyclerView.adapter = myAdapter
        binding!!.topDealsRecyclerView.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

        binding?.topDealsFragmentBackArrow?.setOnClickListener {
            findNavController()
                .navigate(R.id.action_topDealsFragment_to_exploreFragment2)
        }
    }

    //This function observes the TopDeals LiveData and populates the RecyclerView UI
    private fun initViewModel() {
        roomViewModel.topHotels.observe(viewLifecycleOwner,{
            if (it != null){
                myAdapter.populateTopDeals(it.data)

                binding!!.topDealsRecyclerView.adapter = myAdapter
                myAdapter.notifyDataSetChanged()

                //Set Click Listener on adapter list items
                myAdapter.setOnItemClickListener(object : TopDealsAdapter.SetItemClickListener{
                    override fun setOnItemClick(position: Int, myView: View?) {
                        val id = it.data[position].id
                        val price = it.data[position].price.toString()
                        findNavController()
                            .navigate(
                            TopDealsFragmentDirections
                                .actionTopDealsFragmentToHotelDescriptionFragment(
                                    id,price
                                )
                            )
                    }

                })

            }
        })
    }

}