package com.myvision.khoyapaya.number.NumberLevels;

import android.app.Fragment;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.myvision.khoyapaya.R;
import com.myvision.khoyapaya.number.Number;

/**
 * Created by dell-pc on 04/22/2017.
 */

public class MulAdd extends Fragment {
    EditText ans;
    Button ok;
    @Override

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        return inflater.inflate(R.layout.number_muladd, container, false);
    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ans=(EditText)getActivity().findViewById(R.id.muladd_no1);
        getActivity().findViewById(R.id.muladd_ok).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String answer=ans.getText().toString();
                if(answer.equals("123"))
                    if (Number.leveltime.getBoolean("is_first_time_number_level_11", true)) {

                        Number.levellock.edit().putInt("number_level_Isunlock" , 12).apply();
                        //the app is being launched for first time, do something
                        Log.d("TAG", "First time");
                        int cointemp;
                        cointemp= Number.coin.getInt("coin",0)+10;
                        Number.coin.edit().putInt("coin",cointemp).apply();
                        Number.coinv.setText(String.valueOf(Number.coin.getInt("coin",0)));
                        ((Number)getActivity()).oncrose(12);





                        // first time task

                        // record the fact that the app has been started at least once
                        Number.leveltime.edit().putBoolean("is_first_time_number_level_11", false).apply();
                    }
                    else
                    {((Number)getActivity()).oncrose(12);
                        Toast.makeText(getActivity(),"Coin provided 1st time only",Toast.LENGTH_SHORT).show();
                        //second time launch..
                    }
                    else {
                    Toast.makeText(getActivity(),"wrong answer",Toast.LENGTH_SHORT).show();
                    ((Number)getActivity()).onwrong(1);
                    ((Number)getActivity()).onretry(11);
                }

            }
        });
    }
    }
