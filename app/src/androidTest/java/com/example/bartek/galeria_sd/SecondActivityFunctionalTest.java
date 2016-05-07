package com.example.bartek.galeria_sd;


import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.example.bartek.galeria_sd.MainActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.pressBack;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;


@RunWith(AndroidJUnit4.class)
public class SecondActivityFunctionalTest {

    @Rule
    public ActivityTestRule<FirstScreen> mActivityRule = new ActivityTestRule<>(FirstScreen.class);

    @Test
    public void validateSecondActivity() {
        // check that the button is there
        onView(withId(R.id.button)).perform(click());
        onView(withId(R.id.button2))
                .check(matches(withText(("APARAT"))));
        pressBack();

    }

} 