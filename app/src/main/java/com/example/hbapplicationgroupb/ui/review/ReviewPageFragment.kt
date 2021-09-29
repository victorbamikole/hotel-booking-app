package com.example.hbapplicationgroupb.ui.review


import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.widget.RatingBar
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.hbapplicationgroupb.R
import com.example.hbapplicationgroupb.databinding.FragmentReviewPageBinding
import com.example.hbapplicationgroupb.ui.hoteldescription.adapter.HotelViewPagerAdapter
import com.example.hbapplicationgroupb.ui.review.adapter.ReviewPageFragmentRVAdapter
import com.example.hbapplicationgroupb.util.getListOfUserReview


class ReviewPageFragment : Fragment(R.layout.fragment_review_page) {
    //var of type of binding class created for xml file
    private lateinit var binding: FragmentReviewPageBinding
    private lateinit var reviewAdapter:ReviewPageFragmentRVAdapter
    var userRatings = 0
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentReviewPageBinding.bind(view)
        reviewAdapter = ReviewPageFragmentRVAdapter()
        reviewAdapter.getListOfReviews(getListOfUserReview())
        binding.fragmentReviewPageRecyclerView.adapter = reviewAdapter
        binding.fragmentReviewPageRecyclerView.layoutManager = LinearLayoutManager(requireContext())

        //to remove extra colour on top of toolbar
        requireActivity().window.addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)

        //read rating clicked and display toast
        binding.fragmentReviewPageStarViewRatingBar1.onRatingBarChangeListener =
            RatingBar.OnRatingBarChangeListener { p0, p1, p2 ->
                userRatings = p0.rating.toInt()
                Toast.makeText(
                    requireContext(), "star $p1 clicked and has ratings of $userRatings",
                    Toast.LENGTH_SHORT
                ).show()
                binding.fragmentReviewPageStarFiveSmallViewProgressBar.progress =
                    ((userRatings / 5) * 100)
                findNavController()
                    .navigate(R.id.action_reviewPageFragment_to_addReviewPageFragment)
            }
        binding.fragmentReviewPageStarViewRatingBar1.setOnClickListener {
                findNavController()
                    .navigate(R.id.action_reviewPageFragment_to_addReviewPageFragment)

        }


    }
    override fun onDetach() {
        //clear flag on detach of fragment
        requireActivity().window.clearFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
        super.onDetach()
    }
}