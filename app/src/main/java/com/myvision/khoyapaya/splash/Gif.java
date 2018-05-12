package com.myvision.khoyapaya.splash;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.ActionBarActivity;
import android.view.View;

import com.myvision.khoyapaya.GifView;
import com.myvision.khoyapaya.R;

public class Gif extends ActionBarActivity {


    GifView gifView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gif);

        gifView = (GifView) findViewById(R.id.gif_view);


        String stringInfo = "";
        stringInfo += "Duration: " + gifView.getMovieDuration() + "\n";
        stringInfo += "W x H: " + gifView.getMovieWidth() + " x " + gifView.getMovieHeight() + "\n";


        new CountDownTimer(5000,1000) {
            public void onTick(long millisUntilFinished) {

            }

            public void onFinish() {
                gifView.setVisibility(View.GONE);
            }
        }.start();
    }
}
