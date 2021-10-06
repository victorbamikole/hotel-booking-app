package com.example.hbapplicationgroupb.ui.paymentcheckout

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.espresso.Espresso
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.hbapplicationgroupb.R
import com.example.hbapplicationgroupb.ui.topdeals.TopDealsFragment
import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class PaymentCheckoutFragmentTest{

    @Test
    fun testViewsPaymentCheckoutScreen() {
        /**Initializing the scenario for the fragment*/
        val scenario = launchFragmentInContainer<PaymentCheckoutFragment>()

        //Check if the texts and icon are visible
        Espresso.onView(ViewMatchers.withId(R.id.amountToBePaid))
            .check(ViewAssertions.matches((ViewMatchers.isDisplayed())))
        Espresso.onView(ViewMatchers.withId(R.id.amountPrice))
            .check(ViewAssertions.matches((ViewMatchers.isDisplayed())))
        Espresso.onView(ViewMatchers.withId(R.id.paymentMethod))
            .check(ViewAssertions.matches((ViewMatchers.isDisplayed())))
        Espresso.onView(ViewMatchers.withId(R.id.googlePayIcon))
            .check(ViewAssertions.matches((ViewMatchers.isDisplayed())))
        Espresso.onView(ViewMatchers.withId(R.id.googlePay))
            .check(ViewAssertions.matches((ViewMatchers.isDisplayed())))
        Espresso.onView(ViewMatchers.withId(R.id.visaIcon))
            .check(ViewAssertions.matches((ViewMatchers.isDisplayed())))
        Espresso.onView(ViewMatchers.withId(R.id.visa))
            .check(ViewAssertions.matches((ViewMatchers.isDisplayed())))
        Espresso.onView(ViewMatchers.withId(R.id.masterCardIcon))
            .check(ViewAssertions.matches((ViewMatchers.isDisplayed())))
        Espresso.onView(ViewMatchers.withId(R.id.masterCard))
            .check(ViewAssertions.matches((ViewMatchers.isDisplayed())))
        Espresso.onView(ViewMatchers.withId(R.id.debitCardIcon))
            .check(ViewAssertions.matches((ViewMatchers.isDisplayed())))
        Espresso.onView(ViewMatchers.withId(R.id.debitCard))
            .check(ViewAssertions.matches((ViewMatchers.isDisplayed())))
        Espresso.onView(ViewMatchers.withId(R.id.paytm))
            .check(ViewAssertions.matches((ViewMatchers.isDisplayed())))
        Espresso.onView(ViewMatchers.withId(R.id.paytmIcon))
            .check(ViewAssertions.matches((ViewMatchers.isDisplayed())))


    }
}