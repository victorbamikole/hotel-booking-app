package com.example.hbapplicationgroupb.ui.review

import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.WindowManager
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.hbapplicationgroupb.R
import com.example.hbapplicationgroupb.dataBase.db.UserPreferences
import com.example.hbapplicationgroupb.databinding.FragmentAddReviewPageBinding
import com.example.hbapplicationgroupb.model.addRatings.AddRatingsPost
import com.example.hbapplicationgroupb.model.addReviews.AddReviewsPost
import com.example.hbapplicationgroupb.viewModel.RoomViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddReviewPageFragment : Fragment(R.layout.fragment_add_review_page) {
    var userRatings = 0
    //var of type of binding class created for xml file
    private lateinit var binding: FragmentAddReviewPageBinding

    private val args: ReviewPageFragmentArgs by navArgs()
    private val roomViewModel: RoomViewModel by viewModels()
    private lateinit  var _hotelId: String
    private  var token: String? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //set binding to bind views when views have created
        binding = FragmentAddReviewPageBinding.bind(view)
        //to remove extra colour on top of toolbar
        requireActivity().window.addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)

        roomViewModel.addReviews.observe(viewLifecycleOwner, Observer {
            Toast.makeText(requireContext(),"$it", Toast.LENGTH_LONG).show()
        })

        /**
         * passing the @hotelId, @token, and @rating to make @POST api call
         * **/
        val hotelId = args.hotelId
        _hotelId = hotelId
        val rating = args.rating
        token = activity?.let {
            UserPreferences(it).getUserToken()
        }
        if (token==null){
            token="1"
        }

        binding.fragmentAddReviewPageTvPostRed.setOnClickListener {
            val comment = binding.fragmentAddReviewCommentTi.text.toString()
            roomViewModel.addReviewsVM(AddReviewsPost(comment,hotelId), token!!)
            Log.d("COMMENT", comment )

            val ratings =  binding.fragmentReviewPageStarViewRatingBar1.rating.toInt()
            roomViewModel.addRatingsVM(_hotelId, AddRatingsPost(ratings), token!!)
            Log.d("COMMENT","$ratings")

        }
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
//        binding.fragmentAddReviewPageTvPostRed.setOnClickListener {
//            findNavController()
//                .navigate(R.id.action_addReviewPageFragment_to_reviewPageFragment)
//        }
        binding.addRatingBackArrow.setOnClickListener {
            findNavController().navigate(R.id.action_addReviewPageFragment_to_reviewPageFragment)
        }

        binding.addRatingBackArrow.setOnClickListener {
            findNavController()
                .navigate(R.id.action_addReviewPageFragment_to_hotelDescriptionFragment)
        }
    }



    override fun onDetach() {
        //clear flag on detach of fragment
        requireActivity().window.clearFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
        super.onDetach()
    }
}