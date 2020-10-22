package com.example.smartcityb_1.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.smartcityb_1.AppClient;
import com.example.smartcityb_1.R;
import com.example.smartcityb_1.bean.BusTitle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

/**
 * @Login Name win10
 * @Create by 张瀛煜 on 2020/10/8 at 14:44
 */
public class BusDetailsActivity extends AppCompatActivity {
    private ImageView itemChange;
    private TextView title;
    private TextView title1;
    private ImageView ivPhoto;
    private TextView tvMsg;
    private TextView tvNext;
    private BusTitle busTitle;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bus_detalis_layout);
        AppClient.add2(this);
        initView();
        busTitle = (BusTitle) getIntent().getSerializableExtra("date");
        title.setText("定制班车");
        itemChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        tvMsg.setText("起点：" + busTitle.getStartSite() + "\r\n" +
                "终点：" + busTitle.getEndSite() + "\r\n" +
                "票价：" + busTitle.getPrice() + "\r\n" +
                "里程：" + busTitle.getMileage());
        tvNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BusDetailsActivity.this, BusDetailsActivity2.class);
                intent.putExtra("date", busTitle);
                startActivity(intent);
            }
        });
        ivPhoto.setImageResource(busTitle.getBusid() % 2 == 0 ? R.mipmap.ditu : R.mipmap.ditu2);

    }

    private void initView() {
        itemChange = findViewById(R.id.item_change);
        title = findViewById(R.id.title);
        title1 = findViewById(R.id.title1);
        ivPhoto = findViewById(R.id.iv_photo);
        tvMsg = findViewById(R.id.tv_msg);
        tvNext = findViewById(R.id.tv_next);
    }
}
