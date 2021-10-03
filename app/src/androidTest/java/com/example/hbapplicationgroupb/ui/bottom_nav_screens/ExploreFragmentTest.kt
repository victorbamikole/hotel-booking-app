package com.example.hbapplicationgroupb.ui.bottom_nav_screens


//import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.espresso.Espresso
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.hbapplicationgroupb.R
import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith




@RunWith(AndroidJUnit4::class)
class ExploreFragmentTest {


    @Test
    fun testViewsExploreScreen() {
        /**Initializing the scenario for the fragment*/
        val scenario = launchFragmentInContainer<ExploreFragment>()
        //Check if the first text is visible
        Espresso.onView(withId(R.id.firstext))
            .check(ViewAssertions.matches((ViewMatchers.isDisplayed())))
        //Check if the second text is visible
        Espresso.onView(withId(R.id.secondText))
            .check(ViewAssertions.matches((ViewMatchers.isDisplayed())))
        //Check if the third text is visible
        Espresso.onView(withId(R.id.thirdText))
            .check(ViewAssertions.matches((ViewMatchers.isDisplayed())))
        //Check if the recyclerview is visible
        Espresso.onView(withId(R.id.recyclerView))
            .check(ViewAssertions.matches((ViewMatchers.isDisplayed())))
        //Check if the recyclerview is visible
        Espresso.onView(withId(R.id.recyclerView2))
            .check(ViewAssertions.matches((ViewMatchers.isDisplayed())))

    }

}
