package com.myvision.khoyapaya;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.CountDownTimer;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.ActionMode;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.myvision.khoyapaya.control.Control;
import com.myvision.khoyapaya.control.LevelNo;
import com.myvision.khoyapaya.number.Number;
import com.myvision.khoyapaya.splash.Splash;

import java.util.Locale;

import cn.pedant.SweetAlert.SweetAlertDialog;

import static com.myvision.khoyapaya.Settings.sound_of_on;

public class Home extends Activity implements Animation.AnimationListener{
    ImageView play,does,sound,shop;
    Intent svc;
    TextView tvkhoyapaya;
    Intent intent;
    SharedPreferences coin;
    SharedPreferences leveltime;
    SharedPreferences levellock;
    SharedPreferences life;
    SharedPreferences lifecount;
    SharedPreferences no_of_time_checker_cluesol;
    Animation slideLeft, slideRight, slideTop , slideDown;
    boolean firsttimeopen=false,firsttimeactivity=false,backfromfab=false , keyofsound=false;
    public void moveleftred()
    {
        ImageView red=(ImageView) findViewById(R.id.red);
        Animation animation= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.moveleft);
        red.startAnimation(animation);
    }
    public void moveupblue()
    {
        ImageView blue=(ImageView) findViewById(R.id.blue);
        Animation animation= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.moveup_blue);
        blue.startAnimation(animation);
    }
    public void movedownyellow()
    {
        ImageView yellow=(ImageView) findViewById(R.id.yellow);
        Animation animation= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.movedown);
        yellow.startAnimation(animation);
    }
    public void moverightgreen()
    {
        ImageView green=(ImageView) findViewById(R.id.green);
        Animation animation= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.moveright);
        green.startAnimation(animation);
    }
    public void fadeinimages()
    {
        ImageView green=(ImageView) findViewById(R.id.green);
        ImageView yellow=(ImageView) findViewById(R.id.yellow);
        ImageView blue=(ImageView) findViewById(R.id.blue);
        ImageView red=(ImageView) findViewById(R.id.red);
        Animation animation= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fade_in);
        green.startAnimation(animation);
        yellow.startAnimation(animation);
        blue.startAnimation(animation);
        red.startAnimation(animation);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(!getSharedPreferences(null , MODE_PRIVATE).getString("locate","false").equals("false"))
        {
            setLocale(getSharedPreferences(null,MODE_PRIVATE).getString("locate","en"));
        }
        setContentView(R.layout.activity_home);
        slideLeft = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.slide_in_left);
        slideLeft.setAnimationListener(this);
        slideRight = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.slide_in_right);
        slideRight.setAnimationListener(this);
        slideTop = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.slide_up_in);
        slideTop.setAnimationListener(this);
        slideDown = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.slide_down_in);
        slideDown.setAnimationListener(this);
        svc=new Intent(this, PlayAudio.class);
        coin=getSharedPreferences(null,MODE_PRIVATE);
        leveltime=getSharedPreferences(null,MODE_PRIVATE);
        life=getSharedPreferences(null,MODE_PRIVATE);
        lifecount=getSharedPreferences(null,MODE_PRIVATE);
        levellock=getSharedPreferences(null,MODE_PRIVATE);
        tvkhoyapaya=(TextView)findViewById(R.id.textView47);
        tvkhoyapaya.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(/*getSharedPreferences(null , MODE_PRIVATE).getInt("levelno",0)==45 */ 1==1)
                getSharedPreferences(null , MODE_PRIVATE).edit().putBoolean("khoyapaya_touch",true).apply();
                return true;
            }
        });

firsttimeopen=getSharedPreferences(null , MODE_PRIVATE).getBoolean("firsttimecheck",true);
        firsttimeactivity=getSharedPreferences(null,MODE_PRIVATE).getBoolean("1st_timeopen_home",true);
        backfromfab=getIntent().getBooleanExtra("backfromfab",false);
        keyofsound=getSharedPreferences(null , MODE_PRIVATE).getBoolean("keyofsound",true);

        play=(ImageView)findViewById(R.id.green);
        sound=(ImageView)findViewById(R.id.yellow);
        does=(ImageView)findViewById(R.id.blue);
        shop=(ImageView)findViewById(R.id.red);
        fadeinimages();
       play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              intent=new Intent(Home.this,ChoiceActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.right_to_left,R.anim.left_to_right);
            }
        });
sound.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
       intent=new Intent(Home.this , Settings.class);
        startActivity(intent);
        overridePendingTransition(R.anim.right_to_left,R.anim.left_to_right);
    }
});
        does.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
         intent=new Intent(Home.this , Instruction.class);
                startActivity(intent);
                overridePendingTransition(R.anim.right_to_left,R.anim.left_to_right);
            }
        });

       /* moveleftred();
        moveupblue();
        movedownyellow();
        moverightgreen();

     /  Typeface tf = Typeface.createFromAsset(getAssets(),
                "fonts/DroidSansFallback.ttf");
        TextView tv = (TextView) findViewById(R.id.khoyapayatv);
        tv.setTypeface(tf);
*/
        shop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                
                /*final SweetAlertDialog sd = new SweetAlertDialog(Home.this);
                sd.setCancelable(false);
sd.setContentView(R.layout.shop);
                
                sd.setOnDismissListener(new DialogInterface.OnDismissListener() {
                    @Override
                    public void onDismiss(DialogInterface dialogInterface) {
                        sd.dismiss();
                    }
                });
                sd.setCanceledOnTouchOutside(false);
                sd.show();*/

                 Dialog dialogobj = new Dialog(Home.this);
                dialogobj.getWindow().setWindowAnimations(R.style.DialogAnimation);
                dialogobj.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialogobj.getWindow().setGravity(Gravity.CENTER_HORIZONTAL);
                dialogobj.setContentView(R.layout.shop);
                dialogobj.setCanceledOnTouchOutside(false);
                dialogobj.show();
            }
        });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
                stopService(svc);
        finishAffinity();


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        getSharedPreferences(null,MODE_PRIVATE).edit().putInt("length_of_sound",0).apply();
        getSharedPreferences(null,MODE_PRIVATE).edit().putInt("length_of_soundlevel",0).apply();
        getSharedPreferences(null,MODE_PRIVATE).edit().putBoolean("1st_timeopen_home",true).apply();
        //Toast.makeText(Home.this, "destroy", Toast.LENGTH_SHORT).show();
        stopService(svc);
    }

    @Override
    protected void onPause() {
        super.onPause();
        if(intent==null)
        {
            stopService(svc);}
      //Toast.makeText(Home.this, "pause", Toast.LENGTH_SHORT).show();

    }

    @Override
    protected void onRestart() {
        super.onRestart();
        if(getSharedPreferences(null , MODE_PRIVATE).getBoolean("keyofsound",true))
        {

            startService(svc);}
        //Toast.makeText(Home.this, "onRestart", Toast.LENGTH_SHORT).show();
    }
/*
    @Override
    protected void onStop() {
        super.onStop();
   // Toast.makeText(Home.this, "onStop", Toast.LENGTH_SHORT).show();
      //  stopService(svc);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Toast.makeText(Home.this, "onStart", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        Toast.makeText(Home.this, "onPostResume", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Toast.makeText(Home.this, "onNewIntent", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        Toast.makeText(Home.this, "onRestoreInstanceState", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        Toast.makeText(Home.this, " onPostCreate", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Toast.makeText(Home.this, "onResume", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onUserLeaveHint() {
        super.onUserLeaveHint();
        Toast.makeText(Home.this, "onUserLeaveHint", Toast.LENGTH_SHORT).show();
    }*/
 public void setLocale(String lang) {
        Locale myLocale = new Locale(lang);
        Resources res = getResources();
        DisplayMetrics dm = res.getDisplayMetrics();
        Configuration conf = res.getConfiguration();
        conf.locale = myLocale;
        res.updateConfiguration(conf, dm);
       // getSharedPreferences(null , MODE_PRIVATE).edit().putString("locate",lang).apply();
      //  Intent refresh = new Intent(this, Home.class);
       // startActivity(refresh);

       // finish();
    }


    @Override
    public void onAnimationStart(Animation animation) {

    }

    @Override
    public void onAnimationEnd(Animation animation) {

    }

    @Override
    public void onAnimationRepeat(Animation animation) {

    }
    @Override
    protected void onStart() {
        super.onStart();
        if(firsttimeopen)
        {startService(svc);
            getSharedPreferences(null , MODE_PRIVATE).edit().putBoolean("firsttimecheck",false).apply();
        }
        else{
            if(firsttimeactivity|| backfromfab){
                getSharedPreferences(null,MODE_PRIVATE).edit().putBoolean("1st_timeopen_home",false).apply();
                if(keyofsound)
                {
                    Toast.makeText(Home.this, "home if ", Toast.LENGTH_SHORT).show();
                    startService(svc);}
                else
                { Toast.makeText(Home.this, "home else", Toast.LENGTH_SHORT).show();
                    stopService(svc);

                }
            }}
        play.setVisibility(View.VISIBLE);
        play.startAnimation(slideLeft);
        sound.setVisibility(View.VISIBLE);
        sound.startAnimation(slideDown);
        shop.setVisibility(View.VISIBLE);
        shop.startAnimation(slideRight);
        does.setVisibility(View.VISIBLE);
        does.startAnimation(slideTop);
        Toast.makeText(Home.this, "onStart", Toast.LENGTH_SHORT).show();
    }
}
