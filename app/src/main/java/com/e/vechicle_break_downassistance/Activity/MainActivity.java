package com.e.vechicle_break_downassistance.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.e.vechicle_break_downassistance.Activity.Mechanic.Mechanicdash;
import com.e.vechicle_break_downassistance.Activity.User.Dashboard;
import com.e.vechicle_break_downassistance.R;
import com.e.vechicle_break_downassistance.Sensor.Accelerometer;
import com.e.vechicle_break_downassistance.Sensor.Proximity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button signin,signup;
    SharedPreferences preferences;
    SharedPreferences.Editor editor;
    private SensorManager manager;
    private Sensor mAccelerometer;
    private  Accelerometer accelerometer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Proximity proximity=new Proximity(getApplicationContext());
        proximity.proximity();
        manager = (SensorManager) getApplicationContext().getSystemService(SENSOR_SERVICE);
        mAccelerometer=manager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        accelerometer=new Accelerometer(getApplicationContext());

        accelerometer.setOnShakeListener(new Accelerometer.OnShakeListener() {
            @Override
            public void onShake(int count) {
                   Intent intent=new Intent(MainActivity.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
       preferences=getSharedPreferences("app", Context.MODE_PRIVATE);
        editor=preferences.edit();
        if(preferences.getBoolean("status",false)){
            String usertype=preferences.getString("usertype","a");
            Intent intent;
            if (usertype.equals("User")) {
                intent = new Intent(MainActivity.this, Dashboard.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();

            }else if(usertype.equals("Mechanic")){
                intent=new Intent(MainActivity.this, Mechanicdash.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();

            }
        }

        signin=findViewById(R.id.btnsignin);
        signin.setOnClickListener(this);


        signup=findViewById(R.id.btnsignup);
        signup.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()){


            case R.id.btnsignin:
                intent=new Intent(this, Login.class);
                startActivity(intent);
            break;

            case R.id.btnsignup:
               intent=new Intent(this, Register.class);
               startActivity(intent);
                break;

        }

    }

    @Override
    public void onResume() {
        super.onResume();
        manager.registerListener(accelerometer, mAccelerometer,SensorManager.SENSOR_DELAY_UI );
    }

    @Override
    public void onStart() {
        super.onStart();
        manager.registerListener(accelerometer, mAccelerometer,SensorManager.SENSOR_DELAY_UI );
    }

    @Override
    public void onPause() {
        super.onPause();
        manager.unregisterListener(accelerometer);
    }

    @Override
    public void onStop() {
        super.onStop();
        manager.unregisterListener(accelerometer);
    }
}
