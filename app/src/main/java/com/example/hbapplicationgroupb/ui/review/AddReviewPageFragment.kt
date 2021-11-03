package com.example.hbapplicationgroupb.ui.review

import android.opengl.Visibility
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
import com.example.hbapplicationgroupb.di.application.HotelApplication
import com.example.hbapplicationgroupb.model.addRatings.AddRatingsPost
import com.example.hbapplicationgroupb.model.addReviews.AddReviewsPost
import com.example.hbapplicationgroupb.util.resource.ConnectivityLiveData
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

    private lateinit var connectivityLiveData: ConnectivityLiveData
    private var isNetworkAvailable : Boolean = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        connectivityLiveData = ConnectivityLiveData(HotelApplication.application)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //set binding to bind views when views have created
        binding = FragmentAddReviewPageBinding.bind(view)
        //to remove extra colour on top of toolbar
        requireActivity().window.addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)

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

        binding.addRatingBackArrow.setOnClickListener {
            val action = AddReviewPageFragmentDirections
                .actionAddReviewPageFragmentToReviewPageFragment(hotelId,rating)
            findNavController().navigate(action)
        }

        connectivityLiveData.observe(viewLifecycleOwner, Observer { isAvailable ->
            when (isAvailable) {
                true -> {
                    binding.networkConnectionTextId.visibility = View.GONE
                    isNetworkAvailable = true
                }
                false -> {
                    binding.networkConnectionTextId.visibility = View.VISIBLE
                    isNetworkAvailable = false
                }
            }
        })

        binding.fragmentAddReviewPageTvPostRed.setOnClickListener {
            initAddRatings()
            addRatings()
            addReviews()
            val action = AddReviewPageFragmentDirections
                .actionAddReviewPageFragmentToReviewPageFragment(hotelId,rating)
            findNavController().navigate(action)

        }
        binding.fragmentReviewPageStarViewRatingBar1.rating = userRatings.toFloat()
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
                    else -> ("click to change rating v" +
                            "alue").also { binding.fragmentAddReviewPageRatingRemark.text = it }
                }
            }
    }

    private fun addRatings(){
        val comment = binding.fragmentAddReviewCommentTi.text.toString().trim()
        roomViewModel.addReviewsVM(AddReviewsPost(comment,_hotelId), "Bearer $token")
    }
    private fun addReviews(){
        val ratings =  binding.fragmentReviewPageStarViewRatingBar1.rating.toInt()
        roomViewModel.addRatingsVM(_hotelId, AddRatingsPost(ratings), "Bearer $token")
    }

    private fun initAddRatings(){
        binding.progressBar.visibility = View.VISIBLE
        roomViewModel.addRatings.observe(viewLifecycleOwner, Observer { it
            if (it.status == 200 || it.status ==201){
                binding.progressBar.visibility = View.GONE
            }
        })
    }

    override fun onDetach() {
        //clear flag on detach of fragment
        requireActivity().window.clearFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
        super.onDetach()
    }
}