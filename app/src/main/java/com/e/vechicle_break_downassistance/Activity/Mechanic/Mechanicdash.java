package com.e.vechicle_break_downassistance.Activity.Mechanic;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.annotation.NonNull;
import android.view.MenuItem;
import android.widget.TextView;

import com.e.vechicle_break_downassistance.Activity.Login;
import com.e.vechicle_break_downassistance.Activity.MainActivity;
import com.e.vechicle_break_downassistance.Activity.User.Dashboard;
import com.e.vechicle_break_downassistance.Fragments.Mechanic.Completedwork;
import com.e.vechicle_break_downassistance.Fragments.Mechanic.MechDash;
import com.e.vechicle_break_downassistance.Fragments.User.profile;
import com.e.vechicle_break_downassistance.R;
import com.e.vechicle_break_downassistance.Sensor.Accelerometer;
import com.e.vechicle_break_downassistance.Sensor.Proximity;

public class Mechanicdash extends AppCompatActivity {

    SharedPreferences preferences;
    SharedPreferences.Editor editor;
    private SensorManager manager;
    private Sensor mAccelerometer;
    private Accelerometer accelerometer;


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            FragmentManager fragmentManager=getSupportFragmentManager();
            FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
            switch (item.getItemId()) {

                case R.id.navigation_home:

                    MechDash mechDash=new MechDash();
                    fragmentTransaction.replace(R.id.Mechashboardframe,mechDash);
                    fragmentTransaction.commit();
                    return true;
                case R.id.navigation_dashboard:
                    Completedwork completedwork=new Completedwork();
                    fragmentTransaction.replace(R.id.Mechashboardframe,completedwork);
                    fragmentTransaction.commit();
                    return true;
                case R.id.navigation_Logoutmc:
                    AlertDialog.Builder builder=new AlertDialog.Builder(Mechanicdash.this);
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
                    profile profiles=new profile();
                    fragmentTransaction.replace(R.id.Mechashboardframe,profiles);
                    fragmentTransaction.commit();
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mechanicdash);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        Proximity proximity=new Proximity(getApplicationContext());
        proximity.proximity();
        manager = (SensorManager) getApplicationContext().getSystemService(SENSOR_SERVICE);
        mAccelerometer=manager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        accelerometer=new Accelerometer(getApplicationContext());

        accelerometer.setOnShakeListener(new Accelerometer.OnShakeListener() {
            @Override
            public void onShake(int count) {
                Intent intent=new Intent(Mechanicdash.this,Mechanicdash.class);
                startActivity(intent);
                finish();
            }
        });
        preferences=getSharedPreferences("app", Context.MODE_PRIVATE);
        editor=preferences.edit();
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        FragmentManager fragmentManager=getSupportFragmentManager();
        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
        MechDash mechDash=new MechDash();
        fragmentTransaction.replace(R.id.Mechashboardframe,mechDash);
        fragmentTransaction.commit();
    }

}
