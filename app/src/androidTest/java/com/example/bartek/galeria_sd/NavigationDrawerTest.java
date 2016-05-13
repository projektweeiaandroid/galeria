package com.example.bartek.galeria_sd;


import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.example.bartek.galeria_sd.MainActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.openActionBarOverflowOrOptionsMenu;
import static android.support.test.espresso.Espresso.pressBack;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.longClick;
import static android.support.test.espresso.action.ViewActions.pressImeActionButton;
import static android.support.test.espresso.action.ViewActions.pressKey;
import static android.support.test.espresso.action.ViewActions.pressMenuKey;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;




@RunWith(AndroidJUnit4.class)
public class NavigationDrawerTest {

    @Rule
    public ActivityTestRule<FirstScreen> mActivityRule = new ActivityTestRule<>(FirstScreen.class);

    @Test
    public void validateFirstActivity() {
        // check that the button is there

        //onView(withId(R.id.button)).perform(openContextualActionModeOverflowMenu());
        //onView(withId(R.id.picture)).perform(longClick());
        onView(withId(R.id.textView2))
                .check(matches(withText(("(c) Bartłomiej Gałązka"))));
        //pressBack();

    }

} 