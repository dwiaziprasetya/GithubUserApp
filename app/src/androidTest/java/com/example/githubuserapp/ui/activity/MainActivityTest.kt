package com.example.githubuserapp.ui.activity

import android.view.KeyEvent
import android.widget.EditText
import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.pressKey
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.RootMatchers.isDialog
import androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.example.githubuserapp.R
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import kotlin.random.Random

@RunWith(AndroidJUnit4ClassRunner::class)
class MainActivityTest {
    private val sampleUsername = "james"
    private val randomPick = Random.nextInt(0, 8)

    @Before
    fun setup(){
        ActivityScenario.launch(MainActivity::class.java)
    }

    @Test
    fun `Test Add User to Favourite`(){
        // Go to fragment search
        onView(withId(R.id.navigation_search)).perform(click())

        // Click searchbar
        onView(withId(R.id.searchBar)).perform(click())

        // Show searchview and input text
        onView(withId(R.id.searchView)).check(matches(isDisplayed()))
        onView(isAssignableFrom(EditText::class.java)).perform(typeText(sampleUsername), pressKey(KeyEvent.KEYCODE_ENTER))
        Thread.sleep(2000)

        // Show recycleview, click item, and add to favourites
        onView(withId(R.id.rv_person_list)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_person_list)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
            randomPick,
            click()
        ))
        Thread.sleep(2000)
        onView(withId(R.id.favourite_menu)).perform(click())
        Thread.sleep(1000)

        // Go to fragment profile and show fragment favourites
        onView(withId(R.id.navigation_profile)).check(matches(isDisplayed()))
        onView(withId(R.id.navigation_profile)).perform(click())
        Thread.sleep(2000)
        onView(withId(R.id.favourite_menu)).perform(click())
        Thread.sleep(2000)
    }

    @Test
    fun `Test Delete User from Favourite`() {
        // Go to fragment profile and show fragment favourites
        onView(withId(R.id.navigation_profile)).perform(click())
        Thread.sleep(2000)
        onView(withId(R.id.favourite_menu)).perform(click())
        Thread.sleep(1000)

        // Show recycleview and click item
        onView(withId(R.id.rv_favourite)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
            0,
            click()
        ))
        Thread.sleep(2000)

        // Go to fragment detail and delete user from favourite
        onView(withId(R.id.favourite_menu)).perform(click())
        Thread.sleep(2000)
    }

    @Test
    fun `Test change to Dark Mode`() {
        // Go to fragment profile
        onView(withId(R.id.navigation_profile)).perform(click())
        Thread.sleep(2000)

        // Click icon setting and go to setting activity
        onView(withId(R.id.settings_menu)).perform(click())
        Thread.sleep(2000)

        // Click theme button and change theme on dialog alert item
        onView(withId(R.id.btn_theme)).perform(click())
        onView(withText("Dark")).inRoot(isDialog()).check(matches(isDisplayed()))
            .perform(click())
        Thread.sleep(1000)
    }

    @Test
    fun `Test change to Light Mode`() {
        // Go to fragment profile
        onView(withId(R.id.navigation_profile)).perform(click())
        Thread.sleep(2000)

        // Click icon setting and go to setting activity
        onView(withId(R.id.settings_menu)).perform(click())
        Thread.sleep(2000)

        // Click theme button and change theme on dialog alert item
        onView(withId(R.id.btn_theme)).perform(click())
        onView(withText("Light")).inRoot(isDialog()).check(matches(isDisplayed()))
            .perform(click())
        Thread.sleep(1000)
    }

    @Test
    fun `Test change to Follow system Mode`() {
        // Go to fragment profile
        onView(withId(R.id.navigation_profile)).perform(click())
        Thread.sleep(2000)

        // Click icon setting and go to setting activity
        onView(withId(R.id.settings_menu)).perform(click())
        Thread.sleep(2000)

        // Click theme button and change theme on dialog alert item
        onView(withId(R.id.btn_theme)).perform(click())
        onView(withText("Follow system")).inRoot(isDialog()).check(matches(isDisplayed()))
            .perform(click())
        Thread.sleep(1000)
    }
}