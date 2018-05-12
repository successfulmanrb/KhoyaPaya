package com.myvision.khoyapaya.control.GameLevels;

import android.app.Fragment;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.myvision.khoyapaya.R;
import com.myvision.khoyapaya.control.Control;

/**
 * Created by Rahul BANSAL on 3/10/2017.
 */
public class level9 extends Fragment {
    CountDownTimer countDownTimer;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        return inflater.inflate(R.layout.level9 , container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        countDownTimer = new CountDownTimer(10000, 500) {
            // 500 means, onTick function will be called at every 500
            // milliseconds

            @Override
            public void onTick(long leftTimeInMilliseconds) {


            }

            @Override
            public void onFinish() {
                if (Control.leveltime.getBoolean("is_first_time_level9", true)) {

                    //the app is being launched for first time, do something
                    Control.levellock.edit().putInt("lock" , 10).apply();
                    Log.d("TAG", "First time");
                    int cointemp;
                    cointemp= Control.coin.getInt("coin",0)+10;
                    Control.coin.edit().putInt("coin",cointemp).apply();
                    Control.coinv.setText(String.valueOf(Control.coin.getInt("coin",0)));
                    ((Control)getActivity()).oncrose(10);

                    //Toast.makeText(getActivity(),"connected " , Toast.LENGTH_SHORT).show();


                    // first time task

                    // record the fact that the app has been started at least once
                    Control.leveltime.edit().putBoolean("is_first_time_level9", false).apply();

                }
                else
                {((Control)getActivity()).oncrose(10);
                    Toast.makeText(getActivity(),"Coin provided 1st time only",Toast.LENGTH_SHORT).show();
                    //second time launch..
                }


            }

        }.start();



    }
}
