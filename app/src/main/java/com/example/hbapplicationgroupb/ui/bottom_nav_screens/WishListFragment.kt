package com.example.hbapplicationgroupb.ui.bottom_nav_screens

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.hbapplicationgroupb.R
import com.example.hbapplicationgroupb.dataBase.db.UserPreferences
import com.example.hbapplicationgroupb.databinding.FragmentWishListBinding
import com.example.hbapplicationgroupb.model.wishlistdataclass.HotelData
import com.example.hbapplicationgroupb.model.wishlistdataclass.PageItem
import com.example.hbapplicationgroupb.viewModel.RoomViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class WishListFragment : Fragment(R.layout.fragment_wish_list) {
    private lateinit var binding : FragmentWishListBinding
    private lateinit var instanceOfWishListAdapter : WishListAdapter
    private val roomViewModel: RoomViewModel by viewModels()
    private var token:String? = null



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentWishListBinding.bind(view)

        token = activity?.let { UserPreferences(it).getUserToken() }
        if (token == null){
            token = "1"
        }

//        refreshUserToken()
        roomViewModel.deleteCustomerWish.observe(viewLifecycleOwner,{
            if (it != null){
                roomViewModel.getAllWishLIstFromAPi("Bearer ${token!!}")
            }
        })

        setUpWishListRecyclerView()
        roomViewModel.getAllWishLIstFromAPi("Bearer ${token!!}")


        loadListData()
        instanceOfWishListAdapter.allWishClickListener(object : WishListAdapter.AllWishesClickListener{
            override fun onItemSelected(position: Int, item: PageItem) {
                val id = item.hotelId
                findNavController()
                    .navigate(
                        WishListFragmentDirections
                            .actionWishListFragment2ToHotelDescriptionFragment(
                                id
                            )
                    )
            }

            override fun bookNow(position: Int, item: PageItem) {
                val name = item.hotelName
                val action =WishListFragmentDirections
                    .actionWishListFragment2ToBookingDetailsScreenFragment2(name)
                findNavController().navigate(action)
            }

            override fun toggleSaveItemToWishList(
                position: Int,
                saveItemTextBox: TextView,
                saveItemImage: ImageView,
                item: PageItem
            ) {


                saveItemTextBox.text = "Remove"
                    saveItemImage.visibility = View.VISIBLE
                roomViewModel.deleteCustomerWishFromWishList("Bearer ${token!!}",item.hotelId)

                loadListData()

            }

        })
    }

    /**
     * Set up recyclerview for wishlist screen
     */

    private fun setUpWishListRecyclerView() {

        instanceOfWishListAdapter = WishListAdapter()


        binding.wishListRecyclerViewId.apply {
            layoutManager = LinearLayoutManager(activity)
            setHasFixedSize(true)
        }
    }
    private fun loadListData(){

        roomViewModel.getAllWishListFromApi.observe(viewLifecycleOwner,{
            if (it != null) {
                instanceOfWishListAdapter.submitList(it.data.pageItems)
                instanceOfWishListAdapter.notifyDataSetChanged()
                binding.wishListRecyclerViewId.adapter = instanceOfWishListAdapter
            }
        })
    }
    private fun refreshUserToken() {
        val userId = activity.let { UserPreferences(it!!).getUserId() }
        val userRefreshToken = activity.let { UserPreferences(it!!).getUserRefreshToken() }
        roomViewModel.refreshToken(userId, userRefreshToken)


        roomViewModel.refreshToken.observe(viewLifecycleOwner, { refreshToken ->
            activity.let {
                UserPreferences(it!!).saveSession(
                    refreshToken.data.newJwtAccessToken,
                    userId,
                    refreshToken.data.newRefreshToken
                )
            }
        })

    }
}