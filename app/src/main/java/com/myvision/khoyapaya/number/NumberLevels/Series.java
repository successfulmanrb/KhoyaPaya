package com.myvision.khoyapaya.number.NumberLevels;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.myvision.khoyapaya.R;
import com.myvision.khoyapaya.number.Number;

/**
 * Created by dell-pc on 04/21/2017.
 */

public class Series extends Fragment {
    Button rans,wans;
    TextView ques;
    public void button_click(View v)
    {
        int id=v.getId();
        switch (id)
        {
            case R.id.button10: {
                Toast.makeText(getActivity(), "right answer", Toast.LENGTH_SHORT).show();
                if (Number.leveltime.getBoolean("is_first_time_number_level_6", true)) {

                    Number.levellock.edit().putInt("number_level_Isunlock" , 7).apply();
                    //the app is being launched for first time, do something
                    Log.d("TAG", "First time");
                    int cointemp;
                    cointemp= Number.coin.getInt("coin",0)+10;
                    Number.coin.edit().putInt("coin",cointemp).apply();
                    Number.coinv.setText(String.valueOf(Number.coin.getInt("coin",0)));
                    ((Number)getActivity()).oncrose(7);





                    // first time task

                    // record the fact that the app has been started at least once
                    Number.leveltime.edit().putBoolean("is_first_time_number_level_6", false).apply();
                }
                else
                {((Number)getActivity()).oncrose(7);
                    Toast.makeText(getActivity(),"Coin provided 1st time only",Toast.LENGTH_SHORT).show();
                    //second time launch..
                }
            }
                break;
            default:
            {
                Toast.makeText(getActivity(),"wrong answer",Toast.LENGTH_SHORT).show();
                ((Number)getActivity()).onwrong(1);
                ((Number)getActivity()).onretry(6);
            }
        }
    }
    @Override

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        return inflater.inflate(R.layout.number_series, container, false);
    }
    //ans = (ImageView) findViewById(R.id.imageView3);
    //opt1 = (ImageView) findViewById(R.id.imageView4);
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }
}
