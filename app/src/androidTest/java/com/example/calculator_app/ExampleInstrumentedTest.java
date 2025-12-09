package com.example.calculator_app;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)
class MainActivityTest {

    @Rule
    public ActivityScenarioRule<MainActivity> activityRule =
            new ActivityScenarioRule<>(MainActivity.class);

    @Test
    public void testAdditionUI() {
        // Input 2 + 3 =
        onView(withId(R.id.button2)).perform(click());
        onView(withId(R.id.btnPlus)).perform(click());
        onView(withId(R.id.button3)).perform(click());
        onView(withId(R.id.btnEqual)).perform(click());

        // Check display shows 5
        onView(withId(R.id.tvDisplay)).check(matches(withText("5")));
    }

    @Test
    public void testClearUI() {
        onView(withId(R.id.button7)).perform(click());
        onView(withId(R.id.btnC)).perform(click());

        onView(withId(R.id.tvDisplay)).check(matches(withText("")));
        onView(withId(R.id.tvExpression)).check(matches(withText("")));
    }

    @Test
    public void testChangeSignUI() {
        onView(withId(R.id.button5)).perform(click());
        onView(withId(R.id.btnChangeSign)).perform(click());

        onView(withId(R.id.tvDisplay)).check(matches(withText("-5")));
    }

    @Test
    public void testSquareRootUI() {
        onView(withId(R.id.button9)).perform(click());
        onView(withId(R.id.btnSquareRoot)).perform(click());

        onView(withId(R.id.tvDisplay)).check(matches(withText("3")));
    }

    @Test
    public void testPercentUI() {
        onView(withId(R.id.button5)).perform(click());
        onView(withId(R.id.button0)).perform(click()); // 50
        onView(withId(R.id.btnPercent)).perform(click());

        onView(withId(R.id.tvDisplay)).check(matches(withText("0.5")));
    }
}
