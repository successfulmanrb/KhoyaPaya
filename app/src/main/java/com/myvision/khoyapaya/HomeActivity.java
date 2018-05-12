package com.myvision.khoyapaya;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class HomeActivity extends Activity {
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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home2);
       /* moveleftred();
        moveupblue();
        movedownyellow();
        moverightgreen();

     /  Typeface tf = Typeface.createFromAsset(getAssets(),
                "fonts/DroidSansFallback.ttf");
        TextView tv = (TextView) findViewById(R.id.khoyapayatv);
        tv.setTypeface(tf);
*/

    }
}
