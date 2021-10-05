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
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
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

       setBarRatingAndProgressBar()


        binding.fragmentReviewPageTvAddNewReview.setOnClickListener {
            findNavController()
                .navigate(R.id.action_reviewPageFragment_to_addReviewPageFragment)
        }
        binding.ratingBackArrow.setOnClickListener {
            findNavController()
                .navigate(R.id.action_addReviewPageFragment_to_hotelDescriptionFragment)
        }

    }

    private fun setBarRatingAndProgressBar() {
        //read rating clicked and display toast
        binding.fragmentReviewPageStarViewRatingBar1.rating = 4.5f

        //set up five small rating with progress bar
        binding.fragmentReviewPageStarViewRatingBarVerySmall5.rating = 4f
        binding.fragmentReviewPageStarFiveSmallViewProgressBar.progress =
            ((binding.fragmentReviewPageStarViewRatingBarVerySmall5.rating /5 *100).toInt())

        //set up four small rating with progress bar
        binding.fragmentReviewPageStarViewRatingBarVerySmall4.rating = 3.5f
        binding.fragmentReviewPageStarFourSmallViewProgressBar.progress =
            ((binding.fragmentReviewPageStarViewRatingBarVerySmall4.rating /4 *100).toInt())

        //set up three small rating with progress bar
        binding.fragmentReviewPageStarViewRatingBarVerySmall3.rating =3f
        binding.fragmentReviewPageStarThreeSmallViewProgressBar.progress=
            ((binding.fragmentReviewPageStarViewRatingBarVerySmall3.rating /3 *100).toInt())
    }

    override fun onDetach() {
        //clear flag on detach of fragment
        requireActivity().window.clearFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
        super.onDetach()
    }
}