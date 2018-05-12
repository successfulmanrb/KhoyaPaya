package com.myvision.khoyapaya.number.NumberLevels;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.myvision.khoyapaya.R;
import com.myvision.khoyapaya.number.Number;

/**
 * Created by Rahul BANSAL on 3/20/2017.
 */
public class press_orderly extends Fragment {
    Button no1 , no2 , no3 , no4;
    int count=0;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.number_press_orderly, container, false);
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        no1=(Button)getActivity().findViewById(R.id.bno1);
        no2=(Button)getActivity().findViewById(R.id.bno2);
        no3=(Button)getActivity().findViewById(R.id.bno3);
        no4=(Button)getActivity().findViewById(R.id.bno4);

    }
   public void  sequence(View v)
    {
       /* HashMap<String, String> hashMap = new HashMap<String, String>();
        hashMap.put("1", "1");
        hashMap.put("2", "2");
        hashMap.put("3", "3");
        hashMap.put("4", "4");*/
        switch (count) {
            case 0:
                if (v.getId() == no1.getId())
                    count++;
                else {
                    Toast.makeText(getActivity(), "wrong choice 1", Toast.LENGTH_SHORT).show();
                }
                break;

            case 1:
                if (v.getId() == no2.getId())
                    count++;
                else {
                    Toast.makeText(getActivity(), "wrong choice 2", Toast.LENGTH_SHORT).show();
                }
                break;


            case 2:
                if (v.getId() == no3.getId())
                    count++;
                else {
                    Toast.makeText(getActivity(), "wrong choice 3", Toast.LENGTH_SHORT).show();
                }
                break;
            case 3:if(v.getId()==no4.getId())
            {count++;
                Toast.makeText(getActivity(),"write answer " , Toast.LENGTH_SHORT).show();
                if (Number.leveltime.getBoolean("is_first_time_number_level_1", true)) {

                    Number.levellock.edit().putInt("number_level_Isunlock" , 2).apply();
                    //the app is being launched for first time, do something
                    Log.d("TAG", "First time");
                    int cointemp;
                    cointemp= Number.coin.getInt("coin",0)+10;
                    Number.coin.edit().putInt("coin",cointemp).apply();
                    Number.coinv.setText(String.valueOf(Number.coin.getInt("coin",0)));
                    ((Number)getActivity()).oncrose(2);





                    // first time task

                    // record the fact that the app has been started at least once
                    Number.leveltime.edit().putBoolean("is_first_time_number_level_1", false).apply();
                }
                else
                {((Number)getActivity()).oncrose(2);
                    Toast.makeText(getActivity(),"Coin provided 1st time only",Toast.LENGTH_SHORT).show();
                    //second time launch..
                }
            }
            else
            {
                Toast.makeText(getActivity(),"wrong choice 4 " , Toast.LENGTH_SHORT).show();}
                break;

        }


    }
}
