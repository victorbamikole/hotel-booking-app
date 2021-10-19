package com.example.hbapplicationgroupb.ui.userAuthenticationScreen

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.hbapplicationgroupb.MainActivity
import com.example.hbapplicationgroupb.R
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class RegistrationFragmentTest{

    @get:Rule
    val activityRule: ActivityScenarioRule<MainActivity> =
        ActivityScenarioRule(MainActivity::class.java)

    @Test//Test First Name field in Registration Screen
    fun test_isRegistrationScreenFirstNameFieldVisible(){
        onView(withId(R.id.editTextViewRegUsername))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    @Test//Test Email field in Registration Screen
    fun test_isRegistrationScreenUserEmailFieldVisible(){
        onView(withId(R.id.editTextRegUserEmail))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))

    }

    @Test//Test lastName field in Registration Screen
    fun test_isRegistrationScreenLastNameFieldVisible(){
        onView(withId(R.id.editTextUserLastName))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))

    }

    @Test//Test Gender field in Registration Screen
    fun test_isRegistrationScreenGenderFieldVisible(){
        onView(withId(R.id.editTextRegUserGender))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))

    }

    @Test//Test Radio button in Registration Screen
    fun test_isRegistrationScreenRadioButtonCheckable(){
        onView(withId(R.id.btn_radio))
            .check(ViewAssertions.matches(ViewMatchers.isChecked()))
    }

    @Test//Test Register button field in Registration Screen
    fun test_isRegisterButtonClickable(){
        onView(withId(R.id.btn_register))
            .check(ViewAssertions.matches(ViewMatchers.isClickable()))
    }

}