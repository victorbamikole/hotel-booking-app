package com.example.hbapplicationgroupb.ui.hoteldescription

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.hbapplicationgroupb.R
import com.example.hbapplicationgroupb.databinding.FragmentHotelDescriptionBinding
import com.example.hbapplicationgroupb.ui.hoteldescription.adapter.HotelViewPagerAdapter
import com.example.hbapplicationgroupb.ui.hoteldescription.adapter.RoomsViewPagerAdapter
import com.example.hbapplicationgroupb.ui.topdeals.TopDealsFragmentDirections
import com.example.hbapplicationgroupb.util.HorizontalMarginDecorationForViewPager
import com.example.hbapplicationgroupb.util.getListOfHotelImages
import com.example.hbapplicationgroupb.viewModel.RoomViewModel
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlin.properties.Delegates

@AndroidEntryPoint
class HotelDescriptionFragment : Fragment(R.layout.fragment_hotel_description) {
    private lateinit var binding:FragmentHotelDescriptionBinding
    private lateinit var hotelViewPagerAdapter:HotelViewPagerAdapter
    private lateinit var roomViewPagerAdapter: RoomsViewPagerAdapter
    private val roomViewModel : RoomViewModel by viewModels()
    private val safeArgs :HotelDescriptionFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentHotelDescriptionBinding.bind(view)
        val hotelId = safeArgs.hotelId
        roomViewModel.getHotelDescription(hotelId)
        roomViewModel.getHotelReview(hotelId)
        observeHotelReview()

        initialiseViewPager()
        setUpViewPagerTransition()

        binding.bookNowButton.setOnClickListener {
            val name = binding.HotelName.text.toString()
            val action = HotelDescriptionFragmentDirections
                .actionHotelDescriptionFragmentToBookingDetailsScreenFragment2(name)
            findNavController().navigate(action)
        }
        binding.fragmentReviewPageStarViewRatingBarVerySmall4.rating = 4.5f

            binding.addStarRatingContainer.setOnClickListener {
                val action = HotelDescriptionFragmentDirections.actionHotelDescriptionFragmentToReviewPageFragment(hotelId, 0f)
                findNavController()
                    .navigate(action)
            }
        binding.addRatingBackArrow.setOnClickListener {
            findNavController()
                .navigate(R.id.action_hotelDescriptionFragment_to_exploreFragment2)
        }

        //Observe Hotel Description in viewModel
        roomViewModel.hotelDescription.observe(viewLifecycleOwner, {
            if (it != null){
                binding.HotelName.text = it.name
                binding.locationOfHotel.text = it.address
                binding.fragmentHotelDescriptionTvPhonrNumer.text = it.phone
                binding.fragmentHotelDescriptionTvEmail.text = it.email
                binding.hotelDescExpandableTv.text = it.description
                binding.fragmentReviewPageStarViewRatingBarVerySmall4.rating = it.rating.toFloat()
//                binding.tvHotelPrice.text = String.format("$ ${safeArgs.hotelPrice}")

                val hotelRating = it.rating

                //Set Room types for roomViewPagerAdapter
                roomViewPagerAdapter.populateHotelRooms(it.roomTypes.toMutableList())

                //Set Hotel Images on hotelViewPagerAdapter
                hotelViewPagerAdapter.getImagesFromExternalSource(it.gallery)

                navigateToHotelReviews(hotelId,hotelRating,binding.reviewsHolder)
                navigateToHotelReviews(hotelId,hotelRating,binding.addStarRatingContainer )

            } else{
                Snackbar.make(view,"No data retrieved for this hotel", Snackbar.LENGTH_SHORT).show()
            }

        })


    }


    private fun initialiseViewPager() {
        binding.fragmentImageDescriptionViewPager.clipToPadding = true
        binding.fragmentImageDescriptionViewPager.clipChildren = false
        binding.fragmentImageDescriptionViewPager.offscreenPageLimit = 1

        hotelViewPagerAdapter = HotelViewPagerAdapter()
        binding.fragmentImageDescriptionViewPager.adapter = hotelViewPagerAdapter

        //list of rooms viewpager
        binding.fragmentImageDescriptionViewPagerOurServices.clipToPadding = false
        binding.fragmentImageDescriptionViewPagerOurServices.clipChildren = false
        binding.fragmentImageDescriptionViewPagerOurServices.offscreenPageLimit = 1

        roomViewPagerAdapter = RoomsViewPagerAdapter()
        setClickListenerOnGalleryIcon()

        binding.fragmentImageDescriptionViewPagerOurServices.adapter = roomViewPagerAdapter

    }
    private fun setUpViewPagerTransition() {
        setTopViewPagerForListOfHotels()
        setTopViewPagerForListOfRooms()
    }

    private fun setTopViewPagerForListOfRooms() {
        /**
         * Add a PageTransformer that translates the next and previous items horizontally from left to right
         * towards the center of the screen, which makes them
         */
        //visible section of next item in viewpager2
        val nextItemVisiblePx = resources.getDimension(R.dimen.viewpager_next_item_visible_room)
        //margin between item and next item
        val currentItemHorizontalMarginPx = resources
            .getDimension(R.dimen.viewpager_current_item_horizontal_margin_room)
        val pageTranslationX = nextItemVisiblePx + currentItemHorizontalMarginPx

        binding.fragmentImageDescriptionViewPagerOurServices
            .setPageTransformer{ page: View, position:Float ->
                page.translationX = -pageTranslationX * position
                // this line scales the item's height
                page.scaleY = 1f
            }

        val itemDecoration = HorizontalMarginDecorationForViewPager(
            requireContext(),
            R.dimen.viewpager_current_item_horizontal_margin
        )
        binding.fragmentImageDescriptionViewPagerOurServices.addItemDecoration(itemDecoration)
    }

    private fun setTopViewPagerForListOfHotels() {

        //visible section of next item in viewpager2
        val nextItemVisiblePx = resources.getDimension(R.dimen.viewpager_next_item_visible)
        //margin between item and next item
        val currentItemHorizontalMarginPx = resources
            .getDimension(R.dimen.viewpager_current_item_horizontal_margin)

        val pageTranslationX = nextItemVisiblePx + currentItemHorizontalMarginPx

        binding.fragmentImageDescriptionViewPager.setPageTransformer{ page: View, position:Float ->
            page.translationX = -pageTranslationX * position

            // this line scales the item's height
            page.scaleY = 1f
        }

        val itemDecoration = HorizontalMarginDecorationForViewPager(
            requireContext(),
            R.dimen.viewpager_current_item_horizontal_margin
        )
        binding.fragmentImageDescriptionViewPager.addItemDecoration(itemDecoration)

    }


    //Navigate to hotel review page
    private fun navigateToHotelReviews(hotelId : String, rating: Double, view : View){
        view.setOnClickListener {
            findNavController()
                .navigate(HotelDescriptionFragmentDirections
                    .actionHotelDescriptionFragmentToReviewPageFragment
                        (hotelId,rating.toFloat()))
        }
    }


    //Observe hotelReview in roomViewModel
    @SuppressLint("ResourceAsColor")
    private fun observeHotelReview(){
        binding.apply {
            cvFourthImage.visibility = View.GONE
            cvThirdImage.visibility = View.GONE
            cvSecondImage.visibility = View.GONE
            cvFirstImage.visibility = View.GONE
            imageReviewCount.text = String.format("0")
        }
        roomViewModel.hotelReview.observe(viewLifecycleOwner,{
            if(it.data != null){
                if(it.data.isEmpty()){
                    binding.apply {
                        tinyImageView4.visibility = View.GONE
                        tinyImageView3.visibility = View.GONE
                        tinyImageView2.visibility = View.GONE
                        imageReviewCount.text = String.format("0")
                    }
                } else if (it.data.size == 1)
                    {
                        binding.apply {
                            cvFirstImage.visibility = View.VISIBLE
                            cvSecondImage.visibility = View.GONE
                            cvThirdImage.visibility = View.GONE
                            cvFourthImage.visibility = View.GONE
                            imageReviewCount.visibility = View.GONE
                        }

                } else if (it.data.size == 2)
                    {
                        binding.apply {
                            cvFirstImage.visibility = View.VISIBLE
                            cvSecondImage.visibility = View.VISIBLE
                            cvFourthImage.visibility = View.GONE
                            tinyImageView2.visibility = View.VISIBLE
                            imageReviewCount.visibility = View.GONE
                            cvThirdImage.visibility = View.GONE
                            activity?.let { fragment ->
                                Glide.with(fragment)
                                    .load(it.data[0].avatar)
                                    .into(tinyImageView1)
                                Glide.with(fragment)
                                    .load(it.data[1].avatar)
                                    .into(tinyImageView2)
                            }
                        }

                } else if (it.data.size == 3)
                {
                    binding.apply {
                        cvFirstImage.visibility = View.VISIBLE
                        cvFourthImage.visibility = View.GONE
                        binding.imageReviewCount.visibility = View.GONE
                        cvSecondImage.visibility = View.VISIBLE
                        cvFourthImage.visibility = View.GONE
                        tinyImageView2.visibility = View.VISIBLE
                        tinyImageView3.visibility = View.VISIBLE
                        binding.imageReviewCount.visibility = View.GONE
                        cvThirdImage.visibility = View.VISIBLE

                        activity?.let { fragment ->
                            Glide.with(fragment)
                                .load(it.data[0].avatar)
                                .into(binding.tinyImageView1)
                            Glide.with(fragment)
                                .load(it.data[1].avatar)
                                .into(binding.tinyImageView2)
                            Glide.with(fragment)
                                .load(it.data[2].avatar)
                                .into(binding.tinyImageView3)

                        }
                    }

                }else if (it.data.size == 4) {
                    binding.apply {
                        cvFirstImage.visibility = View.VISIBLE
                        cvFourthImage.visibility = View.VISIBLE
                        binding.imageReviewCount.visibility = View.GONE
                        cvSecondImage.visibility = View.VISIBLE
                        cvFourthImage.visibility = View.GONE
                        tinyImageView2.visibility = View.VISIBLE
                        tinyImageView3.visibility = View.VISIBLE
                        tinyImageView4.visibility = View.VISIBLE
                        binding.imageReviewCount.visibility = View.GONE
                        cvThirdImage.visibility = View.VISIBLE

                        activity?.let { fragment ->
                            Glide.with(fragment)
                                .load(it.data[0].avatar)
                                .into(binding.tinyImageView1)
                            Glide.with(fragment)
                                .load(it.data[1].avatar)
                                .into(binding.tinyImageView2)
                            Glide.with(fragment)
                                .load(it.data[2].avatar)
                                .into(binding.tinyImageView3)
                            Glide.with(fragment)
                                .load(it.data[3].avatar)
                                .into(binding.tinyImageView4)
                    } }

                } else{
                    binding.apply {
                        cvFirstImage.visibility = View.VISIBLE
                        cvFourthImage.visibility = View.VISIBLE
                        binding.imageReviewCount.visibility = View.GONE
                        cvSecondImage.visibility = View.VISIBLE
                        cvFourthImage.visibility = View.GONE
                        tinyImageView2.visibility = View.VISIBLE
                        tinyImageView3.visibility = View.VISIBLE
                        tinyImageView4.visibility = View.VISIBLE
                        binding.imageReviewCount.visibility = View.GONE
                        cvThirdImage.visibility = View.VISIBLE

                        activity?.let { fragment ->
                            Glide.with(fragment)
                                .load(it.data[0].avatar)
                                .into(binding.tinyImageView1)
                            Glide.with(fragment)
                                .load(it.data[1].avatar)
                                .into(binding.tinyImageView2)
                            Glide.with(fragment)
                                .load(it.data[2].avatar)
                                .into(binding.tinyImageView3)
                            Glide.with(fragment)
                                .load(it.data[3].avatar)
                                .into(binding.tinyImageView4)
                        }


                    }
                }

            }else{
                binding.apply {
                    tinyImageView4.visibility = View.GONE
                    tinyImageView3.visibility = View.GONE
                    tinyImageView2.visibility = View.GONE
                    imageReviewCount.text = String.format("0")}
            }
        })
    }

    private fun setClickListenerOnGalleryIcon(){
        binding.fragmentHotelDescriptionCardClickToGallery.setOnClickListener {
            if (binding.fragmentImageDescriptionViewPager.currentItem+1 < hotelViewPagerAdapter.itemCount){
                binding.fragmentImageDescriptionViewPager.currentItem ++
            }else{
                binding.fragmentImageDescriptionViewPager.currentItem = 1

            }
        }
    }

}