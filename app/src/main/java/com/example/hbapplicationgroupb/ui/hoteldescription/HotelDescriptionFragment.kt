package com.example.hbapplicationgroupb.ui.hoteldescription

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.hbapplicationgroupb.R
import com.example.hbapplicationgroupb.databinding.FragmentHotelDescriptionBinding
import com.example.hbapplicationgroupb.ui.hoteldescription.adapter.HotelViewPagerAdapter
import com.example.hbapplicationgroupb.ui.hoteldescription.adapter.RoomsViewPagerAdapter
import com.example.hbapplicationgroupb.util.HorizontalMarginDecorationForViewPager
import com.example.hbapplicationgroupb.util.getListOfHotelImages
import com.example.hbapplicationgroupb.viewModel.RoomViewModel
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

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

        roomViewModel.getHotelDescription("8d3c676d-834c-4980-bd45-c7dc70a00d55")

        initialiseViewPager()
        setUpViewPagerTransition()

        binding.bookNowButton.setOnClickListener {
            findNavController()
                .navigate(R.id.action_hotelDescriptionFragment_to_bookingDetailsScreenFragment2)
        }
        binding.fragmentReviewPageStarViewRatingBarVerySmall4.rating = 4.5f
            binding.addStarRatingContainer.setOnClickListener {
                findNavController()
                    .navigate(R.id.action_hotelDescriptionFragment_to_reviewPageFragment)
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
                binding.fragmentReviewPageStarViewRatingBarVerySmall4.numStars = 4//it.rating.toInt()
//                binding.tvHotelPrice.text = String.format("$ ${safeArgs.hotelPrice}")

                //Set Room types for roomViewPagerAdapter
                roomViewPagerAdapter.populateHotelRooms(it.roomTypes.toMutableList())

                //Set Hotel Images on hotelViewPagerAdapter
                hotelViewPagerAdapter.getImagesFromExternalSource(it.gallery)
            } else{
                Snackbar.make(view,"No data retrieved fo this hotel", Snackbar.LENGTH_SHORT).show()
            }

        })

    }


    private fun initialiseViewPager() {
        binding.fragmentImageDescriptionViewPager.clipToPadding = true
        binding.fragmentImageDescriptionViewPager.clipChildren = false
        binding.fragmentImageDescriptionViewPager.offscreenPageLimit = 1

        hotelViewPagerAdapter = HotelViewPagerAdapter()
//        hotelViewPagerAdapter.getImagesFromExternalSource()
        binding.fragmentImageDescriptionViewPager.adapter = hotelViewPagerAdapter

        //list of rooms viewpager
        binding.fragmentImageDescriptionViewPagerOurServices.clipToPadding = false
        binding.fragmentImageDescriptionViewPagerOurServices.clipChildren = false
        binding.fragmentImageDescriptionViewPagerOurServices.offscreenPageLimit = 1

        roomViewPagerAdapter = RoomsViewPagerAdapter()

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

}