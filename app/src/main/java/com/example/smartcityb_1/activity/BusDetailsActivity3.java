package com.example.smartcityb_1.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.example.smartcityb_1.AppClient;
import com.example.smartcityb_1.R;
import com.example.smartcityb_1.bean.BusStation;
import com.example.smartcityb_1.bean.BusTitle;
import com.example.smartcityb_1.net.VolleyLo;
import com.example.smartcityb_1.net.VolleyTo;
import com.example.smartcityb_1.util.Utils;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

/**
 * @Login Name win10
 * @Create by 张瀛煜 on 2020/10/8 at 15:10
 */
public class BusDetailsActivity3 extends AppCompatActivity {
    private BusTitle busTitle;
    private String time;
    private ImageView itemChange;
    private TextView title;
    private TextView title1;
    private TextView tvLine;
    private EditText etName;
    private EditText etTel;
    private Spinner etUp;
    private Spinner etDown;
    private TextView tvNext;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bus_details_layout3);
        busTitle = (BusTitle) getIntent().getSerializableExtra("date");
        time = getIntent().getStringExtra("time");
        initView();
        title.setText("定制班车");
        itemChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });AppClient.add2(this);
        tvLine.setText("起点：" + busTitle.getStartSite() + "  终点：" + busTitle.getEndSite());
        setVolley();
        tvNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BusDetailsActivity3.this, BusDetailsActivity4.class);
                intent.putExtra("date", busTitle);
                intent.putExtra("time", getIntent().getStringExtra("time"));
                intent.putExtra("name", etName.getText().toString());
                intent.putExtra("tel", etTel.getText().toString());
                intent.putExtra("up", etUp.getSelectedItem().toString());
                intent.putExtra("down", etDown.getSelectedItem().toString());
                startActivity(intent);
            }
        });

    }

    List<BusStation> busStations;
    private List<String> up, dowm;

    private void setVolley() {
        up = new ArrayList<>();
        dowm = new ArrayList<>();
        VolleyTo volleyTo = new VolleyTo();
        volleyTo.setUrl("busStationById")
                .setJsonObject("busid", busTitle.getBusid())
                .setVolleyLo(new VolleyLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        busStations = new Gson().fromJson(jsonObject.optJSONArray(Utils.Row).toString()
                                , new TypeToken<List<BusStation>>() {
                                }.getType());
                        for (int i = 0; i < busStations.size(); i++) {
                            BusStation busStation = busStations.get(i);
                            up.add(busStation.getSiteName());
                            dowm.add(busStation.getSiteName());
                        }
                        etUp.setAdapter(new ArrayAdapter<String>(BusDetailsActivity3.this, android.R.layout.simple_list_item_1
                                , up));
                        etDown.setAdapter(new ArrayAdapter<String>(BusDetailsActivity3.this, android.R.layout.simple_list_item_1
                                , dowm));


                    }

                    @Override
                    public void onErrorResponse(VolleyError volleyError) {

                    }
                }).start();
    }

    private void initView() {
        itemChange = findViewById(R.id.item_change);
        title = findViewById(R.id.title);
        title1 = findViewById(R.id.title1);
        tvLine = findViewById(R.id.tv_line);
        etName = findViewById(R.id.et_name);
        etTel = findViewById(R.id.et_tel);
        etUp = findViewById(R.id.et_up);
        etDown = findViewById(R.id.et_down);
        tvNext = findViewById(R.id.tv_next);
    }
}
