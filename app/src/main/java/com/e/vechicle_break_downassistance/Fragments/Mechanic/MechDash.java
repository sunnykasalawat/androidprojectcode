package com.e.vechicle_break_downassistance.Fragments.Mechanic;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.e.vechicle_break_downassistance.R;


public class MechDash extends Fragment {


    public MechDash() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_mech_dash, container, false);
    }

}
