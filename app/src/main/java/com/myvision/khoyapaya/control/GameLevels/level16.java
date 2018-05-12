package com.myvision.khoyapaya.control.GameLevels;

import android.app.Fragment;
import android.content.Context;
import android.content.SharedPreferences;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.myvision.khoyapaya.R;
import com.myvision.khoyapaya.control.Control;

/**
 * Created by Rahul BANSAL on 3/15/2017.
 */
public class level16 extends Fragment implements SensorEventListener {
    GestureDetector gestureDetector;
    private SensorManager sensorManager;
    public int count=0 , temp=0 , belowcount=0;
    boolean activityRunning;
    boolean step=true;
    int  temp1=0;
    ImageView trademill;
    SharedPreferences iscountsensor;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.level16, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

       // count = (TextView) findViewById(R.id.count);
        sensorManager = (SensorManager) getActivity().getSystemService(Context.SENSOR_SERVICE);
        gestureDetector = new GestureDetector(getActivity(),new GestureListener());
        trademill=(ImageView)getActivity().findViewById(R.id.trademill);

        iscountsensor = getActivity().getSharedPreferences(null, Context.MODE_PRIVATE);
            }
    public class GestureListener extends
            GestureDetector.SimpleOnGestureListener {

        @Override
        public boolean onDown(MotionEvent e) {

            return true;
        }

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            belowcount=belowcount+1;
            if(belowcount>=10)
            {activityRunning = false;
                if (Control.leveltime.getBoolean("is_first_time_level16", true)) {
                    Control.levellock.edit().putInt("lock" , 17).apply();
                    //the app is being launched for first time, do something
                    Log.d("TAG", "First time");
                    int cointemp;
                    cointemp= Control.coin.getInt("coin",0)+10;
                    Control.coin.edit().putInt("coin",cointemp).apply();
                    Control.coinv.setText(String.valueOf(Control.coin.getInt("coin",0)));
                    ((Control)getActivity()).oncrose(17);


                    // first time task

                    // record the fact that the app has been started at least once
                    Control.leveltime.edit().putBoolean("is_first_time_level16", false).apply();
                }
                else
                {
                    ((Control)getActivity()).oncrose(17);

                    Toast.makeText(getActivity(),"Coin provided 1st time only",Toast.LENGTH_SHORT).show();
                    //second time launch..
                }

                Toast.makeText(getActivity(), "taskdone now on below", Toast.LENGTH_LONG).show();
            }
            return super.onFling(e1, e2, velocityX, velocityY);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        activityRunning = true;
        Sensor countSensor = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.KITKAT) {
            countSensor = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);
           
        }
        if(countSensor != null) {iscountsensor.edit().putInt("value",1).apply();
            sensorManager.registerListener(this, countSensor, SensorManager.SENSOR_DELAY_UI);
        } else { iscountsensor.edit().putInt("value",0).apply();
            trademill.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                // TODO Auto-generated method stub
                return gestureDetector.onTouchEvent(event);
            }

        });

            Toast.makeText(getActivity(), "Count sensor not available!", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        activityRunning = false;
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if(activityRunning) {

            temp=(int)event.values[0];
             if(step)
             {  temp1=temp;
             step=false;}
            count=temp-temp1;

            Toast.makeText(getActivity(), ""+count, Toast.LENGTH_LONG).show();
            if(count>=10)
            {activityRunning = false;
                if (Control.leveltime.getBoolean("is_first_time_level16", true)) {
                    Control.levellock.edit().putInt("lock" , 17).apply();
                    //the app is being launched for first time, do something
                    Log.d("TAG", "First time");
                    int cointemp;
                    cointemp= Control.coin.getInt("coin",0)+10;
                    Control.coin.edit().putInt("coin",cointemp).apply();
                    Control.coinv.setText(String.valueOf(Control.coin.getInt("coin",0)));
                    ((Control)getActivity()).oncrose(17);


                    // first time task

                    // record the fact that the app has been started at least once
                    Control.leveltime.edit().putBoolean("is_first_time_level16", false).apply();
                }
                else
                {
                    ((Control)getActivity()).oncrose(17);

                    Toast.makeText(getActivity(),"Coin provided 1st time only",Toast.LENGTH_SHORT).show();
                    //second time launch..
                }

                Toast.makeText(getActivity(), "taskdone now", Toast.LENGTH_LONG).show();
            }
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
