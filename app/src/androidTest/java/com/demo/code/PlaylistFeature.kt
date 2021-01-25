package com.demo.code

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.demo.code.activities.MainActivityScreen
import com.demo.code.utils.ViewUtilities.nthChildOf
import com.schibsted.spain.barista.assertion.BaristaRecyclerViewAssertions.assertRecyclerViewItemCount
import com.schibsted.spain.barista.assertion.BaristaVisibilityAssertions.assertDisplayed
import org.hamcrest.Matchers.allOf
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class PlaylistFeature {

    val mActivityRule = ActivityTestRule(MainActivityScreen::class.java)
    @Rule get

    @Test
    fun displayTitleTest(){
        // Check if the toolbar name has the text Playlists
        assertDisplayed("Playlists")
    }

    @Test
    fun displaysListOfPlaylist(){
        // Check if the recycler view has 10 items
        assertRecyclerViewItemCount(R.id.playlist_list,3)
        //Check the name of the row in the list
        onView(allOf(withId(R.id.playlist_name),isDescendantOfA(nthChildOf(withId(R.id.playlist_list),0))))
            .check(matches(withText("Hard Rock Cafe")))
            .check(matches(isDisplayed()))
        //Check the category of the row in the list
        onView(allOf(withId(R.id.playlist_category),isDescendantOfA(nthChildOf(withId(R.id.playlist_list),0))))
            .check(matches(withText("rock")))
            .check(matches(isDisplayed()))
    }

}