package com.myvision.khoyapaya.control.GameLevels;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
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
public class level7 extends Fragment {
ImageView coinimg;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        return inflater.inflate(R.layout.level7, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        coinimg=(ImageView)getActivity().findViewById(R.id.coin);
        coinimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Control.leveltime.getBoolean("is_first_time_level7", true)) {
                    //the app is being launched for first time, do something
                    Control.levellock.edit().putInt("lock" , 8).apply();
                    Log.d("TAG", "First time");
                    int cointemp;
                    cointemp= Control.coin.getInt("coin",0)+10;
                    Control.coin.edit().putInt("coin",cointemp).apply();
                    Control.coinv.setText(String.valueOf(Control.coin.getInt("coin",0)));
                    ((Control)getActivity()).oncrose(8);


                    // first time task

                    // record the fact that the app has been started at least once
                    Control.leveltime.edit().putBoolean("is_first_time_level7", false).apply();
                }
                else
                {   Toast.makeText(getActivity(),"Coin provided 1st time only",Toast.LENGTH_SHORT).show();
                    //second time launch..
                    ((Control)getActivity()).oncrose(8);
                }

                //hsimg.setImageResource(R.drawable.level2happy);


                Toast.makeText(getActivity(),"done",Toast.LENGTH_SHORT).show();
            }
        });

    }


}
