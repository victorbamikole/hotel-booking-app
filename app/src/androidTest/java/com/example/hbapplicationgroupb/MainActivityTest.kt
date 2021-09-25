package com.example.hbapplicationgroupb

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@LargeTest
class MainActivityTest{

    private lateinit var stringToBeTyped: String

    @get:Rule
    var activityRule: ActivityScenarioRule<MainActivity>
            = ActivityScenarioRule(MainActivity::class.java)

    @Before
    fun initValidString() {
        // Specify a valid string.
        stringToBeTyped = "Espresso"
    }

    @Test
    fun checkMainActivityVisibility(){
        onView(withId(R.id.mainActivityLayoutId)).check(matches(isDisplayed()))
    }

    /**
     * perform click on explore nav wishlist and verify it opens when you click
     */

    @Test
    fun click_explore_bottomNav_to_explore_fragment(){

        onView(withId(R.id.exploreFragment)).perform(click())

        onView(withId(R.id.exploreFragmentParent)).check(matches(isDisplayed()))
    }

    /**
     * perform click on bottom nav wishlist and verify it opens when you click
     */

    @Test
    fun click_wishlist_bottomNav_to_wishlist_fragment(){

        onView(withId(R.id.wishListFragment)).perform(click())

        onView(withId(R.id.wishListFragmentParent)).check(matches(isDisplayed()))
    }

    /**
     * perform click on bottom nav profile and verify it opens when you click
     */

    @Test
    fun click_profile_bottomNav_to_profile_fragment(){

        onView(withId(R.id.profileFragment)).perform(click())

        onView(withId(R.id.profileFragmentParent)).check(matches(isDisplayed()))
    }



}