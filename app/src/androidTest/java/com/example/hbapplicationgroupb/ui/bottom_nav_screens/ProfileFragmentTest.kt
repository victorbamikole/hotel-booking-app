package com.example.hbapplicationgroupb.ui.bottom_nav_screens

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
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@LargeTest
class ProfileFragmentTest{
/**
    @get:Rule
    val activityRule: ActivityScenarioRule<MainActivity> =
        ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun test_validateThatViewsAreVisible() {
        /**
         * code to navigate to profileFragment goes here
         */
        /**
         * TEST TO CHECK IF PROFILE NAME TEXT IS VISIBLE
         * */

        Espresso.onView(withId(R.id.fragment_profile_name_tv)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))

        /**
         * TEST TO CHECK IF HISTORY ICON IS VISIBLE
         * **/

        Espresso.onView(withId(R.id.fragment_profile_history_icon)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))

        /**
         * TEST TO CHECK IF HELP ICON IS VISIBLE
         * **/

        Espresso.onView(withId(R.id.fragment_profile_help_icon)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))

        /**
         * TEST TO CHECK IF HELP LOGOUT TEXT IS VISIBLE
         * **/

        Espresso.onView(withId(R.id.fragment_profile_log_out_btn)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))

        /**
         * TEST TO CHECK IF BOOKING SCREEN ICON IS VISIBLE
         * **/

        Espresso.onView(withId(R.id.fragment_profile_history_icon)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    /**
     * TEST TO CHECK IF EDIT PROFILE  TEXT IS CLICKED
     * **/

    @Test
    fun test_testToAssertainThatTheEditProfileTextHasBeenClicked() {
        Espresso.onView(withId(R.id.fragment_profile_edit_profile_tv)).perform(ViewActions.click())
    }

    /**
     * TEST TO CHECK IF BOOKING HISTORY ICON IS CLICKED
     * **/

    @Test
    fun test_testToAscertainThatBookingHistoryIconHasBeenClicked() {
        Espresso.onView(withId(R.id.fragment_profile_history_icon)).perform(ViewActions.click())
    }

    /**
     * TEST TO CHECK IF HELP AND SUPPORT ICON IS CLICKED
     * **/

    @Test
    fun test_testToAscertainThatHelpAndSupportIconHasBeenClicked() {
        Espresso.onView(withId(R.id.fragment_profile_help_icon)).perform(ViewActions.click())
    }

    /**
     * TEST TO CHECK IF UPLOAD IMAGE ICON IS CLICKED
     * **/

    @Test
    fun test_testToAscertainUploadImageIconHasBeenClicked() {
        Espresso.onView(withId(R.id.fragment_profile_upload_iv)).perform(ViewActions.click())
    }

    /**
     * TEST TO CHECK IF LOG OUT TEXT IS CLICKED
     * **/

    @Test
    fun test_testToAscertainLogOutTextHasBeenClicked() {
        Espresso.onView(withId(R.id.fragment_profile_log_out_btn)).perform(ViewActions.click())
    }
   **/
}