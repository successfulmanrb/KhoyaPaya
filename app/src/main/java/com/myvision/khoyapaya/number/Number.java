package com.myvision.khoyapaya.number;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.Dialog;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.cooltechworks.views.ScratchImageView;
import com.myvision.khoyapaya.AlarmReceiver;
import com.myvision.khoyapaya.ChoiceActivity;
import com.myvision.khoyapaya.Home;
import com.myvision.khoyapaya.LevelAudio;
import com.myvision.khoyapaya.PlayAudio;
import com.myvision.khoyapaya.R;
import com.myvision.khoyapaya.control.animation.FlipAnimator;
import com.myvision.khoyapaya.control.fab.FloatingActionButton;
import com.myvision.khoyapaya.control.fab.FloatingActionMenu;
import com.myvision.khoyapaya.control.fab.SubActionButton;
import com.myvision.khoyapaya.number.NumberLevels.Calender;
//import com.myvision.khoyapaya.number.NumberLevels.Operations;
import com.myvision.khoyapaya.number.NumberLevels.MulAdd;
import com.myvision.khoyapaya.number.NumberLevels.No_Sum;
import com.myvision.khoyapaya.number.NumberLevels.NumDig;
import com.myvision.khoyapaya.number.NumberLevels.Operations;
import com.myvision.khoyapaya.number.NumberLevels.PatternNo;
import com.myvision.khoyapaya.number.NumberLevels.Prime;
import com.myvision.khoyapaya.number.NumberLevels.Series;
import com.myvision.khoyapaya.number.NumberLevels.Smallest;
import com.myvision.khoyapaya.number.NumberLevels.press_orderly;
import com.myvision.khoyapaya.number.NumberLevels.invert;

import java.util.GregorianCalendar;
import java.util.Hashtable;

import cn.pedant.SweetAlert.SweetAlertDialog;

import static com.myvision.khoyapaya.control.Control.iscountsensor;
import static com.myvision.khoyapaya.control.Control.islightsensor;

public class Number extends Activity {
    ImageView life1, life2, life3;
    ImageView lcIcon1, lcIcon2, lcIcon3, lcIcon4, solutionrevealed, cluerevealed;
    Dialog diasol;
    ScratchImageView scratchImageView;
    Dialog diaclue;
    TextView revealsol, cancelsol;
    TextView revealclue, cancelclue;
    public static SharedPreferences coin;
    public static SharedPreferences leveltime;
    public static SharedPreferences levellock;
    public static SharedPreferences life;
    public static SharedPreferences lifecount;
    public static SharedPreferences no_of_time_checker_cluesol;
    FragmentManager fragmentManager1;
    FragmentTransaction fragmentTransaction1;
    public static TextView coinv;

    Hashtable<String, Integer> numbers;
    Hashtable<String, Integer> numbersclue;
    Animation pushin, pushout, fadein, fadeout;
    Intent svc;
    Intent svchome;

    private boolean blink;
    CountDownTimer countDownTimer;
    TextView time, solution, clue;
    int level = 1;

    private long totalTimeCountInMilliseconds; // total count down time in
    // milliseconds
    private long timeBlinkInMilliseconds;
    int count = 1;
    long lefttime;
    press_orderly asc;
    Calender calender;
    //Operations operations;
    invert invert;
    Smallest smallest;
    No_Sum no_sum;
    Series series;
    Prime prime;
    PatternNo pattern;
    NumDig numdig;
    Operations operations;
    MulAdd muladd;
    public Number() {
        countDownTimer = null;
        totalTimeCountInMilliseconds = 30000;
        timeBlinkInMilliseconds = 10000;
    }
    public void onwrong (int coinreduce)
    {
        int cointemp, coincheck;
        coincheck= Number.coin.getInt("coin",0);
        if(coincheck>coinreduce) {
            cointemp= Number.coin.getInt("coin",0)-coinreduce;
            coin.edit().putInt("coin", cointemp).apply();

        }
        else
            Toast.makeText(Number.this,"oops no coin",Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
        top();
        setContentView(R.layout.activity_main_number);
        getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.titlebar);
        fab();
        numbers = new Hashtable<String, Integer>();
        numbersclue = new Hashtable<String, Integer>();
        life1 = (ImageView) findViewById(R.id.life1);
        life2 = (ImageView) findViewById(R.id.life2);
        life3 = (ImageView) findViewById(R.id.life3);
//to bring out sound from 0

        getSharedPreferences(null,MODE_PRIVATE).edit().putInt("length_of_sound",0).apply();
        getSharedPreferences(null,MODE_PRIVATE).edit().putInt("length_of_soundlevel",0).apply();
        svc=new Intent(this, LevelAudio.class);
        svchome=new Intent(this, PlayAudio.class);
        stopService(svchome);
if(getSharedPreferences(null , MODE_PRIVATE).getBoolean("keyofsoundlevel",true))
{ startService(svc);}
        no_of_time_checker_cluesol = getSharedPreferences(null, MODE_PRIVATE);
        coin = getSharedPreferences(null, MODE_PRIVATE);
        leveltime = getSharedPreferences(null, MODE_PRIVATE);
        life = getSharedPreferences(null, MODE_PRIVATE);
        lifecount = getSharedPreferences(null, MODE_PRIVATE);
        levellock = getSharedPreferences(null, MODE_PRIVATE);
        coinv = (TextView) findViewById(R.id.textView2);
        time = (TextView) findViewById(R.id.tvtime);
        pushin = AnimationUtils.loadAnimation(this, R.anim.push_up_in);
        fadein = AnimationUtils.loadAnimation(this, R.anim.fade_in);
        fadeout = AnimationUtils.loadAnimation(this, R.anim.fade_out);
        pushout = AnimationUtils.loadAnimation(this, R.anim.push_up_out);
        time.setTextAppearance(getApplicationContext(), R.style.normalText);
      //  starttimer();
        fragmentManager1 = getFragmentManager();

        fragmentTransaction1 = fragmentManager1.beginTransaction();
        asc = new press_orderly();
        calender=new Calender();
        //operations=new Operations();
        invert=new invert();
        smallest=new Smallest();
        no_sum=new No_Sum();
        series=new Series();
        prime=new Prime();
        pattern=new PatternNo();
        numdig=new NumDig();
        operations=new Operations();
        muladd=new MulAdd();
        if(life.getBoolean("is_first_time_run_number",true))
        {  coin.edit().putInt("coin", 10).apply();
        life.edit().putBoolean("is_first_time_run_number",false).apply();}
        coinv.setText(String.valueOf(coin.getInt("coin", 0)));

      /*  if(coin.getBoolean("coinvaluenew",true))
        {  coinv.setText(String.valueOf(coin.getInt("coin", 10)));
            coin.edit().putBoolean("coinvaluenew",false).apply();
        }*/

        if(getIntent().getIntExtra("number_level",0)!=0)
            level=getIntent().getIntExtra("number_level",0);


        lcIcon1.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                Intent intent=new Intent(Number.this, Home.class);
                intent.putExtra("backfromfab",true);
                startActivity(intent);
                return false;
            }
        });
        lcIcon2.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                cancelTimer();
                solutiondialog();
                //scratchImageView = (ScratchImageView) diasol.findViewById(R.id.scratchView1);
                //diasol.show();
                revealsol.setOnClickListener(revealsolbutton());
                   /* if(scratchImageView!=null)
                {  scratchImageView.setRevealListener(new ScratchImageView.IRevealListener() {
                        @Override
                        public void onRevealed(ScratchImageView scratchImageView) {
                            Toast.makeText(Control.this , "revealed" , Toast.LENGTH_SHORT).show();
                            //Don't Write Any Code Here
                        }
                    });}
                    assert scratchImageView != null;
                    if(scratchImageView.isRevealed())
                    {

                    }



                   scratchImageView.setRevealListener(new ScratchImageView.IRevealListener() {
                        @Override
                        public void onRevealed(ScratchImageView scratchImageView) {
                            Toast.makeText(Control.this , "revealed" , Toast.LENGTH_SHORT).show();
                            //Don't Write Any Code Here
                        }
                    });
*/





                return false;
            }
        });
        lcIcon3.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                long times=lefttime;
                cancelTimer();
                cluedialog();
                revealclue.setOnClickListener(revealcluebutton());

                return false;
            }
        });
        lcIcon4.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Intent intent=new Intent(Number.this,ChoiceActivity.class);
                intent.putExtra("backfromfab",true);
                startActivity(intent);
                return false;

            }
        });

        if(getSharedPreferences(null , MODE_PRIVATE).getInt("lifecount",0)==1)
        {life1.setVisibility(View.VISIBLE);
            life2.setVisibility(View.VISIBLE);
            life3.setVisibility(View.VISIBLE);}
        if(getSharedPreferences(null , MODE_PRIVATE).getInt("lifecount",0)==2)
        {life1.setVisibility(View.INVISIBLE);}

        if(getSharedPreferences(null , MODE_PRIVATE).getInt("lifecount",0)==3)
        {life1.setVisibility(View.INVISIBLE);
            life2.setVisibility(View.INVISIBLE);}



        switch (level) {
            case 1:fragmentTransaction1.replace(R.id.numbermainlayout, asc).commit();

                starttimer();
                break;
            case 2:fragmentTransaction1.replace(R.id.numbermainlayout, calender).commit();
                starttimer();
                break;
            case 3:fragmentTransaction1.replace(R.id.numbermainlayout, invert).commit();

                starttimer();
                break;
            case 4:fragmentTransaction1.replace(R.id.numbermainlayout, smallest).commit();

                starttimer();
                break;
            case 5:fragmentTransaction1.replace(R.id.numbermainlayout, no_sum).commit();

                starttimer();
                break;
            case 6:fragmentTransaction1.replace(R.id.numbermainlayout, series).commit();

                starttimer();
                break;
            case 7:fragmentTransaction1.replace(R.id.numbermainlayout, prime).commit();

                starttimer();
                break;
            case 8:fragmentTransaction1.replace(R.id.numbermainlayout, pattern).commit();

                starttimer();
                break;
            case 9:fragmentTransaction1.replace(R.id.numbermainlayout, numdig).commit();

                starttimer();
                break;
            case 10:fragmentTransaction1.replace(R.id.numbermainlayout, operations).commit();

                starttimer();
                break;
            case 11:fragmentTransaction1.replace(R.id.numbermainlayout, muladd).commit();

                starttimer();
                break;
        }
            if(getSharedPreferences(null , MODE_PRIVATE).getInt("lifecount",0)==1)
        {life1.setVisibility(View.VISIBLE);
            life2.setVisibility(View.VISIBLE);
            life3.setVisibility(View.VISIBLE);}
        if(getSharedPreferences(null , MODE_PRIVATE).getInt("lifecount",0)==2)
        {life1.setVisibility(View.INVISIBLE);}

        if(getSharedPreferences(null , MODE_PRIVATE).getInt("lifecount",0)==3)
        {life1.setVisibility(View.INVISIBLE);
            life2.setVisibility(View.INVISIBLE);}
    }


    private void top() {//- ActionBar actionBar = getSupportActionBar();
        //customTitleSupported = requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
        // Making notification bar transparent
        if (Build.VERSION.SDK_INT >= 21) {

            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);

            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                    WindowManager.LayoutParams.FLAG_FULLSCREEN);

        }
        if (Build.VERSION.SDK_INT < 21) {
            //  customTitleSupported = requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                    WindowManager.LayoutParams.FLAG_FULLSCREEN);
        }
    }

    //used to add fab button on right buttom
    private void fab() {

        int redActionButtonSize = getResources().getDimensionPixelSize(R.dimen.red_action_button_size);
        int redActionButtonMargin = getResources().getDimensionPixelOffset(R.dimen.action_button_margin);
        int redActionButtonContentSize = getResources().getDimensionPixelSize(R.dimen.red_action_button_content_size);
        int redActionButtonContentMargin = getResources().getDimensionPixelSize(R.dimen.red_action_button_content_margin);
        int redActionMenuRadius = getResources().getDimensionPixelSize(R.dimen.red_action_menu_radius);
        int blueSubActionButtonSize = getResources().getDimensionPixelSize(R.dimen.blue_sub_action_button_size);
        int blueSubActionButtonContentMargin = getResources().getDimensionPixelSize(R.dimen.blue_sub_action_button_content_margin);

        ImageView fabIconStar = new ImageView(this);
        fabIconStar.setImageDrawable(getResources().getDrawable(R.drawable.fabtreasureicon));

        FloatingActionButton.LayoutParams starParams = new FloatingActionButton.LayoutParams(redActionButtonSize, redActionButtonSize);
        starParams.setMargins(redActionButtonMargin,
                redActionButtonMargin,
                redActionButtonMargin,
                redActionButtonMargin+100);
        fabIconStar.setLayoutParams(starParams);

        FloatingActionButton.LayoutParams fabIconStarParams = new FloatingActionButton.LayoutParams(redActionButtonContentSize, redActionButtonContentSize);
        fabIconStarParams.setMargins(redActionButtonContentMargin,
                redActionButtonContentMargin,
                redActionButtonContentMargin,
                redActionButtonContentMargin);

        final FloatingActionButton leftCenterButton = new FloatingActionButton.Builder(this)
                .setContentView(fabIconStar, fabIconStarParams)
                .setBackgroundDrawable(R.drawable.button_action_red_selector)
                .setPosition(FloatingActionButton.POSITION_BOTTOM_RIGHT)
                .setLayoutParams(starParams)
                .build();

        // Set up customized SubActionButtons for the right center menu
        SubActionButton.Builder lCSubBuilder = new SubActionButton.Builder(this);
        lCSubBuilder.setBackgroundDrawable(getResources().getDrawable(R.drawable.button_action_blue_selector));

        FrameLayout.LayoutParams blueContentParams = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT);
        blueContentParams.setMargins(blueSubActionButtonContentMargin,
                blueSubActionButtonContentMargin,
                blueSubActionButtonContentMargin,
                blueSubActionButtonContentMargin);
        lCSubBuilder.setLayoutParams(blueContentParams);
        // Set custom layout params
        FrameLayout.LayoutParams blueParams = new FrameLayout.LayoutParams(blueSubActionButtonSize, blueSubActionButtonSize);
        lCSubBuilder.setLayoutParams(blueParams);

        lcIcon1 = new ImageView(this);
        lcIcon2 = new ImageView(this);
        lcIcon3 = new ImageView(this);
        lcIcon4 = new ImageView(this);


        lcIcon1.setImageDrawable(getResources().getDrawable(R.drawable.home));
        lcIcon2.setImageDrawable(getResources().getDrawable(R.drawable.solutionfab));
        lcIcon3.setImageDrawable(getResources().getDrawable(R.drawable.cluefab));
        lcIcon4.setImageDrawable(getResources().getDrawable(R.drawable.exitfab));


        // Build another menu with custom options
        final FloatingActionMenu leftCenterMenu = new FloatingActionMenu.Builder(this)
                .addSubActionView(lCSubBuilder.setContentView(lcIcon1, blueContentParams).build())
                .addSubActionView(lCSubBuilder.setContentView(lcIcon2, blueContentParams).build())
                .addSubActionView(lCSubBuilder.setContentView(lcIcon3, blueContentParams).build())
                .addSubActionView(lCSubBuilder.setContentView(lcIcon4, blueContentParams).build())
                .setRadius(redActionMenuRadius)
                .setStartAngle(280)
                .setEndAngle(170)
                .attachTo(leftCenterButton)
                .build();


    }

    private void starttimer() {
        time.setVisibility(View.VISIBLE);
        time.setTextAppearance(getApplicationContext(),
                R.style.normalText);
        countDownTimer = new CountDownTimer(totalTimeCountInMilliseconds, 500) {
            // 500 means, onTick function will be called at every 500
            // milliseconds

            @Override
            public void onTick(long leftTimeInMilliseconds) {
                long seconds = leftTimeInMilliseconds / 1000;
                lefttime = leftTimeInMilliseconds;

                if (leftTimeInMilliseconds < timeBlinkInMilliseconds) {
                    time.setTextAppearance(getApplicationContext(),
                            R.style.blinkText);
                    // change the style of the textview .. giving a red
                    // alert style

                    if (blink) {
                        time.setVisibility(View.VISIBLE);
                        // if blink is true, textview will be visible
                    } else {
                        time.setVisibility(View.INVISIBLE);
                    }

                    blink = !blink; // toggle the value of blink
                }

                time.setText(String.format("%02d", seconds % 60));
                // format the textview to show the easily readable format

            }

            @Override
            public void onFinish() {
                settimer(30000);
                Toast.makeText(Number.this, String.valueOf(count), Toast.LENGTH_SHORT).show();

                if (count <= 2) {
                    if (count == 1) {
                       // lifecount.edit().putInt("lifecount", 1).apply();
                        life1.startAnimation(pushin);
                        life1.startAnimation(pushout);
                        life1.setVisibility(View.INVISIBLE);

                        starttimer();
                       /* if (lifecount.getBoolean("count1", true)) {
                            count++;
                            lifecount.edit().putBoolean("count1", false).commit();
                        }*/


                    }
                    if (count == 2) {
                      //  lifecount.edit().putInt("lifecount", 2).apply();
                        life2.startAnimation(pushin);
                        life2.startAnimation(pushout);
                        life2.setVisibility(View.INVISIBLE);
                       /* if (lifecount.getBoolean("count2", true)) {
                            count++;
                            lifecount.edit().putBoolean("count2", false).commit();
                        }*/
                        starttimer();
                    }

                }
                if (count == 3) {
                    life3.startAnimation(pushin);
                    life3.startAnimation(pushout);
                    life3.setVisibility(View.INVISIBLE);


                    //life.edit().putBoolean("life", false).commit();
                    scheduleAlarm();

                    // Toast.makeText(Control.this,"oops all life finished" , Toast.LENGTH_SHORT).show();

                }
                // this function will be called when the timecount is finished
                count = count + 1;

            }

        }.start();


    }

    public void scheduleAlarm() {getSharedPreferences(null , MODE_PRIVATE).edit().putInt("lifecount", 1).apply();
        Long time = new GregorianCalendar().getTimeInMillis() + 10 * 1000;
        Intent intentAlarm = new Intent(this, AlarmReceiver.class);
        life.edit().putBoolean("life", false).apply();

        // lifecount.edit().putBoolean("count1", true).commit();
        //lifecount.edit().putBoolean("count2", true).commit();

        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        alarmManager.set(AlarmManager.RTC_WAKEUP, time, PendingIntent.getBroadcast(this, 1, intentAlarm, PendingIntent.FLAG_UPDATE_CURRENT));
        Toast.makeText(this, " Scheduled for 1 mins ", Toast.LENGTH_SHORT).show();
        SweetAlertDialog sd = new SweetAlertDialog(Number.this);
        sd.setCancelable(false);

        sd.setContentText("Ooopss No More Life ! Wait For 30mins ");
        sd.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialogInterface) {
                Intent intent = new Intent(Number.this, ChoiceActivity.class);
                startActivity(intent);
            }
        });
        sd.setCanceledOnTouchOutside(false);
        sd.show();

    }


    void cancelTimer() {
        if (countDownTimer != null)
            countDownTimer.cancel();
    }

    public void settimer(long newtime) {


        totalTimeCountInMilliseconds = newtime;
    }

    @Override
    protected void onRestart() {
        super.onRestart();
//for
        settimer(lefttime);
        if(getSharedPreferences(null , MODE_PRIVATE).getBoolean("keyofsoundlevel",true))
        {startService(svc);}
        starttimer();
        Toast.makeText(this, "on restart", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onPause() {
        super.onPause();
        getSharedPreferences(null,MODE_PRIVATE).edit().putInt("length_of_sound",0).apply();
        if(getSharedPreferences(null , MODE_PRIVATE).getBoolean("keyofsoundlevel",true))
        { stopService(svc);}
        cancelTimer();
    }

    public void sequence(View v) {
        asc.sequence(v);
    }
    public void  button_press(View v){calender.button_press(v);}
    public void but_cli(View v){invert.but_cli(v);}
    public void buttonOnClick(View v){smallest.buttonOnClick(v);}
    public void button_cli(View v){no_sum.button_cli(v);}
    public void button_click(View v){series.button_click(v);}
    public void textcli(View v){prime.textcli(v);}
   // public void set_text(View v){operations.set_text();}
    public void st_cli(View v){operations.st_cli(v);}

    private void cluedialog() {
        diaclue = new Dialog(Number.this);
        diaclue.getWindow().setWindowAnimations(R.style.DialogAnimation);
        diaclue.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        diaclue.getWindow().setGravity(Gravity.CENTER_HORIZONTAL);

        diaclue.setContentView(R.layout.clue);
        diaclue.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialogInterface) {
                settimer(lefttime);
                starttimer();

            }
        });
        revealclue = (TextView) diaclue.findViewById(R.id.tvclue);
        cancelclue = (TextView) diaclue.findViewById(R.id.tvcancelclue);
        cancelclue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                diaclue.dismiss();
            }
        });
        scratchImageView = (ScratchImageView) diaclue.findViewById(R.id.scratchView1clue);
        cluerevealed = (ImageView) diaclue.findViewById(R.id.clueflipout);
        clue = (TextView) diaclue.findViewById(R.id.tvclueid);
        cluerevealed.setVisibility(View.GONE);
        clue.setVisibility(View.GONE);

        diaclue.show();

    }

    private View.OnClickListener revealcluebutton() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Integer n = numbers.get("numberclue" + String.valueOf(level));
                Integer sp = no_of_time_checker_cluesol.getInt("clueno" + String.valueOf(level), 0);
                //Toast.makeText(Control.this,""+n,Toast.LENGTH_SHORT).show();
                if (n == null) {
                    n = 0;
                }


                if (coin.getInt("coin", 0) > 2 || sp == level) {
                    if (sp != level) {
                        int cointemp;
                        cointemp = coin.getInt("coin", 0) - 2;
                        coin.edit().putInt("coin", cointemp).apply();
                        coinv.setText(String.valueOf(cointemp));

                        numbers.put("numberclue" + String.valueOf(level), level);
                        no_of_time_checker_cluesol.edit().putInt("clueno" + String.valueOf(level), level).apply();

                    }
                    revealclue.setVisibility(View.GONE);


                    showflipanimationclue();
                    cluerevealed.setVisibility(View.VISIBLE);
                    cluerevealed.startAnimation(fadein);
                    cancelclue.setPadding(300, 5, 0, 0);
                    if (sp == level) {
                        scratchImageView.setVisibility(View.INVISIBLE);
                    }
                    clue.setVisibility(View.VISIBLE);
                    clue.setTextSize(25);
                    switch (level) {
                        case 1:
                            clue.setText(getResources().getText(R.string.ascclue));
                            break;

                        case 2:
                            clue.setText(getResources().getText(R.string.calclue));
                            break;

                        case 3:
                            clue.setText(getResources().getText(R.string.invertclue));
                            break;
                        case 4:
                            clue.setText(getResources().getText(R.string.smallestclue));
                            break;

                        case 5:
                            clue.setText(getResources().getText(R.string.nosumclue));
                            break;

                        case 6:
                            clue.setText(getResources().getText(R.string.seriesclue));
                            break;

                        case 7:
                            clue.setText(getResources().getText(R.string.primeclue));
                            break;

                        case 8:
                            clue.setText(getResources().getText(R.string.patternclue));
                            break;

                        case 9:
                            clue.setText(getResources().getText(R.string.numdigclue));
                            break;

                        case 10:
                            clue.setText(getResources().getText(R.string.operationsclue));
                            break;

                        case 11:
                            clue.setText(getResources().getText(R.string.muladdclue));
                            break;

                    }



                    // scratchImageView.setVisibility(View.VISIBLE);
                } else {
                    if (coin.getInt("coin", 0) == 2)
                        Toast.makeText(Number.this, "only 2 left", Toast.LENGTH_SHORT).show();
                    else
                        Toast.makeText(Number.this, "sorry", Toast.LENGTH_SHORT).show();
                }
      /* scratchImageView.setVisibility(View.VISIBLE);
        scratchImageView.setRevealListener(new ScratchImageView.IRevealListener() {
            @Override
            public void onRevealed(ScratchImageView scratchImageView) {
                Toast.makeText(Control.this , "revealed" , Toast.LENGTH_SHORT).show();
                //Don't Write Any Code Here
            }
        });*/
                //flipout.setVisibility(View.INVISIBLE);

            }
        };
    }

    private void showflipanimationclue() {
        View flipinclue = diaclue.findViewById(R.id.solutionflipinclue);
        View flipoutclue = diaclue.findViewById(R.id.scratchView1clue);
        View framlayout = diaclue.findViewById(R.id.frameLayoutclue);
        FlipAnimator animator = new FlipAnimator(flipinclue, flipoutclue, framlayout.getWidth() / 2, framlayout.getHeight() / 2);
        animator.setDuration(800);
        animator.setRotationDirection(FlipAnimator.DIRECTION_Y);
        framlayout.startAnimation(animator);
    }

    private void solutiondialog()
    {
        diasol = new Dialog(Number.this);
        diasol.getWindow().setWindowAnimations(R.style.DialogAnimation);
        diasol.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        diasol.getWindow().setGravity(Gravity.CENTER_HORIZONTAL);
        diasol.setContentView(R.layout.solutiondialog);
        diasol.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialogInterface) {
                settimer(lefttime);
                starttimer();
            }
        });
        cancelsol=(TextView) diasol.findViewById(R.id.tvcancelsol);
        cancelsol.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                diasol.dismiss();
            }
        });

        revealsol=(TextView) diasol.findViewById(R.id.buttonrevealsol);
        scratchImageView = (ScratchImageView) diasol.findViewById(R.id.scratchView1);
        solutionrevealed=(ImageView)diasol.findViewById(R.id.solutionflipout);
        solution=(TextView)diasol.findViewById(R.id.tvsolutionid);
        solutionrevealed.setVisibility(View.GONE);
        solution.setVisibility(View.GONE);

        diasol.show();

        // flipout= diasol.findViewById(R.id.solutionflipout);




    }

    @Override
    protected void onStart() {
        super.onStart();
        Toast.makeText(Number.this,"Start function" , Toast.LENGTH_SHORT).show();
       /* if(getSharedPreferences(null , MODE_PRIVATE).getBoolean("keyofsoundlevel",true))
        {   startService(svc);}*/
        if(getSharedPreferences(null , MODE_PRIVATE).getInt("lifecount",0)==1)
        {count= 1;}

        if(getSharedPreferences(null , MODE_PRIVATE).getInt("lifecount",0)==2)
        {count=2;}

        if(getSharedPreferences(null , MODE_PRIVATE).getInt("lifecount",0)==3)
        {count=3;}
}

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if(getSharedPreferences(null , MODE_PRIVATE).getBoolean("keyofsoundlevel",true))
        {stopService(svc);}
        getSharedPreferences(null , MODE_PRIVATE).edit().putInt("lifecount", count).apply();
        Intent intent=new Intent(Number.this, Home.class);
        intent.putExtra("backfromfab",true);
        startActivity(intent);
        finish();
    }
    public void onretry(final int levfag){


        cancelTimer();
        final Dialog d = new Dialog(Number.this,android.R.style.Theme_Black_NoTitleBar_Fullscreen);
        d.requestWindowFeature(Window.FEATURE_NO_TITLE);
        d.getWindow().setWindowAnimations(R.style.DialogAnimation);
        d.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        d.getWindow().setGravity(Gravity.CENTER_HORIZONTAL);
        d.setCancelable(false);
        d.setContentView(R.layout.dialog);
        final TextView tvcross=(TextView)d.findViewById(R.id.tvoncross) ;
        tvcross.setVisibility(View.INVISIBLE);
       /* if(levfag==2)
            tvcross.setText(getResources().getText(R.string.cross1));
        if (levfag==3)
            tvcross.setText(getResources().getText(R.string.cross2));*/
        d.show();
        Button diab=  (Button)d.findViewById(R.id.dialogbutton);
        diab.setText("Retry");

        diab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                level=levfag;
                settimer(30000);
                starttimer();
                //Fragment fragment = getFragmentManager().findFragmentByTag(TAG_FRAGMENT);

                FragmentManager fragmentManager1;
                FragmentTransaction fragmentTransaction1;
                fragmentManager1 = getFragmentManager();
                fragmentTransaction1 = fragmentManager1.beginTransaction();
                fragmentTransaction1.remove(getFragmentManager().findFragmentById(R.id.numbermainlayout));
                fragmentTransaction1.addToBackStack(null);

                switch (levfag)
                {case 2:
                    Toast.makeText(Number.this,"case 2",Toast.LENGTH_SHORT).show();

                    fragmentTransaction1.replace(R.id.numbermainlayout,calender).commit();
                    d.dismiss();
                    break;
                    case 3: fragmentTransaction1.replace(R.id.numbermainlayout,invert).commit();
                        d.dismiss();
                        break;
                    case 4: fragmentTransaction1.replace(R.id.numbermainlayout,smallest).commit();
                        d.dismiss();
                        break;
                    case 5: fragmentTransaction1.replace(R.id.numbermainlayout,no_sum).commit();
                        d.dismiss();
                        break;
                    case 6: fragmentTransaction1.replace(R.id.numbermainlayout,series).commit();
                        d.dismiss();
                        break;
                    case 7: fragmentTransaction1.replace(R.id.numbermainlayout,prime).commit();
                        d.dismiss();
                        break;
                    case 8: fragmentTransaction1.replace(R.id.numbermainlayout,pattern).commit();
                        d.dismiss();
                        break;
                    case 9: fragmentTransaction1.replace(R.id.numbermainlayout,numdig).commit();
                        d.dismiss();
                        break;
                    case 10: fragmentTransaction1.replace(R.id.numbermainlayout,operations).commit();
                        d.dismiss();
                        break;
                    case 11: fragmentTransaction1.replace(R.id.numbermainlayout,muladd).commit();
                        d.dismiss();
                        break;
                    /*case 12: fragmentTransaction1.replace(R.id.mal,l12f).commit();
                        d.dismiss();
                        break;
                    case 13: fragmentTransaction1.replace(R.id.mal,l13f).commit();
                        d.dismiss();
                        break;
                    case 14: fragmentTransaction1.replace(R.id.mal,l14f).commit();
                        d.dismiss();
                        break;
                    case 15: fragmentTransaction1.replace(R.id.mal,l15f).commit();
                        d.dismiss();
                        break;
                    case 16: fragmentTransaction1.replace(R.id.mal,l16f).commit();
                        d.dismiss();
                        break;
                    case 17: fragmentTransaction1.replace(R.id.mal,l17f).commit();
                        d.dismiss();
                        break;
                    case 18: fragmentTransaction1.replace(R.id.mal,l18f).commit();
                        d.dismiss();
                        break;*/

                }




            }
        });

    }

    public void oncrose(final int levfag){


        cancelTimer();
        final Dialog d = new Dialog(Number.this,android.R.style.Theme_Black_NoTitleBar_Fullscreen);
        d.requestWindowFeature(Window.FEATURE_NO_TITLE);
        d.getWindow().setWindowAnimations(R.style.DialogAnimation);
        d.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        d.getWindow().setGravity(Gravity.CENTER_HORIZONTAL);
        d.setCancelable(false);
        d.setContentView(R.layout.dialog);
        final TextView tvcross=(TextView)d.findViewById(R.id.tvoncross) ;
        tvcross.setVisibility(View.INVISIBLE);
       /* if(levfag==2)
            tvcross.setText(getResources().getText(R.string.cross1));
        if (levfag==3)
            tvcross.setText(getResources().getText(R.string.cross2));*/
        d.show();
        Button diab=  (Button)d.findViewById(R.id.dialogbutton);


        diab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                level=levfag;
                FragmentManager fragmentManager1;
                FragmentTransaction fragmentTransaction1;
                fragmentManager1 = getFragmentManager();
                fragmentTransaction1 = fragmentManager1.beginTransaction();
                settimer(30000);
                starttimer();
                switch (levfag)
                {case 2: fragmentTransaction1.replace(R.id.numbermainlayout,calender).commit();
                    d.dismiss();
                    break;
                    case 3: fragmentTransaction1.replace(R.id.numbermainlayout,invert).commit();
                        d.dismiss();
                        break;
                    case 4: fragmentTransaction1.replace(R.id.numbermainlayout,smallest).commit();
                        d.dismiss();
                        break;
                    case 5: fragmentTransaction1.replace(R.id.numbermainlayout,no_sum).commit();
                        d.dismiss();
                        break;
                    case 6: fragmentTransaction1.replace(R.id.numbermainlayout,series).commit();
                        d.dismiss();
                        break;
                    case 7: fragmentTransaction1.replace(R.id.numbermainlayout,prime).commit();
                        d.dismiss();
                        break;
                    case 8: fragmentTransaction1.replace(R.id.numbermainlayout,pattern).commit();
                        d.dismiss();
                        break;
                    case 9: fragmentTransaction1.replace(R.id.numbermainlayout,numdig).commit();
                        d.dismiss();
                        break;
                    case 10: fragmentTransaction1.replace(R.id.numbermainlayout,operations).commit();
                        d.dismiss();
                        break;
                    case 11: fragmentTransaction1.replace(R.id.numbermainlayout,muladd).commit();
                        d.dismiss();
                        break;
                    /*case 12: fragmentTransaction1.replace(R.id.mal,l12f).commit();
                        d.dismiss();
                        break;
                    case 13: fragmentTransaction1.replace(R.id.mal,l13f).commit();
                        d.dismiss();
                        break;
                    case 14: fragmentTransaction1.replace(R.id.mal,l14f).commit();
                        d.dismiss();
                        break;
                    case 15: fragmentTransaction1.replace(R.id.mal,l15f).commit();
                        d.dismiss();
                        break;
                    case 16: fragmentTransaction1.replace(R.id.mal,l16f).commit();
                        d.dismiss();
                        break;
                    case 17: fragmentTransaction1.replace(R.id.mal,l17f).commit();
                        d.dismiss();
                        break;
                    case 18: fragmentTransaction1.replace(R.id.mal,l18f).commit();
                        d.dismiss();
                        break;*/

                }


            }
        });

    }
    private void showflipanimation() {
        View flipin = diasol.findViewById(R.id.solutionflipin);
        View flipout= diasol.findViewById(R.id.scratchView1);
        View framlayout = diasol.findViewById(R.id.frameLayout);
      /*  flipin.setAnimation(fadeout);
        flipin.setVisibility(View.INVISIBLE);
        flipout.setVisibility(View.VISIBLE);
        flipout.setAnimation(fadein);*/

        FlipAnimator animator = new FlipAnimator(flipin, flipout , framlayout.getWidth()/2, framlayout.getHeight()/2);
        animator.setDuration(800);
        animator.setRotationDirection(FlipAnimator.DIRECTION_Y);
        framlayout.startAnimation(animator);
    }

    private View.OnClickListener revealsolbutton() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Integer n = numbers.get("sol"+String.valueOf(level));
                Integer sp=no_of_time_checker_cluesol.getInt("solno"+String.valueOf(level),0);
                //Toast.makeText(Control.this,""+n,Toast.LENGTH_SHORT).show();
                if (n == null) {
                    n=0;
                }
                if(coin.getInt("coin",0)>5 || sp==level) {
                    if(sp!=level)
                    {int cointemp;
                        //if(scretchoff==false)
                        cointemp = coin.getInt("coin", 0) - 5;
                        coin.edit().putInt("coin", cointemp).apply();
                        Toast.makeText(Number.this,""+ cointemp,Toast.LENGTH_SHORT).show();
                        coinv.setText(String.valueOf(cointemp));

                        numbers.put("solno"+String.valueOf(level), level);
                        no_of_time_checker_cluesol.edit().putInt("solno"+String.valueOf(level),level).apply();

                    }
                    revealsol.setVisibility(View.GONE);

                    showflipanimation();
                    solutionrevealed.setVisibility(View.VISIBLE);
                    if(sp==level)
                    {scratchImageView.setVisibility(View.GONE);}
                    solutionrevealed.startAnimation(fadein);
                    cancelsol.setPadding(300,5,0,0);
                    solution.setVisibility(View.VISIBLE);
                    solution.setTextSize(25);
                    switch (level)
                    {
                        case 1:solution.setText(getResources().getText(R.string.ascsol));
                            break;
                        case 2:solution.setText(getResources().getText(R.string.calsol));
                            break;
                        case 3:solution.setText(getResources().getText(R.string.invertsol));
                            break;
                        case 4:solution.setText(getResources().getText(R.string.smallestclue));
                            break;
                        case 5:solution.setText(getResources().getText(R.string.nosumsol));
                            break;

                        case 6:solution.setText(getResources().getText(R.string.seriessol));
                            break;

                        case 7:solution.setText(getResources().getText(R.string.primesol));
                            break;

                        case 8:solution.setText(getResources().getText(R.string.patternsol));
                            break;

                        case 9:solution.setText(getResources().getText(R.string.numdigsol));
                            break;

                        case 10:solution.setText(getResources().getText(R.string.operationssol));
                            break;
                        case 11 :solution.setText(getResources().getText(R.string.muladdsol));
                            break;

                        case 12 :if(islightsensor.getInt("value",0)==0) {
                            solution.setText(getResources().getText(R.string.solutionlevel0_12));
                        }else
                        {solution.setText(getResources().getText(R.string.solutionlevel1_12));
                        }
                            break;

                        case 13 :solution.setText(getResources().getText(R.string.solutionlevel_13));
                            break;

                        case 14 :solution.setText(getResources().getText(R.string.solutionlevel_14));
                            break;

                        case 15 :solution.setText(getResources().getText(R.string.solutionlevel_15));
                            break;
                        case 16 :if(iscountsensor.getInt("value",0)==0) {
                            solution.setText(getResources().getText(R.string.solutionlevel0_16));
                        }else
                        {      solution.setText(getResources().getText(R.string.solutionlevel1_16));}
                            break;
                        case 17:solution.setText(getResources().getText(R.string.solutionlevel_17));
                            break;



                    }


                }
                else{
                    if(coin.getInt("coin",0)==5)
                        Toast.makeText(Number.this , "only 5 left" , Toast.LENGTH_SHORT).show();
                    else
                        Toast.makeText(Number.this , "sorry" , Toast.LENGTH_SHORT).show();}
                // scratchImageView.setVisibility(View.VISIBLE);
                //flipout.setVisibility(View.INVISIBLE);

            }
        };
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(getSharedPreferences(null , MODE_PRIVATE).getBoolean("keyofsoundlevel",true))
        { stopService(svc);}
        cancelTimer();
    }
 /*   public void  retry(final int levfag) {
    cancelTimer();
    final Dialog d = new Dialog(Number.this, android.R.style.Theme_Black_NoTitleBar_Fullscreen);
    d.requestWindowFeature(Window.FEATURE_NO_TITLE);
    d.getWindow().setWindowAnimations(R.style.DialogAnimation);
    d.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
    d.getWindow().setGravity(Gravity.CENTER_HORIZONTAL);
    d.setCancelable(false);
    d.setContentView(R.layout.retrydialog);
    final TextView tvcross = (TextView) d.findViewById(R.id.tvretry);
    tvcross.setVisibility(View.INVISIBLE);
       /* if(levfag==2)
            tvcross.setText(getResources().getText(R.string.cross1));
        if (levfag==3)
            tvcross.setText(getResources().getText(R.string.cross2));
    d.show();
    Button diab = (Button) d.findViewById(R.id.button);


    diab.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            level=levfag;

            settimer(30000);
            starttimer();
            FragmentManager fragmentManager1;
            FragmentTransaction fragmentTransaction1;
            fragmentManager1 = getFragmentManager();
            fragmentTransaction1 = fragmentManager1.beginTransaction();
            //fragmentTransaction1.replace(R.id.numbermainlayout,).commit();
            d.dismiss();

        }
    });

}*/}