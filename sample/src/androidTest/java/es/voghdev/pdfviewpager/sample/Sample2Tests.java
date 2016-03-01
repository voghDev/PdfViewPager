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
import static org.hamcrest.CoreMatchers.not;

@RunWith(AndroidJUnit4.class) @LargeTest public class Sample2Tests {
    @Rule public IntentsTestRule<MainActivity> activityRule =
            new IntentsTestRule<>(MainActivity.class, true, false);

    @Test public void startsSecondSample() {
        startActivity();

        onView(withText(R.string.sample2_txt)).perform(click());
        onView(withId(R.id.et_pdfUrl)).check(matches(isDisplayed()));
    }

    @Test public void hidesDownloadButtonAfterClick() {
        startActivity();

        onView(withText(R.string.sample2_txt)).perform(click());
        onView(withId(R.id.btn_download)).perform(click());
        onView(withId(R.id.btn_download)).check(matches(not(isDisplayed())));
    }

    private MainActivity startActivity() {
        return activityRule.launchActivity(null);
    }
}