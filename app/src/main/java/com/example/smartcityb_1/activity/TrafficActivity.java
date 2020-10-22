package com.example.smartcityb_1.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.example.smartcityb_1.R;
import com.example.smartcityb_1.adapter.SubwayStationAdapter;
import com.example.smartcityb_1.bean.SubwayStation;
import com.example.smartcityb_1.net.VolleyLo;
import com.example.smartcityb_1.net.VolleyTo;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONObject;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

/**
 * @Login Name win10
 * @Create by 张瀛煜 on 2020/10/2 at 9:01
 */
public class TrafficActivity extends AppCompatActivity {
    private ImageView itemChange;
    private TextView title;
    private List<SubwayStation> subwayStations;
    private ListView listView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.traffic_layout);
        initView();
        itemChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        title.setText("地铁查询");
        setVolley_Traffic();
    }

    private void setVolley_Traffic() {
        VolleyTo volleyTo = new VolleyTo();
        volleyTo.setUrl("getSubwaysByStation")
                .setJsonObject("stationName", "建国门站")
                .setVolleyLo(new VolleyLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        subwayStations = new Gson().fromJson(jsonObject.optJSONArray("ROWS_DETAIL").toString()
                                , new TypeToken<List<SubwayStation>>() {
                                }.getType());
                        setListView();
                    }

                    @Override
                    public void onErrorResponse(VolleyError volleyError) {

                    }
                }).start();
    }

    private void setListView() {
        Collections.sort(subwayStations, new Comparator<SubwayStation>() {
            @Override
            public int compare(SubwayStation o1, SubwayStation o2) {
                return o1.getTime()-o2.getTime();
            }
        });
        listView.setAdapter(new SubwayStationAdapter(this, subwayStations));
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(TrafficActivity.this, TrafficDetailsActivity.class);
                intent.putExtra("info", subwayStations.get(position));
                intent.putExtra("nowStation","建国门站");
                startActivity(intent);
            }
        });


    }


    private void initView() {
        itemChange = findViewById(R.id.item_change);
        title = findViewById(R.id.title);
        listView = findViewById(R.id.list_view);
    }
}
