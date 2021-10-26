package com.example.hbapplicationgroupb.ui.bottom_nav_screens

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.hbapplicationgroupb.R
import com.example.hbapplicationgroupb.dataBase.db.UserPreferences
import com.example.hbapplicationgroupb.databinding.FragmentWishListBinding
import com.example.hbapplicationgroupb.model.wishlistdataclass.WishListDataClass
import com.example.hbapplicationgroupb.ui.allHotel.AllHotelsFragmentDirections
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

        token = activity?.let { UserPreferences(it).getSessionUser() }
        if (token == null){
            token = "1"
        }


        setUpWishListRecyclerView()

        loadListData()
        instanceOfWishListAdapter.allWishClickListener(object : WishListAdapter.AllWishesClickListener{
            override fun onItemSelected(position: Int, item: WishListDataClass) {
                val id = item.id
                val price = item.hotelPrice.toString()
                findNavController()
                    .navigate(
                        WishListFragmentDirections
                            .actionWishListFragment2ToHotelDescriptionFragment(
                                id,price
                            )
                    )
            }

            override fun bookNow(position: Int, item: WishListDataClass) {
                val name = item.hotelName
                val action =WishListFragmentDirections
                    .actionWishListFragment2ToBookingDetailsScreenFragment2(name)
                findNavController().navigate(action)
            }

            override fun toggleSaveItemToWishList(
                position: Int,
                saveItemTextBox: TextView,
                saveItemImage: ImageView,
                item: WishListDataClass
            ) {
                    saveItemTextBox.text = "Remove"
                    saveItemImage.visibility = View.VISIBLE
                    val wishListData = WishListDataClass(
                        id = item.id,
                        hotelName = item.hotelName,
                        hotelPrice = item.hotelPrice,
                        description = item.description,
                        percentage = item.percentage,
                        token = token!!,
                        featureImage = item.featureImage,
                        saved = true
                    )
                    roomViewModel.deleteWishListFromDb(wishListData)
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
        var token = activity?.let { UserPreferences(it).getSessionUser() }
        if (token == null){
            token = "1"
        }
        roomViewModel.getAllWishList(token).observe(viewLifecycleOwner,{
            instanceOfWishListAdapter.submitList(it)
            instanceOfWishListAdapter.notifyDataSetChanged()
            binding.wishListRecyclerViewId.adapter = instanceOfWishListAdapter
            Toast.makeText(requireContext(), "$it", Toast.LENGTH_SHORT).show()
        })
    }


}