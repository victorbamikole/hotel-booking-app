package com.example.hbapplicationgroupb.ui.bookinghistory

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.hbapplicationgroupb.R
import com.example.hbapplicationgroupb.dataBase.db.UserPreferences
import com.example.hbapplicationgroupb.databinding.FragmentBookingHistoryBinding
import com.example.hbapplicationgroupb.ui.bookinghistory.adapter.BookingHistoryAdapter
import com.example.hbapplicationgroupb.viewModel.RoomViewModel
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BookingHistoryFragment : Fragment(R.layout.fragment_booking_history) {

    //var of type of binding class created for xml file
    private lateinit var binding: FragmentBookingHistoryBinding

    //Create an instance of view models and booking history adapter
    private val roomViewModel: RoomViewModel by viewModels()
    private lateinit var bookingHistoryAdapter : BookingHistoryAdapter

    private var token:String? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //set binding to bind views when views have created
        binding = FragmentBookingHistoryBinding.bind(view)

        setUpRecyclerView()

        token = activity?.let { UserPreferences(it).getUserToken() }
        token?.let { Log.d("TAG", it) }

        binding.bookingHistoryBackArrow.setOnClickListener {
            findNavController().navigate(R.id.action_bookingHistoryFragment_to_profileFragment2)
        }

//        token?.let { roomViewModel.getBookingHistory(it) }


        roomViewModel.getBookingHistory("Bearer $token")
        roomViewModel.bookingHistory.observe(viewLifecycleOwner, Observer { response ->

            if(response!=null){
                response.data.pageItems.let {
                    bookingHistoryAdapter.bookingHistoryData = it
                    Log.d("TAGing", it.toString())
                }
            }
            else{
                Snackbar.make(binding.root, "You are not authorized to access this page", Snackbar.LENGTH_LONG).show()
            }
        })
    }

    private fun setUpRecyclerView(){
        bookingHistoryAdapter = BookingHistoryAdapter()
        binding.fragmentBookingRv.apply {
            adapter = bookingHistoryAdapter
            layoutManager = LinearLayoutManager(requireActivity())
        }

    }
}