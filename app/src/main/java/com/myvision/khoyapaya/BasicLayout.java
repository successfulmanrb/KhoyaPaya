package com.myvision.khoyapaya;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class BasicLayout extends AppCompatActivity {
    Button play,instr,settings;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basic_layout);
        play=(Button)findViewById(R.id.basic_play);
        instr=(Button)findViewById(R.id.basic_instr);
        settings=(Button)findViewById(R.id.basic_settings);
        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(BasicLayout.this,ChoiceActivity.class);
                startActivity(i);
            }
        });
        instr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(BasicLayout.this,Instruction.class);
                startActivity(i);
            }
        });
        settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(BasicLayout.this,Settings.class);
                startActivity(i);
            }
        });
    }
}
