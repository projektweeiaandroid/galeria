package com.example.bartek.galeria_sd;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.example.bartek.galeria_sd.MainActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.pressBack;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.longClick;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static android.support.test.espresso.matcher.ViewMatchers.withContentDescription;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.anything;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasEntry;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;


@RunWith(AndroidJUnit4.class)
public class LongClickTest {

    @Rule
    public ActivityTestRule<FirstScreen> mActivityRule = new ActivityTestRule<>(FirstScreen.class);

    @Test
    public void validateSecondActivity() {
        // check that the button is there
        onView(withId(R.id.button)).perform(click());
        onData(anything()).inAdapterView(withId(R.id.gridView))
                .atPosition(3).perform(longClick());
        onView(withId(R.id.textView3))
                .check(matches(withText(("Usuń"))));
        pressBack();

    }

}