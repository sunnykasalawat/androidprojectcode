package com.e.vechicle_break_downassistance;

import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;

import com.e.vechicle_break_downassistance.Activity.MainActivity;


import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

@RunWith(AndroidJUnit4.class)
public class loginbuttontest {
    @Rule
    public ActivityTestRule<MainActivity> mainActivityActivityTestRule=new ActivityTestRule<>(MainActivity.class);

    @Test
    public void loginbuttonclicktest() throws  Exception{
    onView(withId(R.id.btnsignin)).perform(click());
}
}
