package com.example.hbapplicationgroupb.ui.tophotels

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.hbapplicationgroupb.R
import com.example.hbapplicationgroupb.dataBase.db.UserPreferences
import com.example.hbapplicationgroupb.databinding.FragmentTopHotelsBinding
import com.example.hbapplicationgroupb.model.topDealAndHotel.TopDealAndHotelData
import com.example.hbapplicationgroupb.model.tophotelresponse.TopHotelData
import com.example.hbapplicationgroupb.model.wishlistdataclass.WishListDataClass
import com.example.hbapplicationgroupb.util.resource.Resource
import com.example.hbapplicationgroupb.viewModel.RoomViewModel
import com.example.hbapplicationgroupb.viewModel.UIViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TopHotelsFragment : Fragment(R.layout.fragment_top_hotels) {

    private var _binding: FragmentTopHotelsBinding? = null
    private val binding  get() = _binding!!
    private val roomViewModel : RoomViewModel by viewModels()
    private val myAdapter = TopHotelsAdapter()
    private var token:String? = null


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentTopHotelsBinding.bind(view)

        token = activity?.let { UserPreferences(it).getUserToken() }
        if (token == null){
            token = "1"
        }

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
            }
        })


        myAdapter.topHotelClickListener(object : TopHotelClickListener{
            override fun toggleSaveItemToWishList(
                position: Int,
                saveItemTextBox: TextView,
                saveItemImage: ImageView,
                item: TopDealAndHotelData
            ) {
                if (saveItemImage.visibility == View.INVISIBLE){
                    saveItemTextBox.text = "Saved!"
                    saveItemImage.visibility = View.VISIBLE
                    val wishListData = WishListDataClass(
                        id = item.id,
                        hotelName = item.name,
                        hotelPrice = item.price,
                        description = item.description,
                        percentage = item.percentageRating.toString(),
                        token = token!!,
                        featureImage = item.thumbnail,
                        saved = true
                    )
                    val userToken = activity.let { UserPreferences(it!!).getUserToken() }
                    roomViewModel.addCustomerWishToWishList("Bearer $userToken",item.id)

                    roomViewModel.addCustomerWish.observe(viewLifecycleOwner,{
                        Toast.makeText(requireContext(), "$it", Toast.LENGTH_SHORT).show()
                    })
                    roomViewModel.insertWishListToDb(wishListData)
                }else{
                    saveItemTextBox.text = "Save"
                    saveItemImage.visibility = View.INVISIBLE
                    val wishListData = WishListDataClass(
                        id = item.id,
                        hotelName = item.name,
                        hotelPrice = item.price,
                        description = item.description,
                        percentage = item.percentageRating.toString(),
                        token = token!!,
                        featureImage = item.thumbnail,
                        saved = false
                    )

                    val userToken = activity.let { UserPreferences(it!!).getUserToken() }
                    roomViewModel.deleteCustomerWishFromWishList("Bearer $userToken",item.id)
                    roomViewModel.deleteCustomerWish.observe(viewLifecycleOwner,{
                        Toast.makeText(requireContext(), "$it", Toast.LENGTH_SHORT).show()
                    })
                    roomViewModel.deleteWishListFromDb(wishListData)
                }
            }

        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null

    }
}