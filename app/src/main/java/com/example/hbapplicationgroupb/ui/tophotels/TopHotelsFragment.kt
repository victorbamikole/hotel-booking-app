package com.example.hbapplicationgroupb.ui.tophotels

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.hbapplicationgroupb.R
import com.example.hbapplicationgroupb.databinding.FragmentTopHotelsBinding
import com.example.hbapplicationgroupb.viewModel.RoomViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TopHotelsFragment : Fragment(R.layout.fragment_top_hotels) {

   private var _binding: FragmentTopHotelsBinding? = null
    private val binding  get() = _binding!!
    private val roomViewModel : RoomViewModel by viewModels()
    val myAdapter = TopHotelsAdapter()



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentTopHotelsBinding.bind(view)

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
                myAdapter.populateHotels(it.data)
                myAdapter.notifyDataSetChanged()
                binding.topHotelsRecyclerView.adapter = myAdapter
                Log.d("TTT", "initializeViewModel:${it.data} ")
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null

    }
}