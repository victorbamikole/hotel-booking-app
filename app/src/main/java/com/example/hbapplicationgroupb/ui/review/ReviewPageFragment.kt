package com.example.hbapplicationgroupb.ui.review


import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.fragment.app.Fragment
import com.example.hbapplicationgroupb.R
import com.example.hbapplicationgroupb.databinding.FragmentReviewPageBinding


class ReviewPageFragment : Fragment(R.layout.fragment_review_page) {
    //var of type of binding class created for xml file
    private lateinit var binding: FragmentReviewPageBinding
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //set binding to bind views when views have created
        binding = FragmentReviewPageBinding.bind(view)
        //to remove extra colour on top of toolbar
        requireActivity().window.addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)

    }
    override fun onDetach() {
        //clear flag on detach of fragment
        requireActivity().window.clearFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
        super.onDetach()
    }
}