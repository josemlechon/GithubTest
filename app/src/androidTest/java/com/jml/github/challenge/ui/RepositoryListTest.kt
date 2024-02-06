package com.jml.github.challenge.ui

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.jml.github.challenge.MainActivity
import com.jml.github.challenge.R
import com.jml.github.challenge.ui.fragments.adapter.RepoAdapter
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit

@RunWith(AndroidJUnit4ClassRunner::class)
class RepositoryListTest {

    @JvmField
    @Rule
    var activityRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun clickItemListOpensDetailView() = test {

        blockSemaphoreWait()

        waitUntilSemaphore()
        activityRule.scenario.onActivity { _ ->
            unblockSemaphoreWait()
        }

        onView(withId(R.id.recycler))
            .perform(RecyclerViewActions.actionOnItemAtPosition<RepoAdapter.ViewHolder>(0, click()))

        onView(withId(R.id.rootContent)).check(matches(isDisplayed()))
    }

    @Test
    fun clickItemListOpenDetailOpenWebView() = test {

        blockSemaphoreWait()

        waitUntilSemaphore()
        activityRule.scenario.onActivity {
            unblockSemaphoreWait()
        }

        onView(withId(R.id.recycler))
            .perform(RecyclerViewActions.actionOnItemAtPosition<RepoAdapter.ViewHolder>(0, click()))

        onView(withId(R.id.openWebViewButton)).perform(click())

        blockSemaphoreWait()
        waitUntilSemaphore()
        unblockSemaphoreWait()
        onView(withId(R.id.webView)).check(matches(isDisplayed()))
    }

    private class TestScope{

        private var onActivityFlag = CountDownLatch(1)

        fun blockSemaphoreWait() {
            onActivityFlag = CountDownLatch(1)
        }

        fun unblockSemaphoreWait() {
            onActivityFlag.countDown()
        }

        fun waitUntilSemaphore() {
            onActivityFlag.await(10, TimeUnit.SECONDS)
        }
    }

    private fun test(block: TestScope.() -> Unit) = TestScope().block()
}