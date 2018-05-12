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
public class level15 extends Fragment implements SensorEventListener {
    //SensorManager lets you access the device's sensors
    //declare Variables
    private SensorManager sensorManager;
    ImageView bottleopen , bottleclose;
    int countshake=0;



    @Override

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        return inflater.inflate(R.layout.level15, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        //create instance of sensor manager and get system service to interact with Sensor
        sensorManager = (SensorManager) getActivity().getSystemService(Context.SENSOR_SERVICE);
        //bottleopen=(ImageView)getActivity().findViewById(R.id.bottleopen);
        bottleclose=(ImageView)getActivity().findViewById(R.id.bottleclose);

    }
    // called when sensor value have changed
    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            getAccelerometer(event);
        }}
        private void getAccelerometer(SensorEvent event) {
            float[] values = event.values;
            // Movement
            float x = values[0];
            float y = values[1];
            float z = values[2];
            //get acceleration
            float accelationSquareRoot = (x * x + y * y + z * z)
                    / (SensorManager.GRAVITY_EARTH * SensorManager.GRAVITY_EARTH);
            //get current time
            long actualTime = System.currentTimeMillis();
            if (accelationSquareRoot >= 2)
             {  countshake++;
                 if(countshake>3){countshake=0;
                 sensorManager.unregisterListener(this);
//bottleclose.setVisibility(View.GONE);
               // bottleopen.setVisibility(View.VISIBLE);
                     bottleclose.setImageResource(R.drawable.lifenew);
                if (Control.leveltime.getBoolean("is_first_time_level15", true)) {
                    Control.levellock.edit().putInt("lock" , 16).apply();
                    //the app is being launched for first time, do something
                    Log.d("TAG", "First time");
                    int cointemp;
                    cointemp= Control.coin.getInt("coin",0)+10;
                    Control.coin.edit().putInt("coin",cointemp).apply();
                    Control.coinv.setText(String.valueOf(Control.coin.getInt("coin",0)));
                    ((Control)getActivity()).oncrose(16);


                    // first time task

                    // record the fact that the app has been started at least once
                    Control.leveltime.edit().putBoolean("is_first_time_level15", false).apply();
                }
                else
                {
                    ((Control)getActivity()).oncrose(16);

                    Toast.makeText(getActivity(),"Coin provided 1st time only",Toast.LENGTH_SHORT).show();
                    //second time launch..
                }}

                //Toast.makeText(this, "Device was shaked", Toast.LENGTH_SHORT)
                      //  .show();

            }
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {

        }

        @Override
        public void onResume() {
            super.onResume();
            // register this class as a listener for the orientation and
            // accelerometer sensors
            sensorManager.registerListener(this,
                    sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
                    SensorManager.SENSOR_DELAY_NORMAL);
        }

        @Override
        public void onPause(){
            // unregister listener
            super.onPause();
            sensorManager.unregisterListener(this);
        }


    }



