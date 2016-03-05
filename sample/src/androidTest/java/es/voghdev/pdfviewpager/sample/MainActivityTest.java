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
import static android.support.test.espresso.assertion.PositionAssertions.isBelow;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class) @LargeTest public class MainActivityTest  {
    @Rule public IntentsTestRule<MainActivity> activityRule =
            new IntentsTestRule<>(MainActivity.class, true, false);

    @Test public void showsSamplesTitle() {
        startActivity();

        onView(withText(R.string.txt1)).check(matches(isDisplayed()));
    }

    @Test public void showsSamplesButton1() {
        startActivity();

        onView(withId(R.id.btnSample2)).check(matches(isDisplayed()));
    }

    @Test public void showsSamplesButton2() {
        startActivity();

        onView(withText(R.string.sample3_txt)).check(isBelow(withId(R.id.btnSample2)));
    }

    @Test public void showsSamplesButton6() {
        startActivity();

        onView(withText(R.string.sample6_txt)).check(isBelow(withId(R.id.btnSample5)));
    }

    @Test public void showsSamplesButton7() {
        startActivity();

        onView(withText(R.string.sample7_txt)).check(isBelow(withId(R.id.btnSample6)));
    }

    @Test public void showsSamplesButton8() {
        startActivity();

        onView(withText(R.string.sample8_txt)).check(isBelow(withId(R.id.btnSample7)));
    }


    @Test public void samplesButton3IsCorrectlyPositioned() {
        startActivity();

        onView(withText(R.string.sample3_txt)).check(isBelow(withId(R.id.btnSample2)));
    }

    @Test public void showsInitialPdfViewer() {
        startActivity();

        onView(withId(R.id.pdfViewPager)).check(matches(isDisplayed()));
    }

    private MainActivity startActivity() {
        return activityRule.launchActivity(null);
    }
}