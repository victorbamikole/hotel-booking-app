package com.example.hbapplicationgroupb.ui.privacy_policy_screen

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.espresso.Espresso
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.hbapplicationgroupb.R
import com.example.hbapplicationgroupb.ui.bottom_nav_screens.ExploreFragment
import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class PrivacyPolicyFragmentTest {

    @Test
    fun testViews() {
        /**Initializing the scenario for the fragment*/
        val scenario = launchFragmentInContainer<PrivacyPolicyFragment>()
        //Check if the first text is visible
        Espresso.onView(ViewMatchers.withId(R.id.ptextView2))
            .check(ViewAssertions.matches((ViewMatchers.isDisplayed())))
        Espresso.onView(ViewMatchers.withId(R.id.ptextView3))
            .check(ViewAssertions.matches((ViewMatchers.isDisplayed())))
        Espresso.onView(ViewMatchers.withId(R.id.ptextView4))
            .check(ViewAssertions.matches((ViewMatchers.isDisplayed())))
        Espresso.onView(ViewMatchers.withId(R.id.ptextView6))
            .check(ViewAssertions.matches((ViewMatchers.isDisplayed())))
        Espresso.onView(ViewMatchers.withId(R.id.ptextView7))
            .check(ViewAssertions.matches((ViewMatchers.isDisplayed())))
    }
}