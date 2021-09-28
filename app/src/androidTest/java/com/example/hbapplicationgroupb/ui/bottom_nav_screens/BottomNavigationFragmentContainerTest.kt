package com.example.hbapplicationgroupb.ui.bottom_nav_screens

import androidx.fragment.app.testing.FragmentScenario
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.lifecycle.Lifecycle
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.hbapplicationgroupb.R
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class BottomNavigationFragmentContainerTest{

    /**
     * Create a scenario to perform the test
     */
    private lateinit var scenario : FragmentScenario<BottomNavigationFragmentContainer>

    @Before
    fun setUp(){
        scenario = launchFragmentInContainer(themeResId = R.style.Theme_HBApplicationGroupB)
        scenario.moveToState(Lifecycle.State.STARTED)
    }

    @Test
    fun test_if_bottom_nav_fragment_container_is_visible(){
        onView(withId(R.id.bottomNavContainerId)).check(matches(isDisplayed()))
    }
}