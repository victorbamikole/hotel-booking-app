package com.example.hbapplicationgroupb.ui.review


import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.hbapplicationgroupb.R
import com.example.hbapplicationgroupb.databinding.FragmentReviewPageBinding


class ReviewPageFragment : Fragment(R.layout.fragment_review_page) {
    private lateinit var binding: FragmentReviewPageBinding
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentReviewPageBinding.bind(view)

    }
}