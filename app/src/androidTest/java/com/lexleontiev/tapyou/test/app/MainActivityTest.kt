package com.lexleontiev.tapyou.test.app


import androidx.test.ext.junit.rules.activityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.lexleontiev.tapyou.test.app.ui.MainActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    @get:Rule
    val rule = activityScenarioRule<MainActivity>()

    @Test
    fun typeANumber_resultIsDisplayed() {
//        onView(withId(R.id.edit_text_factorial)).perform(typeText("1"), closeSoftKeyboard())
//        onView(withId(R.id.button)).perform(click())
//
//        onView(withId(R.id.text_result)).check(matches(isDisplayed()))
//        onView(withId(R.id.text_result)).check(matches(withText("1")))
    }
}
