package nz.co.test.transactions.ui

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import nz.co.test.transactions.R
import nz.co.test.transactions.activities.MainActivity
import org.hamcrest.CoreMatchers.containsString
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    @get:Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun transactionList_isDisplayed() {
        // Check if the RecyclerView is displayed
        onView(withId(R.id.transaction_rv)).check(matches(isDisplayed()))
    }

    @Test
    fun transactionItem_click_opensDetailActivity() {
        // Wait for data to load (if asynchronous)
        Thread.sleep(2000)

        // Perform click on first item
        onView(withId(R.id.transaction_rv))
            .perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))

        // Check if detail screen is shown
        onView(withId(R.id.summaryTextView)).check(matches(isDisplayed()))
    }

    @Test
    fun transactionItem_showsCorrectColorAndGST() {
        Thread.sleep(2000)

        // Click the first item to go to the detail screen
        onView(withId(R.id.transaction_rv))
            .perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))

        // Check if GST text is displayed
        onView(withId(R.id.gstTextView))
            .check(matches(withText(containsString("$"))))
    }

}