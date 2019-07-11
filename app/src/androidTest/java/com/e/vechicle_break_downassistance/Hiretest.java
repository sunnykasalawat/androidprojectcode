package com.e.vechicle_break_downassistance;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;


import com.e.vechicle_break_downassistance.Activity.User.Dashboard;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;



@RunWith(AndroidJUnit4.class)
public class Hiretest {
    @Rule
    public ActivityTestRule<Dashboard> dashboardActivityTestRule=new ActivityTestRule<>(Dashboard.class);

    @Before
    public void setupfragment(){

        dashboardActivityTestRule.getActivity().getFragmentManager().beginTransaction();
    }
@Test
public void hiretest(){
 }



}
