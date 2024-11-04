package com.example.sensor;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    SensorManager mSensorManager;
    Sensor sensor;
    TextView textView,textView2,textView3;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.textView);
        textView2 = findViewById(R.id.textView2);
        textView3 = findViewById(R.id.textView3);

        mSensorManager =(SensorManager) getSystemService(Context.SENSOR_SERVICE);
        sensor = mSensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);

        mSensorManager.registerListener(this,sensor,SensorManager.SENSOR_DELAY_NORMAL);

    }

    public void onSensorChanged(SensorEvent event) {

        textView.setText("X: "+String.valueOf(event.values[0]));
        textView2.setText("Y: "+String.valueOf(event.values[1]));
        textView3.setText("Z: "+String.valueOf(event.values[2]));
    }

    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}