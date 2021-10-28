package com.example.hbapplicationgroupb.ui.review


import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.hbapplicationgroupb.R
import com.example.hbapplicationgroupb.databinding.FragmentReviewPageBinding
import com.example.hbapplicationgroupb.ui.review.adapter.ReviewPageFragmentRVAdapter
import com.example.hbapplicationgroupb.viewModel.RoomViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ReviewPageFragment : Fragment(R.layout.fragment_review_page) {
    //var of type of binding class created for xml file
    private lateinit var binding: FragmentReviewPageBinding
    private lateinit var reviewAdapter:ReviewPageFragmentRVAdapter
//    private val safeArgs : Review

    private val safeArgs : ReviewPageFragmentArgs by navArgs()
    private val roomViewModel : RoomViewModel by viewModels()
    var userRatings = 0

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val hotelId = safeArgs.hotelId
        val hotelRating = safeArgs.rating
        roomViewModel.getHotelReview(hotelId)

        binding = FragmentReviewPageBinding.bind(view)
        reviewAdapter = ReviewPageFragmentRVAdapter()
        binding.fragmentReviewPageRecyclerView.adapter = reviewAdapter
        binding.fragmentReviewPageRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.fragmentReviewPageStarViewRatingBar1.numStars = hotelRating.toInt()
        binding.fragmentReviewPageTvAverageRating.text = hotelRating.toString().substring(0,3)


        //to remove extra colour on top of toolbar
        requireActivity().window.addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)

       setBarRatingAndProgressBar()

        binding.fragmentReviewPageTvAddNewReview.setOnClickListener {
            val action = ReviewPageFragmentDirections
                .actionReviewPageFragmentToAddReviewPageFragment(hotelId)
            findNavController()
                .navigate(action)
        }
        binding.ratingBackArrow.setOnClickListener {
            findNavController()
                .navigate(R.id.action_reviewPageFragment_to_hotelDescriptionFragment)
        }

        binding.ratingBackArrow.setOnClickListener {
            findNavController().navigate(
                ReviewPageFragmentDirections.actionReviewPageFragmentToHotelDescriptionFragment(
                    hotelId))
        }

        roomViewModel.hotelReview.observe(viewLifecycleOwner,  { it
            if (it.data == null){
                binding.tvUiStateMessage.text = String.format("Loading ...")
                binding.tvUiStateMessage.visibility = View.VISIBLE
                binding.progressBar.visibility = View.VISIBLE

            }else if (it.data.isNotEmpty()){
                reviewAdapter.getListOfReviews(it.data)
                binding.tvUiStateMessage.visibility = View.GONE
                binding.progressBar.visibility = View.GONE
                binding.fragmentReviewPageTvTwentyFiveRatings.text = String.format("${it.data.size} reviews")
            }else{
                binding.tvUiStateMessage.text = String.format("There are no reviews for this hotel")
                binding.tvUiStateMessage.visibility = View.VISIBLE
                binding.progressBar.visibility = View.VISIBLE
                binding.fragmentReviewPageTvTwentyFiveRatings.text = String.format("0 reviews")
            }

        })

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