package com.e.vechicle_break_downassistance.Sensor;

import android.app.Application;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

import static android.content.Context.SENSOR_SERVICE;

public class Proximity {
    Context context;

    public Proximity(Context context) {
        this.context = context;
    }

    public void proximity(){
    SensorManager manager = (SensorManager) context.getSystemService(SENSOR_SERVICE);
    Sensor sensor = manager.getDefaultSensor(Sensor.TYPE_PROXIMITY);
    SensorEventListener event = new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent sensorEvent) {
            if(sensorEvent.values[0] <= 2){
               System.exit(0);
            }
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int i) {
        }
    };
    manager.registerListener(event, sensor,SensorManager.SENSOR_DELAY_NORMAL );
}
}

