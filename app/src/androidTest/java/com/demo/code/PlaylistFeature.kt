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
import com.schibsted.spain.barista.assertion.BaristaVisibilityAssertions.assertNotDisplayed
import org.hamcrest.Matchers.allOf
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class PlaylistFeature : BaseUiTest() {

    val CAFE_NAME = "Hard Rock Cafe"
    val CAFE_CATEGORY = "rock"
    val TOOLBAR_NAME = "Playlists"

    val mActivityRule = ActivityTestRule(MainActivityScreen::class.java)
    @Rule get

    @Test
    fun displayTitleTest(){
        // Check if the toolbar name has the text Playlists
        assertDisplayed(TOOLBAR_NAME)
    }

    @Test
    fun displaysListOfPlaylist(){
        // Check if the recycler view has 10 items
        assertRecyclerViewItemCount(R.id.playlist_list,10)
        //Check the name of the row in the list
        onView(allOf(withId(R.id.playlist_name),isDescendantOfA(nthChildOf(withId(R.id.playlist_list),0))))
            .check(matches(withText(CAFE_NAME)))
            .check(matches(isDisplayed()))
        //Check the category of the row in the list
        onView(allOf(withId(R.id.playlist_category),isDescendantOfA(nthChildOf(withId(R.id.playlist_list),0))))
            .check(matches(withText(CAFE_CATEGORY)))
            .check(matches(isDisplayed()))
    }

    @Test
    fun displaysListOfPlaylists(){
        // Check if the list of playlists are displayed
        assertDisplayed(R.id.playlist_list)
    }


    @Test
    fun checkIfTheLoaderHidesAfterDisplayingTheList(){
        // Wait for the loader to close
        Thread.sleep(4000)
        // Check if the loader is hidden
        assertNotDisplayed(R.id.simpleProgressBar)
    }


}