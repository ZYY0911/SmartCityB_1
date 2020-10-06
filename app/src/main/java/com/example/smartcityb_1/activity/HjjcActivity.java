package com.example.smartcityb_1.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.smartcityb_1.R;

import java.util.Random;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

/**
 * @Login Name win10
 * @Create by 张瀛煜 on 2020/10/6 at 10:30
 */
public class HjjcActivity extends AppCompatActivity {
    private ImageView itemChange;
    private TextView title;
    private TextView title1;
    private TextView tvWd;
    private TextView tvCo;
    private TextView tvPm;
    private TextView tvXy;
    private TextView tvXl;
    private TextView tvTw;
    private TextView tvDyl;
    private TextView tvHdsj;
    private TextView tvTz;
    Random random = new Random();
    private boolean isLoop = true;
    private Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(@NonNull Message msg) {
            tvWd.setText("温度："+random.nextInt(40) + "℃");
            tvCo.setText("二氧化碳："+random.nextInt(8000) + "PPM");
            tvPm.setText("PM2.5"+random.nextInt(300) + "ug/m³");
            return false;
        }
    });

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hjjc_layout);
        initView();
        itemChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        //温度：27℃
        //二氧化碳：594PPM
        //PM2.5：200ug/m³
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (isLoop) {
                    handler.sendEmptyMessage(0);
                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
        //血压：200Pa
        //心率：60
        //体温：36.5℃
        //运动量：3999
        //户外活动时间：1h22min
        //体重：70kg
        tvXl.setText("心率："+random.nextInt(100) + "");
        tvTw.setText("体温："+random.nextInt(37) + random.nextFloat() + "℃");
        tvXy.setText("血压："+random.nextInt(300) + "Pa");
        tvDyl.setText("运动量："+random.nextInt(5000) + "");
        tvHdsj.setText("户外活动时间："+random.nextInt(2) + "h" + random.nextInt(61) + "min");
        tvTz.setText("体重："+random.nextInt(80) + "kg");


        title.setText("环境监测");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        isLoop = false;
    }

    private void initView() {
        itemChange = findViewById(R.id.item_change);
        title = findViewById(R.id.title);
        title1 = findViewById(R.id.title1);
        tvWd = findViewById(R.id.tv_wd);
        tvCo = findViewById(R.id.tv_co);
        tvPm = findViewById(R.id.tv_pm);
        tvXy = findViewById(R.id.tv_xy);
        tvXl = findViewById(R.id.tv_xl);
        tvTw = findViewById(R.id.tv_tw);
        tvDyl = findViewById(R.id.tv_dyl);
        tvHdsj = findViewById(R.id.tv_hdsj);
        tvTz = findViewById(R.id.tv_tz);
    }
}
