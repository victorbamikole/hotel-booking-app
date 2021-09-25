package com.example.hbapplicationgroupb

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.hbapplicationgroupb.databinding.FragmentWishListBinding

class WishListFragment : Fragment(R.layout.fragment_wish_list) {
    var binding : FragmentWishListBinding? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentWishListBinding.bind(view)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

}