/*
 * Copyright (C) 2016 Olmo Gallegos Hernández.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package es.voghdev.pdfviewpager.sample;

import android.support.test.espresso.intent.rule.IntentsTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import es.voghdev.pdfviewpager.MainActivity;
import es.voghdev.pdfviewpager.R;
import es.voghdev.pdfviewpager.library.view.VerticalViewPager;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withClassName;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.core.StringContains.containsString;
import static org.junit.Assert.fail;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class Sample10Tests extends BaseTest {
    @Rule
    public IntentsTestRule<MainActivity> activityRule =
            new IntentsTestRule<>(MainActivity.class, true, false);

    @Test
    public void startsTenthSample() {
        startActivity();
        openActionBarMenu();

        onView(withText(R.string.menu_sample10_txt)).perform(click());
        onView(withClassName(containsString(VerticalViewPager.class.getSimpleName()))).check(matches(isDisplayed()));
    }

    @Test
    public void shouldSwipeToLastPageThenGoBackToFirstWithoutCrashing() throws Exception {
        startActivity();
        openActionBarMenu();

        onView(withText(R.string.menu_sample10_txt)).perform(click());

        try {
            swipeForwardVertically(R.id.verticalViewPager, 3);
            swipeBackwardsVertically(R.id.verticalViewPager, 3);
        } catch (Exception ex) {
            fail("Error paging");
        }
    }

    private MainActivity startActivity() {
        return activityRule.launchActivity(null);
    }
}