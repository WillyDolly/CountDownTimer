package com.popland.pop.countdowntimer;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import java.text.DecimalFormat;

public class Activity2 extends AppCompatActivity {
    TextView tvTGran, tvGiay;
    CountDownTimer cdt;
    long tongtg = 30000;
    int secondsLeft = 0;
    String millis_format = "%03d";//thêm số 0 đằng trước, 3: số lượng số, d: decimal
    String tg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              Intent i = new Intent(Activity2.this, Activity3.class);
                startActivity(i);
            }
        });
        tvTGran = (TextView) findViewById(R.id.TVtgran);
        tvGiay = (TextView) findViewById(R.id.TVgiay);
    }
    // countdown có giây & milli giây
    public void ClickEvent(View view) {
        cdt = new CountDownTimer(tongtg, 1) {
            @Override
            public void onTick(long ms) {
                if (Math.round((float) ms / 1000) != secondsLeft)//nếu secondsLeft thay đổi
                    secondsLeft = Math.round((float) ms / 1000);
                int roundMillis = secondsLeft * 1000;
                if (roundMillis == tongtg)
                    tg = secondsLeft + "." + String.format(millis_format, 0);//String.format định dạng tham số 2
                   else
                       tg =secondsLeft+"."+String.format(millis_format,ms%1000);
                tvGiay.setText(tg);
            }

            @Override
            public void onFinish() {
                tg = "0";
                tvGiay.setText(tg);
            }
        }.start();
    }

    public void ClickPause(View view){
        cdt.cancel();
        float stopoint=  Float.parseFloat(tg);
        DecimalFormat dcf = new DecimalFormat("#.000");
        tvTGran.setText(dcf.format(10 - stopoint));
    }
}