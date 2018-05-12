package com.myvision.khoyapaya.number.NumberLevels;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.myvision.khoyapaya.R;
import com.myvision.khoyapaya.number.Number;

/**
 * Created by dell-pc on 04/15/2017.
 */

public class invert extends Fragment {
    ImageView arrow,img1,img2,img1invert,img21;
    TextView tv1,tv2,plus,equal,ans;
    Button plus_button,minus,ok;
    public void but_cli(View v)
    {
        int id=v.getId();
        switch (id)
        {
            case R.id.minus: {
                int n=Integer.parseInt(ans.getText().toString());
                if(n==0)
                {

                }
                else{
                    ans.setText(""+(n-1));
                }
                break;
            }
            case R.id.plus: {
                int n=Integer.parseInt(ans.getText().toString());
                ans.setText(""+(n+1));
                break;
            }
            case R.id.ok: {
                if(Integer.parseInt(ans.getText().toString()) == 9) {
                    Toast.makeText(getActivity(), "right answer", Toast.LENGTH_SHORT).show();
                    if (Number.leveltime.getBoolean("is_first_time_number_level_3", true)) {

                        Number.levellock.edit().putInt("number_level_Isunlock" , 4).apply();
                        //the app is being launched for first time, do something
                        Log.d("TAG", "First time");
                        int cointemp;
                        cointemp= Number.coin.getInt("coin",0)+10;
                        Number.coin.edit().putInt("coin",cointemp).apply();
                        Number.coinv.setText(String.valueOf(Number.coin.getInt("coin",0)));
                        ((Number)getActivity()).oncrose(4);





                        // first time task

                        // record the fact that the app has been started at least once
                        Number.leveltime.edit().putBoolean("is_first_time_number_level_3", false).apply();
                    }
                    else
                    {((Number)getActivity()).oncrose(4);
                        Toast.makeText(getActivity(),"Coin provided 1st time only",Toast.LENGTH_SHORT).show();
                        //second time launch..
                    }
                }
                else {
                    Toast.makeText(getActivity(), "wrong answer", Toast.LENGTH_SHORT).show();
                    ((Number) getActivity()).onwrong(1);
                    ((Number)getActivity()).onretry(3);
                }
                break;
            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater,  ViewGroup container,  Bundle savedInstanceState) {
        return inflater.inflate(R.layout.invert, container, false);
    }


    @Override
    public void onActivityCreated( Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        img1=(ImageView)getActivity().findViewById(R.id.image1);
        img2=(ImageView)getActivity().findViewById(R.id.image2);
        arrow=(ImageView)getActivity().findViewById(R.id.arrow);
        img1invert=(ImageView)getActivity().findViewById(R.id.invertimage);
        img21=(ImageView)getActivity().findViewById(R.id.image2copy);
        tv1=(TextView)getActivity().findViewById(R.id.image1_value);
        tv2=(TextView)getActivity().findViewById(R.id.image2_value);
        plus=(TextView)getActivity().findViewById(R.id.plus_sign);
        equal=(TextView)getActivity().findViewById(R.id.question_mark);
        minus=(Button) getActivity().findViewById(R.id.minus);
        ans=(TextView)getActivity().findViewById(R.id.answer);
        plus_button=(Button) getActivity().findViewById(R.id.plus);
        ok=(Button) getActivity().findViewById(R.id.ok);

       img1invert.setVisibility(View.VISIBLE);
        img1.setVisibility(View.VISIBLE);
        img2.setVisibility(View.INVISIBLE);
        img21.setVisibility(View.INVISIBLE);
        plus.setVisibility(View.INVISIBLE);
        equal.setVisibility(View.INVISIBLE);
        plus_button.setVisibility(View.INVISIBLE);
        minus.setVisibility(View.INVISIBLE);
        ans.setVisibility(View.INVISIBLE);
        ok.setVisibility(View.INVISIBLE);
        arrow.setVisibility(View.VISIBLE);

        ans.setText("0");

        arrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                img1.setVisibility(View.INVISIBLE);
                img2.setVisibility(View.VISIBLE);
                tv1.setVisibility(View.INVISIBLE);
                tv2.setVisibility(View.INVISIBLE);
                img1invert.setVisibility(View.INVISIBLE);
                img21.setVisibility(View.VISIBLE);
                plus.setVisibility(View.VISIBLE);
                equal.setVisibility(View.VISIBLE);
                arrow.setVisibility(View.INVISIBLE);
                plus_button.setVisibility(View.VISIBLE);
                minus.setVisibility(View.VISIBLE);
                ans.setVisibility(View.VISIBLE);
                ok.setVisibility(View.VISIBLE);
                Toast.makeText(getActivity(),"arrow",Toast.LENGTH_SHORT).show();
            }
        });

    }
}
