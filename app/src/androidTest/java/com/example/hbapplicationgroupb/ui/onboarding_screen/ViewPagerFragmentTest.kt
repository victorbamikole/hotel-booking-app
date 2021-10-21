package com.example.hbapplicationgroupb.ui.onboarding_screen

import androidx.fragment.app.testing.FragmentScenario
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.swipeRight
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.example.hbapplicationgroupb.R
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
@LargeTest
class ViewPagerFragmentTest{
    private lateinit var scenario: FragmentScenario<ViewPagerFragment>

    @Before
    fun setUp() {
        scenario = launchFragmentInContainer(themeResId = R.style.Theme_HBApplicationGroupB)
    }
    @Test
    fun text_isDisplayed_in_view() {
        onView(withId(R.id.btn_skip)).check(matches(isDisplayed()))
        onView(withId(R.id.btn_next)).check(matches(isDisplayed()))
    }


    @Test
    fun view_isDisplayed_in_viewpager2() {
        onView(withId(R.id.onboardingViewPager)).check(matches(isDisplayed()))
        onView(withId(R.id.onboardingImage)).check(matches(isDisplayed()))
        onView(withId(R.id.onboardingTitle)).check(matches(isDisplayed()))
        onView(withId(R.id.onboardingDescription)).check(matches(isDisplayed()))
        onView(withId(R.id.onboardingViewPager)).perform(swipeRight())
    }



}