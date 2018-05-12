package com.myvision.khoyapaya.control.GameLevels;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.content.Context;
import android.content.SharedPreferences;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.myvision.khoyapaya.R;
import com.myvision.khoyapaya.control.Control;

/**
 * Created by Rahul BANSAL on 3/10/2017.
 */
public class level12 extends Fragment implements SensorEventListener {
    //SensorManager lets you access the device's sensors
    //declare Variables
    private SensorManager sensorManager;
    ImageView sunflowerdown , sunflowermiddle , sunflowerup , sun , cloud , cloud2 , cloud3;
    Animation fadein , fadeout;

    private int xDelta , x;
    private int yDelta , y;
    private ViewGroup mainLayout;
    SharedPreferences islightsensor;




    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        return inflater.inflate(R.layout.level12, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mainLayout = (RelativeLayout) getActivity().findViewById(R.id.level12main);
        sunflowerdown=(ImageView)getActivity().findViewById(R.id.sunflowerdown);
        sunflowermiddle=(ImageView)getActivity().findViewById(R.id.sunflowermiddle);
        sunflowerup=(ImageView)getActivity().findViewById(R.id.sunflowerup);
        sun=(ImageView)getActivity().findViewById(R.id.sun);
        cloud=(ImageView)getActivity().findViewById(R.id.cloud);
        cloud2=(ImageView)getActivity().findViewById(R.id.cloud2);
        cloud3=(ImageView)getActivity().findViewById(R.id.imageView4);

        sensorManager= (SensorManager)getActivity().getSystemService(Context.SENSOR_SERVICE);
        Sensor lightSensor= sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
        islightsensor = getActivity().getSharedPreferences(null, Context.MODE_PRIVATE);

        fadein= AnimationUtils.loadAnimation(getActivity(),R.anim.fade_in);
        fadeout=AnimationUtils.loadAnimation(getActivity(),R.anim.fade_out);


        if (lightSensor == null){
            islightsensor.edit().putInt("value",0).apply();
            sun.setVisibility(View.VISIBLE);
            cloud.setVisibility(View.VISIBLE);
            cloud2.setVisibility(View.VISIBLE);
            cloud3.setVisibility(View.VISIBLE);
            cloud.setOnTouchListener(onTouchListener());



            //Toast.makeText(LightSensor.this,"No Light Sensor Found! ",Toast.LENGTH_LONG).show();
        }else{ float max =  lightSensor.getMaximumRange();
            //Get Maximum Value From Light sensor
           // sun.setVisibility(View.VISIBLE);
           // cloud.setVisibility(View.VISIBLE);
           // cloud.setOnTouchListener(onTouchListener());
            islightsensor.edit().putInt("value",1).apply();
            sensorManager.registerListener(this,lightSensor,SensorManager.SENSOR_DELAY_NORMAL);
        }
    }
    private boolean isViewOverlapping(){
        int[] firstPosition = new int[2];
        int[] secondPosition = new int[2];

       // sun.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
        sun.getLocationOnScreen(firstPosition);
        cloud.getLocationOnScreen(secondPosition);
       if(secondPosition[1]>firstPosition[1]+150)
            return true;
        else
            return  false;
        //int r = sun.getMeasuredWidth() + firstPosition[0];
       // int l = secondPosition[0];
       // return (r >= l && (r != 0 && l != 0));
    }

    private View.OnTouchListener onTouchListener() {
        return new View.OnTouchListener() {

            @SuppressLint("ClickableViewAccessibility")
            @Override
            public boolean onTouch(View view, MotionEvent event) {

                final int x = (int) event.getRawX();
                final int y = (int) event.getRawY();

                switch (event.getAction() & MotionEvent.ACTION_MASK) {

                    case MotionEvent.ACTION_DOWN:
                        RelativeLayout.LayoutParams lParams = (RelativeLayout.LayoutParams)
                                view.getLayoutParams();

                        xDelta = x - lParams.leftMargin;
                        yDelta = y - lParams.topMargin;
                        break;

                    case MotionEvent.ACTION_UP:
                        boolean isgave=isViewOverlapping();
                        if(!isgave)
                        { if (Control.leveltime.getBoolean("is_first_time_level12", true)) {
                            //the app is being launched for first time, do something
                            Control.levellock.edit().putInt("lock", 13).apply();
                            Log.d("TAG", "First time");
                            int cointemp;
                            cointemp = Control.coin.getInt("coin", 0) + 10;
                            Control.coin.edit().putInt("coin", cointemp).apply();
                            Control.coinv.setText(String.valueOf(Control.coin.getInt("coin", 0)));
                            ((Control) getActivity()).oncrose(13);

                            //((Control)getActivity()).oncrose(8);


                            // first time task

                            // record the fact that the app has been started at least once
                            Control.leveltime.edit().putBoolean("is_first_time_level12", false).apply();
                        } else {
                            Toast.makeText(getActivity(), "Coin provided 1st time only", Toast.LENGTH_SHORT).show();
                            //second time launch..
                            ((Control) getActivity()).oncrose(13);
                        }

                        }
                        Toast.makeText(getActivity(),"thanks for new location!", Toast.LENGTH_SHORT).show();
                        break;

                    case MotionEvent.ACTION_MOVE:
                        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) view
                                .getLayoutParams();
                        layoutParams.leftMargin = x - xDelta;
                        layoutParams.topMargin = y - yDelta;
                        //  layoutParams.rightMargin = 0;
                        // layoutParams.bottomMargin = 0;
                        view.setLayoutParams(layoutParams);
                        break;

                }
                mainLayout.invalidate();
                return true;
            }
        };
    }
    @Override
    public void onResume() {
        super.onResume();
        // register this class as a listener for the Pressure Sensor
        sensorManager.registerListener(this,
                sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT),
                SensorManager.SENSOR_DELAY_NORMAL);
    }
    @Override
    public void onPause() {
        // unregister listener
        super.onPause();
        sensorManager.unregisterListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if(event.sensor.getType()==Sensor.TYPE_LIGHT){
            float currentLight = event.values[0];
            //Toast.makeText(getActivity(), " "+currentLight, Toast.LENGTH_SHORT).show();
            if(currentLight>600){
                //Toast.makeText(getActivity(), " "+currentLight, Toast.LENGTH_SHORT).show();
               // sunflowerdown.startAnimation(fadeout);
                sunflowerdown.setVisibility(View.GONE);
                sunflowermiddle.setVisibility(View.VISIBLE);
                sunflowermiddle.startAnimation(fadein);
                if(currentLight>5000)
                {              // Toast.makeText(getActivity(), " "+currentLight, Toast.LENGTH_SHORT).show();
                    //sunflowermiddle.startAnimation(fadeout);
                    sunflowermiddle.setVisibility(View.GONE);


                sunflowerup.setVisibility(View.VISIBLE);
                    sunflowerup.startAnimation(fadein);
                    sensorManager.unregisterListener(this);

                    if (Control.leveltime.getBoolean("is_first_time_level12", true)) {
                        //the app is being launched for first time, do something
                        Control.levellock.edit().putInt("lock", 13).apply();
                        Log.d("TAG", "First time");
                        int cointemp;
                        cointemp = Control.coin.getInt("coin", 0) + 10;
                        Control.coin.edit().putInt("coin", cointemp).apply();
                        Control.coinv.setText(String.valueOf(Control.coin.getInt("coin", 0)));
                        ((Control) getActivity()).oncrose(13);

                        //((Control)getActivity()).oncrose(8);


                        // first time task

                        // record the fact that the app has been started at least once
                        Control.leveltime.edit().putBoolean("is_first_time_level12", false).apply();
                    } else {
                        Toast.makeText(getActivity(), "Coin provided 1st time only", Toast.LENGTH_SHORT).show();
                        //second time launch..
                        ((Control) getActivity()).oncrose(13);
                    }}


            }

        }}



    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}
