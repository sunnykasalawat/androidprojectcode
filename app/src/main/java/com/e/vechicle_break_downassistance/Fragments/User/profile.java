package com.e.vechicle_break_downassistance.Fragments.User;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.e.vechicle_break_downassistance.R;


public class profile extends Fragment {
private ImageView imageview;
private TextView Name;

    public profile() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_profile, container, false);

        imageview=view.findViewById(R.id.pivprofile);
        Name=view.findViewById(R.id.ptvname);
        return view;
    }

}
