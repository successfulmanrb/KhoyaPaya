package com.myvision.khoyapaya.control.GameLevels;

import android.app.Fragment;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.myvision.khoyapaya.R;
import com.myvision.khoyapaya.control.Control;

/**
 * Created by Rahul BANSAL on 2/19/2017.
 */
public class level17 extends Fragment implements SensorEventListener {
    //SensorManager lets you access the device's sensors
    //declare Variables
    private SensorManager sensorManager;
        ImageView baby;
    @Override

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        return inflater.inflate(R.layout.level17, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        baby=(ImageView)getActivity().findViewById(R.id.baby);
//create instance of sensor manager and get system service to interact with Sensor
        sensorManager= (SensorManager)getActivity().getSystemService(Context.SENSOR_SERVICE);
        Sensor proximitySensor= sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);
        if (proximitySensor == null){
            Toast.makeText(getActivity(),"No Proximity Sensor Found! ", Toast.LENGTH_LONG).show();
        }
    }



    @Override
    public void onResume() {
        super.onResume();
        // register this class as a listener for the Proximity Sensor
        sensorManager.registerListener(this,
                sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY),
                SensorManager.SENSOR_DELAY_NORMAL);
    }
    @Override
    public void onPause() {
        // unregister listener
        super.onPause();
        sensorManager.unregisterListener(this);
    }
    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
    // called when sensor value have changed
    @Override
    public void onSensorChanged(SensorEvent event) {
        // The Proximity sensor returns a single value either 0 or 5(also 1 depends on Sensor manufacturer).
        // 0 for near and 5 for far
        if(event.sensor.getType()==Sensor.TYPE_PROXIMITY){
            if(event.values[0]==0){
//baby.setVisibility(View.VISIBLE);
                baby.setImageResource(R.drawable.kiss);
                if (Control.leveltime.getBoolean("is_first_time_level17", true)) {
                    Control.levellock.edit().putInt("lock" , 18).apply();
                    //the app is being launched for first time, do something
                    Log.d("TAG", "First time");
                    int cointemp;
                    cointemp= Control.coin.getInt("coin",0)+10;
                    Control.coin.edit().putInt("coin",cointemp).apply();
                    Control.coinv.setText(String.valueOf(Control.coin.getInt("coin",0)));
                    ((Control)getActivity()).oncrose(18);


                    // first time task

                    // record the fact that the app has been started at least once
                    Control.leveltime.edit().putBoolean("is_first_time_level17", false).apply();
                }
                else
                {
                    ((Control)getActivity()).oncrose(18);

                    Toast.makeText(getActivity(),"Coin provided 1st time only",Toast.LENGTH_SHORT).show();
                    //second time launch..
                }



            }
            else{

            }

        }}
}

