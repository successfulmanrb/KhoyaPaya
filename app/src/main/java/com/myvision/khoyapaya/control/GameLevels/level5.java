package com.myvision.khoyapaya.control.GameLevels;

import android.app.Fragment;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.myvision.khoyapaya.R;
import com.myvision.khoyapaya.control.Control;

/**
 * Created by Rahul BANSAL on 2/19/2017.
 */
public class level5 extends Fragment implements SensorEventListener {
    private SensorManager sensorManager;
    TextView x , y , z;
    double ax,ay,az;   // these are the acceleration in x,y and z axis

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        return inflater.inflate(R.layout.level5, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        sensorManager=(SensorManager)getActivity().getSystemService(Context.SENSOR_SERVICE);
        sensorManager.registerListener(this, sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER), SensorManager.SENSOR_DELAY_NORMAL);


        x =(TextView)getActivity().findViewById(R.id.x);
        y=(TextView)getActivity().findViewById(R.id.y);
        z=(TextView)getActivity().findViewById(R.id.z);

           }

    @Override
    public void onAccuracyChanged(Sensor arg0, int arg1) {

    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType()==Sensor.TYPE_ACCELEROMETER){
            ax=event.values[0]*180/Math.PI;
            x.setText("x   "+(int)ax);
            //Toast.makeText(getActivity(),"x"+ax,Toast.LENGTH_SHORT).show();
            ay=event.values[1]*180/Math.PI;
            y.setText(" y  "+(int)ay);
            //Toast.makeText(getActivity(),"y"+ay,Toast.LENGTH_SHORT).show();
            az=event.values[2]*180/Math.PI;
            z.setText("z   "+(int)az);
          // Toast.makeText(getActivity(),"z"+az,Toast.LENGTH_SHORT).show();
        } if(ax<130  && ay<300 && az<-400){
            //Do some stuff
            sensorManager.unregisterListener(this);


            // Intent i = new Intent(getActivity(), LevelNo.class);


            if (Control.leveltime.getBoolean("is_first_time_level5", true)) {
                Control.levellock.edit().putInt("lock" , 6).apply();
                //the app is being launched for first time, do something
                Log.d("TAG", "First time");
                int cointemp;
                cointemp= Control.coin.getInt("coin",0)+10;
                Control.coin.edit().putInt("coin",cointemp).apply();
                Control.coinv.setText(String.valueOf(Control.coin.getInt("coin",0)));
                ((Control)getActivity()).oncrose(6);


                // first time task

                // record the fact that the app has been started at least once
                Control.leveltime.edit().putBoolean("is_first_time_level5", false).apply();
            }
            else
            {((Control)getActivity()).oncrose(6);
                Toast.makeText(getActivity(),"Coin provided 1st time only 5",Toast.LENGTH_SHORT).show();
                //second time launch..
            }
            //  startActivity(i);
        }

    }

}
