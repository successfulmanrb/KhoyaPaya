package com.myvision.khoyapaya.number.NumberLevels;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.myvision.khoyapaya.R;
import com.myvision.khoyapaya.number.Number;

/**
 * Created by dell-pc on 04/21/2017.
 */

public class NumDig extends Fragment {
    Button one, two, first, third;


    @Override

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        return inflater.inflate(R.layout.number_numdig, container, false);
    }

    //ans = (ImageView) findViewById(R.id.imageView3);
    //opt1 = (ImageView) findViewById(R.id.imageView4);
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        // handler = new Handler();
        one = (Button) getActivity().findViewById(R.id.button7);
        two = (Button) getActivity().findViewById(R.id.button8);
        first=(Button)getActivity().findViewById(R.id.button22);
        third=(Button)getActivity().findViewById(R.id.button21);

        one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "wrong answer", Toast.LENGTH_SHORT).show();
                ((Number)getActivity()).onwrong(1);
                ((Number)getActivity()).onretry(9);


            }
        });
        two.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "right answer", Toast.LENGTH_SHORT).show();
                if (Number.leveltime.getBoolean("is_first_time_number_level_9", true)) {

                    Number.levellock.edit().putInt("number_level_Isunlock" , 10).apply();
                    //the app is being launched for first time, do something
                    Log.d("TAG", "First time");
                    int cointemp;
                    cointemp= Number.coin.getInt("coin",0)+10;
                    Number.coin.edit().putInt("coin",cointemp).apply();
                    Number.coinv.setText(String.valueOf(Number.coin.getInt("coin",0)));
                    ((Number)getActivity()).oncrose(10);





                    // first time task

                    // record the fact that the app has been started at least once
                    Number.leveltime.edit().putBoolean("is_first_time_number_level_9", false).apply();
                }
                else
                {((Number)getActivity()).oncrose(10);
                    Toast.makeText(getActivity(),"Coin provided 1st time only",Toast.LENGTH_SHORT).show();
                    //second time launch..
                }
            }
        });
        first.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "wrong answer", Toast.LENGTH_SHORT).show();
                ((Number)getActivity()).onwrong(1);
                ((Number)getActivity()).onretry(9);
            }
        });
        third.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "wrong answer", Toast.LENGTH_SHORT).show();
                ((Number)getActivity()).onwrong(1);
                ((Number)getActivity()).onretry(9);
            }
        });
    }
}


