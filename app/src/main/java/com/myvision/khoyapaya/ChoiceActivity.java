package com.myvision.khoyapaya;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.myvision.khoyapaya.control.LevelNo;
import com.myvision.khoyapaya.number.Number_Level_Selector;

import static android.content.Context.MODE_PRIVATE;
import static com.myvision.khoyapaya.number.Number.life;

public class ChoiceActivity extends Activity {
Intent svc;
    Intent intent=null;
    ImageView number , control;
    private Intent i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choice);
        svc= new Intent(ChoiceActivity.this,PlayAudio.class);
        number=(ImageView) findViewById(R.id.imgnumberchoice);
        control=(ImageView)findViewById(R.id.imgcontrolchoice);
if(getIntent().getBooleanExtra("backfromfab",false)){
        if(!getSharedPreferences(null , MODE_PRIVATE).getBoolean("keyofsound",true))
        { Toast.makeText(ChoiceActivity.this, "coiceactivity of", Toast.LENGTH_SHORT).show();
            stopService(svc);}else{startService(svc);}}

        control.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              /*  if(life!=null)
                {*/ if(getSharedPreferences(null , MODE_PRIVATE).getBoolean("opencontrol",false))
                { intent =new Intent(ChoiceActivity.this, LevelNo.class);
                startActivity(intent);
         overridePendingTransition(R.anim.right_to_left,R.anim.left_to_right);
}else {
                    Toast.makeText(ChoiceActivity.this, "clear 5 number level 1st", Toast.LENGTH_SHORT).show();
                }/*}else{ Toast.makeText(ChoiceActivity.this, "clear 5 number level 1st life", Toast.LENGTH_SHORT).show();}*/}});
        number.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
 intent =new Intent(ChoiceActivity.this, Number_Level_Selector.class);
                startActivity(intent);
                overridePendingTransition(R.anim.right_to_left,R.anim.left_to_right);
            }
        });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
     i=new Intent(ChoiceActivity.this,Home.class);
        startActivity(i);
        overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left);

    }
    @Override
    protected void onDestroy() {
        super.onDestroy();


     /*   if(getSharedPreferences(null , MODE_PRIVATE).getBoolean("keyofsound",true))
        {Toast.makeText(ChoiceActivity.this, "des", Toast.LENGTH_SHORT).show();
            stopService(svc);}*/
    }
    @Override
    protected void onRestart() {
        super.onRestart();
        Toast.makeText(ChoiceActivity.this, "tes", Toast.LENGTH_SHORT).show();
        if(getSharedPreferences(null , MODE_PRIVATE).getBoolean("keyofsound",true))
        {
            startService(svc);}
    }
    @Override
    protected void onPause() {
        super.onPause();
        if(intent==null&&i==null)
            stopService(svc);
        //Toast.makeText(Home.this, "pause", Toast.LENGTH_SHORT).show();

    }
}
