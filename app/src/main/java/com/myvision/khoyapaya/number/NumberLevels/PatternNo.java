package com.myvision.khoyapaya.number.NumberLevels;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.myvision.khoyapaya.R;
import com.myvision.khoyapaya.number.Number;

/**
 * Created by dell-pc on 04/21/2017.
 */

public class PatternNo extends Fragment {
    EditText number;
    String ans;
    @Override

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        return inflater.inflate(R.layout.number_pattern, container, false);
    }
    //ans = (ImageView) findViewById(R.id.imageView3);
    //opt1 = (ImageView) findViewById(R.id.imageView4);
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        number=(EditText)getActivity().findViewById(R.id.editText);
        number.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ans=number.getText().toString();
                if(!"".equals(ans))
                {

                    if (ans.equals("93")) {
                        Toast.makeText(getActivity(), "right answer", Toast.LENGTH_SHORT).show();
                        if (Number.leveltime.getBoolean("is_first_time_number_level_8", true)) {

                            Number.levellock.edit().putInt("number_level_Isunlock" , 9).apply();
                            //the app is being launched for first time, do something
                            Log.d("TAG", "First time");
                            int cointemp;
                            cointemp= Number.coin.getInt("coin",0)+10;
                            Number.coin.edit().putInt("coin",cointemp).apply();
                            Number.coinv.setText(String.valueOf(Number.coin.getInt("coin",0)));
                            ((Number)getActivity()).oncrose(9);





                            // first time task

                            // record the fact that the app has been started at least once
                            Number.leveltime.edit().putBoolean("is_first_time_number_level_8", false).apply();
                        }
                        else
                        {((Number)getActivity()).oncrose(9);
                            Toast.makeText(getActivity(),"Coin provided 1st time only",Toast.LENGTH_SHORT).show();
                            //second time launch..
                        }

                    } else {
                        Toast.makeText(getActivity(), "wrong answer", Toast.LENGTH_SHORT).show();
                        number.setText("");
                        ((Number)getActivity()).onwrong(1);
                        ((Number)getActivity()).onretry(8);
                    }
                }
            }
        });


    }

}
