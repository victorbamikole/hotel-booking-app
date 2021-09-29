package com.example.hbapplicationgroupb.ui.bookingDetailsScreen.bookingDetailsScreenFragment

import androidx.lifecycle.Lifecycle
import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.hbapplicationgroupb.R
import org.junit.Assert.*

import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class BookingDetailsScreenFragmentTest {

//    private lateinit var scenario: FragmentScenario<BookingDetailsScreenFragment>
//
//    @Before
//    fun setUp() {
//        scenario = launchFragmentInContainer(themeResId= R.style.Theme_AppCompat_DayNight)
//        scenario.moveToState(Lifecycle.State.STARTED)
//    }

    @Test
    fun TestBookingARoom(){
        var name = "Onum Samuel Ungbede"
        var phone  = "08091185347"
        var checkIn = ""
        var checkOut = ""
        var people = ""
        var rooms = ""

        onView(withId(R.id.bookingDetailsScreen_textView_Name)).perform(typeText(name))
        onView(withId(R.id.bookingDetailsScreen_textView_PhoneNumber)).perform(typeText(phone))
        Espresso.closeSoftKeyboard()
        onView(withId(R.id.bookingDetailsScreen_textView_Start_Date)).perform(click())
        Espresso.pressBack()
        onView(withId(R.id.bookingDetailsScreen_textView_End_Date)).perform(click())
        Espresso.pressBack()
        onView(withId(R.id.bookingDetailsScreen_textView_Age_Bracket)).perform(click())
        Espresso.pressBack()
        onView(withId(R.id.bookingDetailsScreen_textView_Room_type)).perform(click())
        Espresso.pressBack()
        onView(withId(R.id.fragment_booking_bookNow_Button)).perform(click())


//    @After
//    fun tearDown() {
//    }
}
}
