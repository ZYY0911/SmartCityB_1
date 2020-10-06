package com.example.smartcityb_1.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.smartcityb_1.R;
import com.example.smartcityb_1.util.Utils;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

/**
 * @Login Name win10
 * @Create by 张瀛煜 on 2020/10/6 at 10:27
 */
public class YYLyDetailsActivity extends AppCompatActivity {
    private ImageView itemChange;
    private TextView title;
    private TextView title1;
    private ImageView ivPhoto;
    private TextView tvMsg;
    private TextView tvBm;

    private int index = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ylyxq_layout);
        initView();
        index = getIntent().getIntExtra("index", 1);
        title.setText(index == 1 ? "养老院1" : "养老院2");
        tvMsg.setText("        点击“查询”按钮跳转至违章记录页面，标签栏显示本页面标题，点击返回图标返回到上一页，查询出所有违章数据，需有违法时间、违章地点、违章记分、罚款金额、处理状态，默认显示5~6条记录，点击查看更多显示全部违章记录。点击“查询”按钮跳转至违章记录页面，标签栏显示本页面标题，点击返回图标返回到上一页，查询出所有违章数据，需有违法时间、违章地点、违章记分、罚款金额、处理状态，默认显示5~6条记录，点击查看更多显示全部违章记录。点击“查询”按钮跳转至违章记录页面，标签栏显示本页面标题，点击返回图标返回到上一页，查询出所有违章数据，需有违法时间、违章地点、违章记分、罚款金额、处理状态，默认显示5~6条记录，点击查看更多显示全部违章记录。");
        tvBm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Utils.showToast("预约成功", YYLyDetailsActivity.this);
            }
        });


    }

    private void initView() {
        itemChange = findViewById(R.id.item_change);
        title = findViewById(R.id.title);
        title1 = findViewById(R.id.title1);
        ivPhoto = findViewById(R.id.iv_photo);
        tvMsg = findViewById(R.id.tv_msg);
        tvBm = findViewById(R.id.tv_bm);
    }
}
