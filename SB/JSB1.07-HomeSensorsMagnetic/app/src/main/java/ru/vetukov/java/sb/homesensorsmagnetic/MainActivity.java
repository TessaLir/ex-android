package ru.vetukov.java.sb.homesensorsmagnetic;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    private SensorManager manager = null;
    private Sensor sensor = null;

    private CompassView compass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        compass = findViewById(R.id.compass);

        manager = (SensorManager) getSystemService(SENSOR_SERVICE);
        sensor = manager.getSensorList(Sensor.TYPE_MAGNETIC_FIELD).get(0);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(manager != null && sensor != null)
            manager.registerListener(this, sensor, 0);
    }

    @Override
    protected void onPause() {
        super.onPause();
        if(manager != null)
        {
            manager.unregisterListener(this);
        }
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        compass.magChanged(event.values);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}
