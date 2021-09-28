package com.example.hbapplicationgroupb.ui.bottom_nav_screens

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.hbapplicationgroupb.R
import com.example.hbapplicationgroupb.databinding.FragmentWishListBinding
import com.example.hbapplicationgroupb.model.dataclass.WishListDataClass
import com.example.hbapplicationgroupb.ui.adapters.WishListAdapter

class WishListFragment : Fragment(R.layout.fragment_wish_list) {
    private lateinit var binding : FragmentWishListBinding
    private lateinit var instanceOfWishListAdapter : WishListAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentWishListBinding.bind(view)

        setUpWishListRecyclerView()
    }

    private fun setUpWishListRecyclerView() {
        instanceOfWishListAdapter = WishListAdapter()
        instanceOfWishListAdapter.hotels = listOf(
            WishListDataClass("Taj Waterside", "$6999"),
            WishListDataClass("Park Plaza", "$6999")
        )

        binding.wishListRecyclerViewId.apply {
            adapter = instanceOfWishListAdapter
            layoutManager = LinearLayoutManager(activity)
            setHasFixedSize(true)
        }
    }

}