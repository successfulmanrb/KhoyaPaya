package com.myvision.khoyapaya.control.GameLevels;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.myvision.khoyapaya.R;
import com.myvision.khoyapaya.control.Control;

/**
 * Created by Rahul BANSAL on 3/10/2017.
 */
public class level10 extends Fragment {
    ImageView coinimg , beggar;
    TextView  money;
    private int xDelta , x;
    private int yDelta , y;
    private ViewGroup mainLayout;
     LinearLayout money_ll;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        return inflater.inflate(R.layout.level10, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mainLayout = (LinearLayout) getActivity().findViewById(R.id.level10main);
        money=(TextView)getActivity().findViewById(R.id.money) ;
         money_ll = (LinearLayout) getActivity().findViewById(R.id.moneyll);
        coinimg=(ImageView)getActivity().findViewById(R.id.coin);
        beggar=(ImageView)getActivity().findViewById(R.id.beggar);


       money.setOnTouchListener(onTouchListener());



    }
    private View.OnTouchListener onTouchListener() {
        return new View.OnTouchListener() {


            float dX, dY;

            @Override
            public boolean onTouch(View v, MotionEvent event) {

                switch (event.getAction()) {

                    case MotionEvent.ACTION_DOWN:

                        dX = v.getX() - event.getRawX();
                        dY = v.getY() - event.getRawY();

                        break;
                    case MotionEvent.ACTION_MOVE:

                        v.animate()
                                .x(event.getRawX() + dX)
                                .y(event.getRawY() + dY)
                                .setDuration(0)
                                .start();

                        if (isViewOverlapping(v, money_ll)) {

                            money.setEnabled(false);

                            v.animate()
                                    .x((getActivity().findViewById(R.id.moneyll).getX() + getActivity().findViewById(R.id.beggar).getX()))
                                    .y(getActivity().findViewById(R.id.moneyll).getY() + getActivity().findViewById(R.id.beggar).getY())
                                    .setDuration(100)
                                    .start();


                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {money.setVisibility(View.GONE);
                                    beggar.setImageResource(R.drawable.schoolboy);
                                    Toast.makeText(getActivity(), "Work Done!", Toast.LENGTH_SHORT).show();
                                    if (Control.leveltime.getBoolean("is_first_time_level10", true)) {
                                        //the app is being launched for first time, do something
                                        Control.levellock.edit().putInt("lock", 11).apply();
                                        Log.d("TAG", "First time");
                                        int cointemp;
                                        cointemp = Control.coin.getInt("coin", 0) + 10;
                                        Control.coin.edit().putInt("coin", cointemp).apply();
                                        Control.coinv.setText(String.valueOf(Control.coin.getInt("coin", 0)));
                                        ((Control) getActivity()).oncrose(11);

                                        //((Control)getActivity()).oncrose(8);


                                        // first time task

                                        // record the fact that the app has been started at least once
                                        Control.leveltime.edit().putBoolean("is_first_time_level10", false).apply();
                                    } else {
                                        Toast.makeText(getActivity(), "Coin provided 1st time only", Toast.LENGTH_SHORT).show();
                                        //second time launch..
                                        ((Control) getActivity()).oncrose(11);
                                    }

                                }
                            }, 1100);
                        }

                        break;
                    default:
                        return false;
                }
                return true;
            }
        };

    }
    private boolean isViewOverlapping(View firstView, View secondView) {

        int[] firstPosition = new int[2];
        int[] secondPosition = new int[2];

        firstView.getLocationOnScreen(firstPosition);
        secondView.getLocationOnScreen(secondPosition);

        Rect rectFirstView = new Rect(firstPosition[0], firstPosition[1],
                firstPosition[0] + firstView.getMeasuredWidth(), firstPosition[1] + firstView.getMeasuredHeight());
        Rect rectSecondView = new Rect(secondPosition[0], secondPosition[1],
                secondPosition[0] + secondView.getMeasuredWidth(), secondPosition[1] + secondView.getMeasuredHeight());
        return rectFirstView.intersect(rectSecondView);

    }

}
