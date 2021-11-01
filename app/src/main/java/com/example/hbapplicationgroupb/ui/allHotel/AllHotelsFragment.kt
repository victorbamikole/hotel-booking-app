package com.example.hbapplicationgroupb.ui.allHotel

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.*
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.hbapplicationgroupb.R
import com.example.hbapplicationgroupb.dataBase.db.UserPreferences
import com.example.hbapplicationgroupb.databinding.FragmentAllHotelsBinding
import com.example.hbapplicationgroupb.model.allhotel.PageItem
import com.example.hbapplicationgroupb.model.wishlistdataclass.WishListDataClass
import com.example.hbapplicationgroupb.util.resource.Resource
import com.example.hbapplicationgroupb.viewModel.RoomViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AllHotelsFragment : Fragment(R.layout.fragment_all_hotels) {

    private var _binding: FragmentAllHotelsBinding? = null
    private val binding get() = _binding!!
    private val roomViewModel: RoomViewModel by viewModels()
    var stateList = mutableListOf<PageItem>()
    private var token:String? = null

    private val myAdapter = AllHotelAdapter()


    override fun onResume() {
        super.onResume()

//        refreshUserToken()



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
        token = activity?.let { UserPreferences(it).getUserToken()}
        if (token == null){
            token = "1"
        }

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
                findNavController()
                    .navigate(
                        AllHotelsFragmentDirections
                            .actionAllHotelsFragmentToHotelDescriptionFragment(
                                id
                            )
                    )
            }

            override fun bookNow(position: Int, item: PageItem) {
                val name = item.name
                val action =AllHotelsFragmentDirections
                    .actionAllHotelsFragmentToBookingDetailsScreenFragment2(name)
                findNavController().navigate(action)
            }

            override fun toggleSaveItemToWishList(
                position: Int,
                saveItemTextBox: TextView,
                saveItemImage: ImageView,
                item: PageItem
            ) {

                if (saveItemImage.visibility == View.INVISIBLE){
                    saveItemTextBox.text = "Saved!"
                    saveItemImage.visibility = View.VISIBLE
                    val wishListData = WishListDataClass(
                        id = item.id,
                        hotelName = item.name,
                        hotelPrice = item.roomTypes[0].price,
                        description = item.description,
                        percentage = item.rating.toString(),
                        token = token!!,
                        featureImage = item.featuredImage,
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
                        hotelPrice = item.roomTypes[0].price,
                        description = item.description,
                        percentage = item.rating.toString(),
                        token = token!!,
                        featureImage = item.featuredImage,
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

    private fun refreshUserToken() {
        val userId = activity.let { UserPreferences(it!!).getUserId() }
        val userRefreshToken = activity.let { UserPreferences(it!!).getUserRefreshToken() }
        roomViewModel.refreshToken(userId,userRefreshToken)

        val newToken = activity.let { UserPreferences(it!!).getUserToken() }

        Log.d("tokenQuery", "refreshUserToken: newToken = $newToken")

        roomViewModel.refreshToken.observe(viewLifecycleOwner,{refreshToken->
            activity.let { UserPreferences(it!!).saveSession(refreshToken.data.newJwtAccessToken,userId, refreshToken.data.newRefreshToken) }
        })
    }

}