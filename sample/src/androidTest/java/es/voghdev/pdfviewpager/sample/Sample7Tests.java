package es.voghdev.pdfviewpager.sample;

import android.support.test.espresso.intent.rule.IntentsTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import es.voghdev.pdfviewpager.MainActivity;
import es.voghdev.pdfviewpager.R;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class) @LargeTest public class Sample7Tests {
    @Rule public IntentsTestRule<MainActivity> activityRule =
            new IntentsTestRule<>(MainActivity.class, true, false);

    @Test public void startsSeventhSample() {
        startActivity();

        onView(withText(R.string.sample7_txt)).perform(click());
        onView(withId(R.id.pdfViewPagerZoom)).check(matches(isDisplayed()));
    }

    private MainActivity startActivity() {
        return activityRule.launchActivity(null);
    }
}