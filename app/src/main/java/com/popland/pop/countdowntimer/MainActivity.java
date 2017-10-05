package com.popland.pop.countdowntimer;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
TextView tvGiay, tv2;
    Button btnGo, btn2;
    int secondsLeft = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, Activity2.class);
                startActivity(i);
            }
        });
        tvGiay = (TextView)findViewById(R.id.TVgiay);
        btnGo = (Button)findViewById(R.id.BTNgo);
        tv2 = (TextView)findViewById(R.id.TV2);
        btn2 = (Button)findViewById(R.id.BTN2);
        btnGo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               new CountDownTimer(10000, 250){

                   @Override
                   public void onTick(long ms) {
                     if(Math.round((float)ms/1000)!= secondsLeft){//ms phải >=500
                         secondsLeft = Math.round((float)ms/1000);//Math.round làm tròn >=0.5 = 1
                         tvGiay.setText(secondsLeft+"");
                     }
                       Log.i("test","ms ="+ms+"till finished ="+secondsLeft);//Log.i(tag, msg) tạo msg trong logcat
                   }

                   @Override
                   public void onFinish() {
                       tvGiay.setText("0");
                   }
               }.start();//10s: 10->0 bao gồm onTick &onFinish
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new CountDownTimer(10000, 1000) {
                    @Override
                    public void onTick(long ms) {
                        tv2.setText("remain: "+ms/1000);
                    }

                    @Override
                    public void onFinish() {
                       tv2.setText("done");
                    }
                }.start();//10s: 9->done gồm cả 2 method
            }             // từ 1 chuyển qua done dài 2s
        });
    }
}
