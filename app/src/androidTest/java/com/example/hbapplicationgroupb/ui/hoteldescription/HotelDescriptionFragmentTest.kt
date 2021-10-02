package com.example.hbapplicationgroupb.ui.hoteldescription

import androidx.fragment.app.testing.FragmentScenario
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.lifecycle.Lifecycle
import androidx.test.espresso.Espresso
import androidx.test.espresso.ViewAssertion
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.example.hbapplicationgroupb.MainActivity
import com.example.hbapplicationgroupb.R
import com.example.hbapplicationgroupb.ui.bookingDetailsScreen.bookingDetailsScreenFragment.BookingDetailsScreenFragment
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@LargeTest
class HotelDescriptionFragmentTest{


    /**Initializing the scenario for the fragment*/
    private lateinit var scenario: FragmentScenario<HotelDescriptionFragment>

    @Before
    fun setUp() {
        scenario = launchFragmentInContainer(themeResId= R.style.Theme_HBApplicationGroupB)
        scenario.moveToState(Lifecycle.State.STARTED)
    }

    @Test
    fun test_areViewsVisibleWhenAppNavigateToFragment(){
        /**
         * code to navigate to hotelDescription fragment goes here
         */
        //check if viewpager is visible
        Espresso.onView(withId(R.id.fragment_image_description_viewPager))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        //check if expandable textView is visible
        Espresso.onView(withId(R.id.hotel_desc_expandable_tv))
            .check(ViewAssertions.matches(ViewMatchers.withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)))
        //check if gallery button is displayed
        Espresso.onView(withId(R.id.fragment_hotel_description_cardClick_to_Gallery))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
       //check if wifi icon is visible
        Espresso.onView(withId(R.id.fragment_hotel_description_card_wifi))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))

        //check if breakfast icon is visible
        Espresso.onView(withId(R.id.fragment_hotel_description_card_breakfast))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))

        //check if pets icon is visible
        Espresso.onView(withId(R.id.fragment_hotel_description_card_pets))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))

        //check if bars icon is visible
        Espresso.onView(withId(R.id.fragment_hotel_description_card_bars))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))

        //check if pool icon is visible
        Espresso.onView(withId(R.id.fragment_hotel_description_card_pool))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))

        //check if more icon is visible
        Espresso.onView(withId(R.id.fragment_hotel_description_card_more))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))

        //check if tv is visible
        Espresso.onView(withId(R.id.fragment_hotel_description_tv_pool))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))

        //check if layout is visible
        Espresso.onView(withId(R.id.fragment_hotel_description_layout_emailAndPhone))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))

        //check if email text is visible
        Espresso.onView(withId(R.id.fragment_hotel_description_tv_email))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))

        //check if phone number is visible
        Espresso.onView(withId(R.id.fragment_hotel_description_tv_phonrNumer))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))

        //check if layout is visible
        Espresso.onView(withId(R.id.fragment_hotel_description_layout_priceRatingReview))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))

        //check if star rating icon is visible
        Espresso.onView(withId(R.id.fragment_review_page_star_view_ratingBar_verySmall4))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))

        //check if our services textview is visible
        Espresso.onView(withId(R.id.fragment_hotel_description_tv_service))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))

        //check if bottom viewpager is visible
        Espresso.onView(withId(R.id.fragment_image_description_viewPager_ourServices))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

}