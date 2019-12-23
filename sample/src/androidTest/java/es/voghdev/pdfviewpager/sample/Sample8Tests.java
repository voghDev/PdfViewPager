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

import androidx.test.espresso.intent.rule.IntentsTestRule;
import androidx.test.filters.LargeTest;
import androidx.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import es.voghdev.pdfviewpager.MainActivity;
import es.voghdev.pdfviewpager.R;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.swipeLeft;
import static androidx.test.espresso.action.ViewActions.swipeRight;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.junit.Assert.fail;

@RunWith(AndroidJUnit4.class) @LargeTest
public class Sample8Tests extends BaseTest {
    @Rule public IntentsTestRule<MainActivity> activityRule =
            new IntentsTestRule<>(MainActivity.class, true, false);

    @Test public void startsEighthSample() {
        startActivity();
        openActionBarMenu();

        onView(withText(R.string.menu_sample8_txt)).perform(click());
        onView(withId(R.id.pdfViewPagerZoom)).check(matches(isDisplayed()));
    }

    @Test
    public void shouldSwipeToLastPageThenGoBackToFirstWithoutCrashing() throws Exception {
        startActivity();
        openActionBarMenu();

        onView(withText(R.string.menu_sample8_txt)).perform(click());

        try {
            swipeToEnd();
            swipeToBeginning();
        } catch (Exception ex) {
            fail("Error paging");
        }
    }

    private void swipeToEnd() {
        for (int i = 0; i < 30; i++) {
            onView(withId(R.id.pdfViewPagerZoom)).perform(swipeLeft());
        }
    }

    private void swipeToBeginning() {
        for (int i = 0; i < 30; i++) {
            onView(withId(R.id.pdfViewPagerZoom)).perform(swipeRight());
        }
    }

    private MainActivity startActivity() {
        return activityRule.launchActivity(null);
    }
}