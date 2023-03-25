package com.example.guessutils

import android.content.res.Resources
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.*
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
class MainActivityTest {
    @Rule
    @JvmField
    val activityScenarioRule = ActivityScenarioRule<MainActivity>(MainActivity::class.java)
    val tag:String = MainActivityTest::class.java.simpleName

    @Test
    fun guessWrong(){
        var secretRandom:Int = 0
        lateinit var resource:Resources
        activityScenarioRule.scenario.onActivity { activity->
            secretRandom = activity.secretNumber.secretRandom
            resource = activity.resources
        }
        for(n in 1..100){
            if(n != secretRandom) {
                onView(withId(R.id.userInputEditText)).perform(clearText())
                onView(withId(R.id.userInputEditText)).perform(typeText(n.toString()))
                onView(withId(R.id.guessButton)).perform(click())
                val message = if (n < secretRandom) resource.getString(R.string.bigger)
                else resource.getString(R.string.smaller)
                onView(withText(message)).check(matches(isDisplayed()))
                onView(withText("Ok")).perform(click())
            }
        }
    }
}