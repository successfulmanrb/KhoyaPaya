package com.myvision.khoyapaya.splash;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.myvision.khoyapaya.Audio;
import com.myvision.khoyapaya.GifView;
import com.myvision.khoyapaya.Home;
import com.myvision.khoyapaya.HomeActivity;
import com.myvision.khoyapaya.PlayAudio;
import com.myvision.khoyapaya.R;

public class Splash extends Activity {
    TextView  presents, myision, puzzle1;
    GifView gifView;
    Handler animationCompleteCallBack;
    RelativeLayout cl;
    Typewriter typewriter;
    public void fadeinmyvision()
    {
        TextView myvision=(TextView)findViewById(R.id.myvision);
        Animation animation= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fade_in);
        myvision.startAnimation(animation);
    }
    public void fadeinpresents()
    {
        TextView presents=(TextView)findViewById(R.id.presents);
        Animation animation= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fade_in);
        presents.startAnimation(animation);
    }
    public void bounce()
    {

        TextView puzzle=(TextView)findViewById(R.id.puzzle);
        Animation animation= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.bounce);

        puzzle.startAnimation(animation);
        new CountDownTimer(2000,1000) {
            public void onTick(long millisUntilFinished) {

            }

            public void onFinish() {
                Intent home=new Intent(Splash.this, Home.class);
                startActivity(home);
                finish();
            }
        }.start();


            }
    public void moveup()
    {
        TextView myvision=(TextView)findViewById(R.id.myvision);
        Animation animation= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.moveup);
        myvision.startAnimation(animation);
    }
    public void gif()
    {
        gifView = (GifView) findViewById(R.id.gif_view);


        String stringInfo = "";
        stringInfo += "Duration: " + gifView.getMovieDuration() + "\n";
        stringInfo += "W x H: " + gifView.getMovieWidth() + " x " + gifView.getMovieHeight() + "\n";



    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        cl=(RelativeLayout) findViewById(R.id.cl);

    // Audio.start();

        presents=(TextView)findViewById(R.id.presents);
        myision=(TextView)findViewById(R.id.myvision);
        puzzle1=(TextView)findViewById(R.id.puzzle);
        presents.setVisibility(View.INVISIBLE);
//typewriter.setVisibility(View.INVISIBLE);
        myision.setVisibility(View.INVISIBLE);
        puzzle1.setVisibility(View.INVISIBLE);
        animationCompleteCallBack = new Handler(new Handler.Callback() {
            @Override
            public boolean handleMessage(Message msg) {
                Log.i("Log", "Animation Completed");
               puzzle1.setVisibility(View.VISIBLE);
               bounce();

                return false;
            }
        });

        gif();
        new CountDownTimer(2000,1000) {
            public void onTick(long millisUntilFinished) {

            }

            public void onFinish() {
                gifView.setVisibility(View.GONE);
                fadeinmyvision();
                new CountDownTimer(1000,1000) {
                    public void onTick(long millisUntilFinished) {

                    }

                    public void onFinish() {
                        moveup();
                        new CountDownTimer(1000,1000) {
                            public void onTick(long millisUntilFinished) {

                            }

                            public void onFinish() {
                                typewriter = (Typewriter)findViewById(R.id.khoyapaya);
                                typewriter.setVisibility(View.VISIBLE);
                                presents.setVisibility(View.VISIBLE);
                               // puzzle1.setVisibility(View.VISIBLE);
                                fadeinpresents();

                                typewriter.setCharacterDelay(120);
                                typewriter.setAnimationCompleteListener(animationCompleteCallBack);
                                typewriter.animateText("KhoyaPaya");

                               // setContentView(typewriter);
                                // bounce();

                            }
                        }.start();
                    }
                }.start();

            }
        }.start();




    }
}
