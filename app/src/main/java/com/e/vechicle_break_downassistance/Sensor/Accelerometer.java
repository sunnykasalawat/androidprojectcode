package com.e.vechicle_break_downassistance.Sensor;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

import static android.content.Context.SENSOR_SERVICE;

public class Accelerometer {
    Context context;

    public Accelerometer(Context context) {
        this.context = context;
    }

    public Boolean accelerometer() {
        Boolean sense=false;
        final SensorManager manager = (SensorManager) context.getSystemService(SENSOR_SERVICE);
        final Sensor sensor = manager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        final SensorEventListener sensorEventListener=new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent sensorEvent) {
            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int i) {

            }

        };

        if(sensor != null){
            sense=true;
            manager.registerListener(sensorEventListener,sensor,SensorManager.SENSOR_DELAY_NORMAL);
        }
        return sense;
    }
}