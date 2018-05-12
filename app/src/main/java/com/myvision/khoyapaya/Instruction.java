package com.myvision.khoyapaya;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.widget.TextView;

public class Instruction extends Activity {
Intent intent;
    Intent svc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instruction);
        svc=new Intent(this, PlayAudio.class);
        ((TextView)findViewById(R.id.gddis)).setText(Html.fromHtml(getResources().getString(R.string.inst)));
    }
    @Override
    public void onBackPressed() {
      intent=new Intent(Instruction.this,Home.class);
        startActivity(intent);
        finish();
        overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left);
    }
    @Override
    protected void onPause() {
        super.onPause();
        if(intent==null)
            stopService(svc);
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
}
