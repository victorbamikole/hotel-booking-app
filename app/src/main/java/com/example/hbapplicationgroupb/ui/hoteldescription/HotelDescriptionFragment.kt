package com.example.hbapplicationgroupb.ui.hoteldescription

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.navigation.fragment.findNavController
import com.example.hbapplicationgroupb.R
import com.example.hbapplicationgroupb.databinding.FragmentHotelDescriptionBinding
import com.example.hbapplicationgroupb.ui.hoteldescription.adapter.HotelViewPagerAdapter
import com.example.hbapplicationgroupb.ui.hoteldescription.adapter.RoomsViewPagerAdapter
import com.example.hbapplicationgroupb.util.HorizontalMaginDecorationForViewPager
import com.example.hbapplicationgroupb.util.getListForBottomViewPager
import com.example.hbapplicationgroupb.util.getListOfHotelImages

class HotelDescriptionFragment : Fragment(R.layout.fragment_hotel_description) {
    private lateinit var binding:FragmentHotelDescriptionBinding
    private lateinit var hotelViewPagerAdapter:HotelViewPagerAdapter
    private lateinit var roomViewPageradapter: RoomsViewPagerAdapter
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentHotelDescriptionBinding.bind(view)
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

    }


    private fun initialiseViewPager() {
        binding.fragmentImageDescriptionViewPager.clipToPadding = true
        binding.fragmentImageDescriptionViewPager.clipChildren = false
        binding.fragmentImageDescriptionViewPager.offscreenPageLimit = 1

        hotelViewPagerAdapter = HotelViewPagerAdapter()
        hotelViewPagerAdapter.getImagesFromExternalSource(getListOfHotelImages())
        binding.fragmentImageDescriptionViewPager.adapter = hotelViewPagerAdapter

        //list of rooms viewpager
        binding.fragmentImageDescriptionViewPagerOurServices.clipToPadding = false
        binding.fragmentImageDescriptionViewPagerOurServices.clipChildren = false
        binding.fragmentImageDescriptionViewPagerOurServices.offscreenPageLimit = 1

        roomViewPageradapter = RoomsViewPagerAdapter()
        roomViewPageradapter.getImagesFromExternalSource(getListForBottomViewPager())
        binding.fragmentImageDescriptionViewPagerOurServices.adapter = roomViewPageradapter
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

        val itemDecoration = HorizontalMaginDecorationForViewPager(
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

        val itemDecoration = HorizontalMaginDecorationForViewPager(
            requireContext(),
            R.dimen.viewpager_current_item_horizontal_margin
        )
        binding.fragmentImageDescriptionViewPager.addItemDecoration(itemDecoration)

    }
}