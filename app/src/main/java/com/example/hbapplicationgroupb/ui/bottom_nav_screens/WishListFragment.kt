package com.example.hbapplicationgroupb.ui.bottom_nav_screens

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.hbapplicationgroupb.R
import com.example.hbapplicationgroupb.databinding.FragmentWishListBinding
import com.example.hbapplicationgroupb.model.dataclass.WishListDataClass
import com.example.hbapplicationgroupb.viewModel.RoomViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class WishListFragment : Fragment(R.layout.fragment_wish_list) {
    private lateinit var binding : FragmentWishListBinding
    private lateinit var instanceOfWishListAdapter : WishListAdapter
    private val roomViewModel: RoomViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentWishListBinding.bind(view)

        setUpWishListRecyclerView()
    }

    /**
     * Set up recyclerview for wishlist screen
     */

    private fun setUpWishListRecyclerView() {
        roomViewModel.wishList.observe(viewLifecycleOwner,{
            val list:List<WishListDataClass> = it.toList()
            instanceOfWishListAdapter.hotels = list
        })

        instanceOfWishListAdapter = WishListAdapter()


        binding.wishListRecyclerViewId.apply {
            adapter = instanceOfWishListAdapter
            layoutManager = LinearLayoutManager(activity)
            setHasFixedSize(true)
        }
    }

}