package com.example.hbapplicationgroupb

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.hbapplicationgroupb.databinding.FragmentAddReviewPageBinding


class ProfileScreenFragment : Fragment(R.layout.fragment_profile_screen) {

    //var of type of binding class created for xml file
    private lateinit var binding: FragmentAddReviewPageBinding
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //set binding to bind views when views have created
        binding = FragmentAddReviewPageBinding.bind(view)
    }

}