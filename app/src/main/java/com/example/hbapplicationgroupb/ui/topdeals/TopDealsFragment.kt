package com.example.hbapplicationgroupb.ui.topdeals

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.hbapplicationgroupb.R
import com.example.hbapplicationgroupb.dataBase.db.UserPreferences
import com.example.hbapplicationgroupb.databinding.FragmentTopDealsBinding
import com.example.hbapplicationgroupb.model.topDealAndHotel.TopDealAndHotelData
import com.example.hbapplicationgroupb.model.wishlistdataclass.WishListDataClass
import com.example.hbapplicationgroupb.viewModel.RoomViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TopDealsFragment : Fragment(R.layout.fragment_top_deals) {
    var binding: FragmentTopDealsBinding? = null
    private val roomViewModel : RoomViewModel by viewModels()
    private val myAdapter = TopDealsAdapter()
    private var token:String? = null


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentTopDealsBinding.bind(view)

        token = activity?.let { UserPreferences(it).getUserToken() }
        if (token == null){
            token = "1"
        }

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
        roomViewModel.allTopDeals.observe(viewLifecycleOwner,{
            if (it != null){
                myAdapter.populateTopDeals(it.data)

                binding!!.topDealsRecyclerView.adapter = myAdapter
                myAdapter.notifyDataSetChanged()

                //Set Click Listener on adapter list items
                myAdapter.setOnItemClickListener(object : TopDealsAdapter.SetItemClickListener{
                    override fun setOnItemClick(position: Int, myView: View?) {
                        val id = it.data[position].id
                        findNavController()
                            .navigate(
                            TopDealsFragmentDirections
                                .actionTopDealsFragmentToHotelDescriptionFragment(
                                    id
                                )
                            )
                    }


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
                            roomViewModel.deleteWishListFromDb(wishListData)
                        }

                }

                })

            }
        })
    }

}