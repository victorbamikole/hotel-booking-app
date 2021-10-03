package com.example.hbapplicationgroupb.ui.review

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.RatingBar
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.hbapplicationgroupb.R
import com.example.hbapplicationgroupb.databinding.FragmentAddReviewPageBinding
import com.example.hbapplicationgroupb.databinding.FragmentReviewPageBinding

class AddReviewPageFragment : Fragment(R.layout.fragment_add_review_page) {
    var userRatings = 0
    //var of type of binding class created for xml file
    private lateinit var binding: FragmentAddReviewPageBinding
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //set binding to bind views when views have created
        binding = FragmentAddReviewPageBinding.bind(view)
        //to remove extra colour on top of toolbar
        requireActivity().window.addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)

        //read rating when user click on star and display text
        binding.fragmentReviewPageStarViewRatingBar1.setOnRatingBarChangeListener {  ratingBar, fl, b ->

                userRatings = ratingBar.rating.toInt()
                Toast.makeText(requireContext(),"star $fl clicked and has ratings of $userRatings",
                    Toast.LENGTH_SHORT).show()
                when(fl.toInt()){
                    1 -> "Very poor".also { binding.fragmentAddReviewPageRatingRemark.text = it }
                    2 -> "Poor".also { binding.fragmentAddReviewPageRatingRemark.text = it }
                    3 -> "Good".also { binding.fragmentAddReviewPageRatingRemark.text = it }
                    4 -> "Very Good".also { binding.fragmentAddReviewPageRatingRemark.text = it }
                    5 -> "Excellent".also { binding.fragmentAddReviewPageRatingRemark.text = it }
                    else -> "click to change rating value".also { binding.fragmentAddReviewPageRatingRemark.text = it }
                }


            }
        binding.fragmentAddReviewPageTvPostRed.setOnClickListener {
            findNavController()
                .navigate(R.id.action_addReviewPageFragment_to_reviewPageFragment)
        }
        binding.addRatingBackArrow.setOnClickListener {
            findNavController().navigate(R.id.action_addReviewPageFragment_to_reviewPageFragment)
        }
    }
    override fun onDetach() {
        //clear flag on detach of fragment
        requireActivity().window.clearFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
        super.onDetach()
    }
}