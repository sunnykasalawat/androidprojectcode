package com.e.vechicle_break_downassistance;

import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;

import com.e.vechicle_break_downassistance.Activity.Register;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.closeSoftKeyboard;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

@RunWith(AndroidJUnit4.class)
public class RegisterTest {
    @Rule
    public ActivityTestRule<Register> loginActivityTestRule=new ActivityTestRule<>(Register.class);

    @Test
    public void TestUI() throws  Exception{

        onView(withId(R.id.fullname)).perform(typeText("Sijan"));
        closeSoftKeyboard();
        onView(withId(R.id.rbmale)).perform(click());
        onView(withId(R.id.phone)).perform(typeText("9123456789"));
        closeSoftKeyboard();
        onView(withId(R.id.email)).perform(typeText("sijan@yahoo.com"));
        closeSoftKeyboard();
        onView(withId(R.id.address)).perform(typeText("Bhaktapur"));
        closeSoftKeyboard();
        onView(withId(R.id.username)).perform(typeText("sijan"));
        closeSoftKeyboard();
        onView(withId(R.id.password)).perform(typeText("sijan"));
        closeSoftKeyboard();
        onView(withId(R.id.rdouser)).perform(click());

        onView(withId(R.id.btnlocation)).perform(scrollTo(),click());
        onView(withId(R.id.btnregister)).perform(scrollTo(),click());




    }
}
