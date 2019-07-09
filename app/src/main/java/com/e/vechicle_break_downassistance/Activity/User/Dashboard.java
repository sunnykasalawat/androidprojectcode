package com.e.vechicle_break_downassistance.Activity.User;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.annotation.NonNull;
import android.view.MenuItem;

import com.e.vechicle_break_downassistance.Activity.Login;
import com.e.vechicle_break_downassistance.Activity.MainActivity;
import com.e.vechicle_break_downassistance.Fragments.User.Mechanic_data;
import com.e.vechicle_break_downassistance.Fragments.User.accept_or_cancel_view;
import com.e.vechicle_break_downassistance.Fragments.User.profile;
import com.e.vechicle_break_downassistance.R;
import com.e.vechicle_break_downassistance.Sensor.Proximity;

public class Dashboard extends AppCompatActivity {
    SharedPreferences preferences;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        Proximity proximity=new Proximity(getApplicationContext());
        proximity.proximity();
        FragmentManager fragmentManager=getSupportFragmentManager();
        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
        Mechanic_data mec=new Mechanic_data();
        fragmentTransaction.replace(R.id.Userdashboardframe,mec);
        fragmentTransaction.commit();
        preferences=getSharedPreferences("app", Context.MODE_PRIVATE);
        editor=preferences.edit();



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
                    accept_or_cancel_view acv=new accept_or_cancel_view();
                    fragmentTransaction.replace(R.id.Userdashboardframe,acv);
                    fragmentTransaction.commit();

                    return true;
                case R.id.navigation_logout:
                    AlertDialog.Builder builder=new AlertDialog.Builder(Dashboard.this);
                    builder.setTitle("Log Out")
                            .setMessage("Do you really want to Log out?")
                            .setCancelable(false)
                            .setPositiveButton("yes", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    editor.remove("app");
                                    editor.putBoolean("status",false)
                                            .commit();
                                    Intent intent=new Intent(getApplicationContext(), MainActivity.class);
                                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                    startActivity(intent);
                                    finish();

                                }
                            })
                            .setNegativeButton("no", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {

                                }
                            });
                    builder.show();
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
