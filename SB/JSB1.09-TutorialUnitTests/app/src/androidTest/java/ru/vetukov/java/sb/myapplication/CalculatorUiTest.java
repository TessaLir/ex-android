package ru.vetukov.java.sb.myapplication;

import android.support.test.espresso.ViewAction;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class CalculatorUiTest {
    @Rule
    public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void checkUi() {
        onView(withId(R.id.main_et_firsr_arg))
                .check(matches(isDisplayed()));

        onView(withId(R.id.main_et_second_arg))
                .check(matches(isDisplayed()));

        onView(withText("Result: N/A"))
                .check(matches(isDisplayed()));

        onView(withText("Add"))
                .check(matches(isDisplayed()));

        onView(withText("Sub"))
                .check(matches(isDisplayed()));

        onView(withText("Multy"))
                .check(matches(isDisplayed()));

        onView(withId(R.id.main_btn_div))
                .check(matches(isDisplayed()));
    }

    @Test
    public void checkCalculation() {
        onView(withId(R.id.main_et_firsr_arg))
                .perform(typeText("1"));

        onView(withId(R.id.main_et_second_arg))
                .perform(typeText("2"));

        onView(withText("Add"))
                .perform(click());

        onView(withId(R.id.main_tv_result))
                .check(matches(withText("3")));
    }


    @Test
    public void checkMultCalculation() {
        onView(withId(R.id.main_et_firsr_arg))
                .perform(typeText("2"));

        onView(withId(R.id.main_et_second_arg))
                .perform(typeText("2"));

        onView(withText("Multy"))
                .perform(click());

        onView(withId(R.id.main_tv_result))
                .check(matches(withText("4")));
    }

    @Test
    public void checkDivCalculation() {
        onView(withId(R.id.main_et_firsr_arg))
                .perform(typeText("10"));

        onView(withId(R.id.main_et_second_arg))
                .perform(typeText("2"));

        onView(withText("Division"))
                .perform(click());

        onView(withId(R.id.main_tv_result))
                .check(matches(withText("5")));
    }
}
