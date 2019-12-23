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

import androidx.test.espresso.Espresso;
import androidx.test.espresso.IdlingResource;
import androidx.test.espresso.intent.rule.IntentsTestRule;
import androidx.test.filters.LargeTest;
import androidx.test.runner.AndroidJUnit4;

import org.junit.After;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import es.voghdev.pdfviewpager.MainActivity;
import es.voghdev.pdfviewpager.R;
import es.voghdev.pdfviewpager.sample.idlingresource.WaitIdlingResource;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.swipeLeft;
import static androidx.test.espresso.action.ViewActions.swipeRight;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.fail;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class Sample2Tests extends BaseTest {
    private static final int N_PAGES = 5;

    WaitIdlingResource idlingResource;

    @Rule
    public IntentsTestRule<MainActivity> activityRule =
            new IntentsTestRule<>(MainActivity.class, true, false);

    @Test
    public void startsSecondSample() {
        startActivity();
        openActionBarMenu();

        onView(withText(R.string.menu_sample2_txt)).perform(click());
        onView(withId(R.id.et_pdfUrl)).check(matches(isDisplayed()));
    }

    @Test
    public void hidesDownloadButtonAfterClick() {
        startActivity();
        openActionBarMenu();

        onView(withText(R.string.menu_sample2_txt)).perform(click());
        onView(withId(R.id.btn_download)).perform(click());
        onView(withId(R.id.btn_download)).check(matches(not(isDisplayed())));
    }

    @Test
    public void showsPdfAfterDownload() {
        startActivity();
        openActionBarMenu();

        onView(withText(R.string.menu_sample2_txt)).perform(click());
        onView(withId(R.id.btn_download)).perform(click());
        idlingResource = new WaitIdlingResource(System.currentTimeMillis(), 1500);
        Espresso.registerIdlingResources(idlingResource);
        onView(withId(R.id.btn_download)).check(matches(isDisplayed()));
        onView(withId(R.id.pdfViewPager)).check(matches(isDisplayed()));
    }

    @Test
    public void swipesPdfToLastPageAndBackWithNoCrashesWhenDownloadIsCompleted() {
        startActivity();
        openActionBarMenu();

        onView(withText(R.string.menu_sample2_txt)).perform(click());
        onView(withId(R.id.btn_download)).perform(click());
        idlingResource = new WaitIdlingResource(System.currentTimeMillis(), 1500);
        Espresso.registerIdlingResources(idlingResource);
        try {
            swipeToLastPage();
            swipeToBeginning();
        } catch (Exception ex) {
            fail("Remote PDF crashes when paging");
        }
    }

    private void swipeToLastPage() {
        for (int i = 0; i < N_PAGES; i++) {
            onView(withId(R.id.pdfViewPager)).perform(swipeLeft());
        }
    }

    private void swipeToBeginning() {
        for (int i = 0; i < N_PAGES; i++) {
            onView(withId(R.id.pdfViewPager)).perform(swipeRight());
        }
    }

    @After
    public void tearDown() {
        List<IdlingResource> idlingResources = Espresso.getIdlingResources();
        for (IdlingResource resource : idlingResources) {
            Espresso.unregisterIdlingResources(resource);
        }
    }

    private MainActivity startActivity() {
        return activityRule.launchActivity(null);
    }
}