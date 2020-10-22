package com.example.smartcityb_1.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.smartcityb_1.R;
import com.example.smartcityb_1.adapter.LjExpand;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

/**
 * @Login Name win10
 * @Create by 张瀛煜 on 2020/10/8 at 17:11
 */
public class LjLayout extends AppCompatActivity {
    private ImageView itemChange;
    private TextView title;
    private TextView title1;
    private ExpandableListView expandLj;

    private Map<String, List<String>> map;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lj_layout);
        initView();
        map = new HashMap<>();
        List<String> strings = new ArrayList<>();
        strings.add("白纸");
        strings.add("筷子");
        strings.add("纸杯");
        map.put("木头", strings);
        List<String> strings1 = new ArrayList<>();
        strings1.add("玻璃杯");
        strings1.add("酒杯");
        strings1.add("啤机瓶");
        map.put("玻璃", strings1);
        title.setText(getIntent().getStringExtra("name"));
        itemChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        expandLj.setAdapter(new LjExpand(map));
    }

    private void initView() {
        itemChange = findViewById(R.id.item_change);
        title = findViewById(R.id.title);
        title1 = findViewById(R.id.title1);
        expandLj = findViewById(R.id.expand_lj);
    }
}
