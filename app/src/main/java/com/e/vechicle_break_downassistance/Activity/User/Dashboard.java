package com.e.vechicle_break_downassistance.Activity.User;

import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.annotation.NonNull;
import android.view.MenuItem;

import com.e.vechicle_break_downassistance.Fragments.User.Mechanic_data;
import com.e.vechicle_break_downassistance.Fragments.User.profile;
import com.e.vechicle_break_downassistance.R;

public class Dashboard extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        FragmentManager fragmentManager=getSupportFragmentManager();
        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
        Mechanic_data mec=new Mechanic_data();
        fragmentTransaction.replace(R.id.Userdashboardframe,mec);
        fragmentTransaction.commit();



        BottomNavigationView navView = findViewById(R.id.nav_view);
            navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

    }



    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            FragmentManager fragmentManager=getSupportFragmentManager();
            FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();

            switch (item.getItemId()) {
                case R.id.navigation_home:
                    Mechanic_data mec=new Mechanic_data();
                    fragmentTransaction.replace(R.id.Userdashboardframe,mec);
                    fragmentTransaction.commit();
                    return true;
                case R.id.navigation_dashboard:
                    return true;
                case R.id.navigation_notifications:
                    return true;
                case R.id.navigation_profile:
                  profile pro=new profile();
                  fragmentTransaction.replace(R.id.Userdashboardframe,pro);
                  fragmentTransaction.commit();

                    return true;
            }
            return false;
        }
    };

}
