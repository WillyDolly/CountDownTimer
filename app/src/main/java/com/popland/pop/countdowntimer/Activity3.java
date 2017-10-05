package com.popland.pop.countdowntimer;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.text.DecimalFormat;

public class Activity3 extends AppCompatActivity {
TextView tv;
    CountDownTimer cdt;
    MediaPlayer mf;
    int t = 29;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_3);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        tv = (TextView)findViewById(R.id.TV);
        mf = MediaPlayer.create(Activity3.this, R.raw.woodclock);
    }

    public void ClickStart(View view){
        cdt = new CountDownTimer(30000, 1) {//đồng hồ điếm ngược chuẩn xác nhất
            @Override
            public void onTick(long ms) {
                float secondLeft = (float)ms /1000;
                //âm thanh gõ khớp với sự thay đổi từng giây
                String str = "0."+String.format("%03d",ms%1000);
                float STR = Float.parseFloat(str);
                int f = Math.round(secondLeft - STR);
                if(f != t){
                    t = f;
                    mf.start();
                }
                if(f<10){// âm thanh chạy nhanh vào 10s cuối
                   mf.start();
                }
                Log.i("test","secondLeft: "+secondLeft+" STR: "+STR+" f: "+f+" t: "+t);

                DecimalFormat dcf = new DecimalFormat("0.000");
                tv.setText(dcf.format(30 - secondLeft));
            }

            @Override
            public void onFinish() {
                tv.setText("30");
            }
        }.start();
    }

    public void ClickStop(View view){
        cdt.cancel();
    }

}
