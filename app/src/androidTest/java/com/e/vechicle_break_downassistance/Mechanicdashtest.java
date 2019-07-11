package com.e.vechicle_break_downassistance;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.assertion.ViewAssertions;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;

import com.e.vechicle_break_downassistance.Activity.Mechanic.Mechanicdash;
import com.e.vechicle_break_downassistance.Activity.User.Dashboard;

import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;



@RunWith(AndroidJUnit4.class)
public class Mechanicdashtest {
    @Rule
    public ActivityTestRule<Mechanicdash> dashboardActivityTestRule=new ActivityTestRule<>(Mechanicdash.class);


    @Before
    public void setupfragment(){

        dashboardActivityTestRule.getActivity().getFragmentManager().beginTransaction();
    }
    @Test
    public void run(){

    }



}
