package com.example.hbapplicationgroupb.ui.topdeals

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.espresso.Espresso
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.hbapplicationgroupb.R
import com.example.hbapplicationgroupb.ui.tophotels.TopHotelsFragment
import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class TopDealsAndHotelFragmentTest{

    @Test
    fun testViewsTopDealsScreen() {
        /**Initializing the scenario for the fragment*/
        val scenario = launchFragmentInContainer<TopDealsFragment>()

        //Check if the recyclerview is visible
        Espresso.onView(ViewMatchers.withId(R.id.topDealsRecyclerView))
            .check(ViewAssertions.matches((ViewMatchers.isDisplayed())))

    }
}