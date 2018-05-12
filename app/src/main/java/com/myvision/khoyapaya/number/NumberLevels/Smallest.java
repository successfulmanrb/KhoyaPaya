package com.myvision.khoyapaya.number.NumberLevels;

import android.app.Fragment;
import android.graphics.PorterDuff;
import android.os.Bundle;
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
 * Created by dell-pc on 04/20/2017.
 */

public class Smallest extends Fragment {
    Button b1,b2,b3,submit;
    EditText e1,e2,e3;

    public void buttonOnClick(View view)
    {
        switch (view.getId())
        {
            case R.id.button4:
                if(e1.getText().toString().equals(""))
                {
                    e1.setText(b1.getText().toString());
                    e2.requestFocus();
                }
                else
                {
                    if(e2.getText().toString().equals(""))
                    {
                        e2.setText(b1.getText().toString());
                        e3.requestFocus();

                    }
                    else
                    {
                        e3.setText(b1.getText().toString());

                        b1.setEnabled(false);
                        b2.setEnabled(false);
                        b3.setEnabled(false);

                    }
                }

                break;
            case R.id.button5:
                if(e1.getText().toString().equals(""))
                {
                    e1.setText(b2.getText().toString());
                    e2.requestFocus();
                }
                else
                {
                    if(e2.getText().toString().equals(""))
                    {
                        e2.setText(b2.getText().toString());
                        e3.requestFocus();
                    }
                    else
                    {
                        e3.setText(b2.getText().toString());
                        b1.setEnabled(false);
                        b2.setEnabled(false);
                        b3.setEnabled(false);
                    }
                }
                break;
            case R.id.button6:
                if(e1.getText().toString().equals(""))
                {
                    e1.setText(b3.getText().toString());
                    e2.requestFocus();
                }
                else
                {
                    if(e2.getText().toString().equals(""))
                    {
                        e2.setText(b3.getText().toString());
                        e3.requestFocus();
                    }
                    else
                    {
                        e3.setText(b3.getText().toString());
                        b1.setEnabled(false);
                        b2.setEnabled(false);
                        b3.setEnabled(false);

                    }
                }
                break;
        }
    }

    @Override

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        return inflater.inflate(R.layout.number_smallest, container, false);
    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        b1=(Button)getActivity().findViewById(R.id.button4);
        b2=(Button)getActivity().findViewById(R.id.button5);
        b3=(Button)getActivity().findViewById(R.id.button6);
        submit=(Button)getActivity().findViewById(R.id.button7);
        e1=(EditText)getActivity().findViewById(R.id.editText);
        e2=(EditText)getActivity().findViewById(R.id.editText2);
        e3=(EditText)getActivity().findViewById(R.id.editText3);
        e1.getBackground().setColorFilter(getResources().getColor(R.color.editcolor), PorterDuff.Mode.SRC_IN);
        e2.getBackground().setColorFilter(getResources().getColor(R.color.editcolor), PorterDuff.Mode.SRC_IN);
        e3.getBackground().setColorFilter(getResources().getColor(R.color.editcolor), PorterDuff.Mode.SRC_IN);
        e1.setText("");
        e2.setText("");
        e3.setText("");

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(e1.getText().toString().equals("3") && e2.getText().toString().equals("3") && e3.getText().toString().equals("3"))
                {
                    Toast.makeText(getActivity(),"right answer",Toast.LENGTH_SHORT).show();
                    if (Number.leveltime.getBoolean("is_first_time_number_level_4", true)) {

                        Number.levellock.edit().putInt("number_level_Isunlock" , 5).apply();
                        //the app is being launched for first time, do something
                        Log.d("TAG", "First time");
                        int cointemp;
                        cointemp= Number.coin.getInt("coin",0)+10;
                        Number.coin.edit().putInt("coin",cointemp).apply();
                        Number.coinv.setText(String.valueOf(Number.coin.getInt("coin",0)));
                        ((Number)getActivity()).oncrose(5);





                        // first time task

                        // record the fact that the app has been started at least once
                        Number.leveltime.edit().putBoolean("is_first_time_number_level_4", false).apply();
                    }
                    else
                    {((Number)getActivity()).oncrose(5);
                        Toast.makeText(getActivity(),"Coin provided 1st time only",Toast.LENGTH_SHORT).show();
                        //second time launch..
                    }
                }
                else
                {
                    Toast.makeText(getActivity(),"wrong answer",Toast.LENGTH_SHORT).show();
                    ((Number)getActivity()).onwrong(1);
                    ((Number)getActivity()).onretry(4);
                    b1.setEnabled(true);
                    b2.setEnabled(true);
                    b3.setEnabled(true);
                    e1.setText("");
                    e2.setText("");
                    e3.setText("");
                    e1.requestFocus();
                }
            }
        });

    }
    }


