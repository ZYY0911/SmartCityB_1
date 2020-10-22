package com.example.smartcityb_1.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


import com.example.smartcityb_1.R;
import com.example.smartcityb_1.bean.HoDetails;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

/**
 * @Login Name win10
 * @Create by 张瀛煜 on 2020/10/9 at 7:53
 */
public class SFActivity extends AppCompatActivity {
    private ImageView itemChange;
    private TextView title;
    private TextView title1;
    private TextView tvText;
    private HoDetails hoDetails;
    private TextView tvState;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sf_layout);

        initView();
        title.setText("水费");
        itemChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        hoDetails = (HoDetails) getIntent().getSerializableExtra("date");
        int baleance = hoDetails.getBanlance();
        int cost = hoDetails.getCost();
        if (cost > baleance) {
            tvState.setText("欠费:" + (cost - baleance));
        } else {
            tvState.setText("暂无欠费");
        }
        tvText.setText("缴费单位：" + hoDetails.getUnit() + "\n" + "缴费户号：" + hoDetails.getAccountId() + "|" + hoDetails.getAccountAddress());

    }

    private void initView() {
        itemChange = findViewById(R.id.item_change);
        title = findViewById(R.id.title);
        title1 = findViewById(R.id.title1);
        tvText = findViewById(R.id.tv_text);
        tvState = findViewById(R.id.tv_state);
    }
}
