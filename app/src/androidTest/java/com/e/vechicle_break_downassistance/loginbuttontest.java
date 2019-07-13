package com.e.vechicle_break_downassistance;

import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;

import com.e.vechicle_break_downassistance.Activity.MainActivity;
import com.e.vechicle_break_downassistance.Activity.User.Dashboard;
import com.google.android.gms.maps.model.Dash;


import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

@RunWith(AndroidJUnit4.class)
public class loginbuttontest {
    @Rule
    public ActivityTestRule<Dashboard> dashboard=new ActivityTestRule<>(Dashboard.class);

    @Before
    public void yoursetupfrag(){
        dashboard.getActivity().getSupportFragmentManager().beginTransaction();
    }
    @Test
    public void loginbuttonclicktest() throws  Exception{
        onView(withId(R.id.navigation_logout)).perform(click());

}
}
