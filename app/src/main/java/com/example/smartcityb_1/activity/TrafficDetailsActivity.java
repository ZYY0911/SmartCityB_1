package com.example.smartcityb_1.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.example.smartcityb_1.R;
import com.example.smartcityb_1.bean.StaionInfo;
import com.example.smartcityb_1.bean.SubwayStation;
import com.example.smartcityb_1.net.VolleyLo;
import com.example.smartcityb_1.net.VolleyTo;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONObject;

import java.util.List;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

/**
 * @Login Name win10
 * @Create by 张瀛煜 on 2020/10/2 at 9:21
 */
public class TrafficDetailsActivity extends AppCompatActivity {
    private ImageView itemChange;
    private TextView title;
    private TextView title1;
    private TextView tvLine;
    private TextView tvNext;
    private TextView tvTime;
    private LinearLayout layoutStation;
    private SubwayStation subwayStation;
    private List<StaionInfo> staionInfos;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.traffoc_details_layout);
        initView();
        subwayStation = (SubwayStation) getIntent().getSerializableExtra("info");
        title.setText(subwayStation.getName());
        setVolley_Detail();
        itemChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        title1.setText("详情");

        title1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(TrafficDetailsActivity.this, AllTrafficActivity.class));
            }
        });
    }

    private void setVolley_Detail() {
        VolleyTo volleyTo = new VolleyTo();
        volleyTo.setUrl("getAllStationById")
                .setJsonObject("id", subwayStation.getSubwayid())
                .setVolleyLo(new VolleyLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        staionInfos = new Gson().fromJson(jsonObject.optJSONArray("ROWS_DETAIL").toString()
                                , new TypeToken<List<StaionInfo>>() {
                                }.getType());
                        setViewItem();
                    }

                    @Override
                    public void onErrorResponse(VolleyError volleyError) {

                    }
                }).start();
    }

    private void setViewItem() {
        tvLine.setText(staionInfos.get(0).getStationname() + "——" + staionInfos.get(staionInfos.size() - 1).getStationname());
        tvNext.setText("即将到站：" + subwayStation.getNextname());
        String nowState = getIntent().getStringExtra("nowStation");
        int index = 0, nowIndex = 0;
        long distance = 0;
        for (int i = 0; i < staionInfos.size(); i++) {
            if (staionInfos.get(i).getStationname().equals(nowState)) {
                nowIndex = staionInfos.get(i).getStationIndex();
            }
            if (staionInfos.get(i).getStationname().equals(subwayStation.getNextname())) {
                index = staionInfos.get(i).getStationIndex();
            }
        }
        for (int i = 0; i < staionInfos.size(); i++) {
            if (staionInfos.get(i).getStationIndex() > Math.min(index, nowIndex) && staionInfos.get(i).getStationIndex() < Math.max(index, nowIndex)) {
                distance += staionInfos.get(i).getDistance();
            }
        }
        tvTime.setText("剩余时间：" + subwayStation.getTime() + "分钟       间隔：" + (Math.max(index, nowIndex) - Math.min(index, nowIndex)) + "站(距离" + distance + "km)");
        layoutStation.removeAllViews();
        for (int i = 0; i < staionInfos.size(); i++) {
            View view = LayoutInflater.from(this).inflate(R.layout.details_item, null);
            ViewHolder holder = new ViewHolder();
            holder.itemLayout = view.findViewById(R.id.item_layout);
            holder.itemBg = view.findViewById(R.id.item_bg);
            holder.itemStation = view.findViewById(R.id.item_station);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            layoutParams.setMargins(15, 0, 15, 0);
            view.setLayoutParams(layoutParams);
            StaionInfo staionInfo = staionInfos.get(i);
            holder.itemStation.setText(staionInfo.getStationname());
            if (staionInfo.getStationIndex() < index) {
                holder.itemBg.setBackgroundResource(R.drawable.details_3);
            } else if (staionInfo.getStationIndex() == index) {
                holder.itemBg.setBackgroundResource(R.drawable.details_2);
            } else {
                holder.itemBg.setBackgroundResource(R.drawable.details_1);
            }
            layoutStation.addView(view);
        }
    }

    static class ViewHolder {
        private LinearLayout itemLayout;
        private TextView itemBg;
        private TextView itemStation;
    }

    private void initView() {
        itemChange = findViewById(R.id.item_change);
        title = findViewById(R.id.title);
        title1 = findViewById(R.id.title1);
        tvLine = findViewById(R.id.tv_line);
        tvNext = findViewById(R.id.tv_next);
        tvTime = findViewById(R.id.tv_time);
        layoutStation = findViewById(R.id.layout_station);

    }
}
