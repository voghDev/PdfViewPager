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
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class) @LargeTest
public class InvalidPdfTests extends BaseTest {
    @Rule
    public IntentsTestRule<MainActivity> activityRule =
            new IntentsTestRule<>(MainActivity.class, true, false);

    @Test
    public void showsAnErrorMessageWhenTryingToLoadAnInvalidPdf() {
        startActivity();
        openActionBarMenu();

        onView(withText(R.string.menu_sample10_txt)).perform(click());
        onView(withId(R.id.pdfErrorView)).check(matches(isDisplayed()));
    }

    private MainActivity startActivity() {
        return activityRule.launchActivity(null);
    }
}
