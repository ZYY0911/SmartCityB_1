package com.example.smartcityb_1.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


import com.example.smartcityb_1.R;
import com.example.smartcityb_1.bean.TccInfo;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

/**
 * @Login Name win10
 * @Create by 张瀛煜 on 2020/10/8 at 9:13
 */
public class TccDetails extends AppCompatActivity {
    private ImageView itemChange;
    private TextView title;
    private TextView title1;
    private TextView tvMsg;
    private TccInfo tccInfo;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tcc_detalis_layout);
        initView();
        title.setText("停车场详情");
        itemChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        tccInfo = (TccInfo) getIntent().getSerializableExtra("info");
        tvMsg.setText("停车场名称：" + tccInfo.getParkName() + "\r\n" +
                "地址：" + tccInfo.getAddress() + "\r\n" +
                "距离：" + tccInfo.getDistance() + "\r\n" +
                "是否对外开放：" + (tccInfo.getIsOpen().equals("Y") ? "对外开放" : "不对外开放") + "\r\n" +
                "车位信息：\r\n" +
                "    停车费：" + tccInfo.getRate() + "\r\n" +
                "    剩余车位：" + tccInfo.getSpaceNum() + "/" + tccInfo.getSpaceNum() + "\r\n" +
                "    参考收费：" + tccInfo.getRateRefer());
    }

    private void initView() {
        itemChange = findViewById(R.id.item_change);
        title = findViewById(R.id.title);
        title1 = findViewById(R.id.title1);
        tvMsg = findViewById(R.id.tv_msg);
    }
}
