package com.e.vechicle_break_downassistance;

import android.content.Intent;

import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;

import com.e.vechicle_break_downassistance.Activity.Login;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.closeSoftKeyboard;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class Logintest {
    @Rule
    public ActivityTestRule<Login> loginActivityTestRule=new ActivityTestRule<>(Login.class);

    @Test
    public void TestUI() throws  Exception{
    onView(withId(R.id.textemail)).perform(typeText("turtle"));
        closeSoftKeyboard();
    onView(withId(R.id.textpassword)).perform(typeText("turtle"));
    closeSoftKeyboard();
    onView(withId(R.id.buttonlogin)).perform(click());

    }
}
