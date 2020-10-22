package com.example.smartcityb_1.activity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.example.smartcityb_1.R;
import com.example.smartcityb_1.adapter.TccHistoryAdapter;
import com.example.smartcityb_1.bean.Tcchistory;
import com.example.smartcityb_1.net.VolleyLo;
import com.example.smartcityb_1.net.VolleyTo;
import com.example.smartcityb_1.util.Utils;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONObject;

import java.util.List;
import java.util.Random;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

/**
 * @Login Name win10
 * @Create by 张瀛煜 on 2020/10/8 at 9:19
 */
public class TccHistoryActivity extends AppCompatActivity {
    private ImageView itemChange;
    private TextView title;
    private TextView title1;
    private LinearLayout layoutStart;
    private LinearLayout layoutEnd;
    private Button btQuery;
    private ListView listView;
    private TextView tvMore;
    List<Tcchistory> tcchistories;
    private TextView  etStart;
    private TextView etEnd;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tcc_history_layout);
        initView();
        setVolley();
        title.setText("停车场记录");
        itemChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        tvMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adapter.setCount(tcchistories.size());
                adapter.notifyDataSetChanged();
            }
        });
        layoutStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(TccHistoryActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        etStart.setText(year + "-" + (month + 1) + "-" + dayOfMonth);
                    }
                }, 2020, 9, 8);
                datePickerDialog.show();
            }
        });
        layoutEnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(TccHistoryActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        etEnd.setText(year + "-" + (month + 1) + "-" + dayOfMonth);
                    }
                }, 2020, 9, 8);
                datePickerDialog.show();
            }
        });
        btQuery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String strat = etStart.getText().toString();
                String end = etEnd.getText().toString();
                if (end.equals("") && !strat.equals("")) {
                    VolleyTo volleyTo = new VolleyTo();
                    volleyTo.setUrl("getParkingHistoryByIdAndInTime")
                            .setJsonObject("parkingid", 1)
                            .setJsonObject("inTime1", "")
                            .setJsonObject("inTime2", strat)
                            .setVolleyLo(new VolleyLo() {
                                @Override
                                public void onResponse(JSONObject jsonObject) {
                                    List<Tcchistory> tcchistories1 = new Gson().fromJson(jsonObject.optJSONArray(Utils.Rows).toString()
                                            , new TypeToken<List<Tcchistory>>() {
                                            }.getType());
                                    adapter.setCount((random.nextInt(2) + 1) % 2 == 0 ? 5 : 6);
                                    tcchistories.clear();
                                    tcchistories.addAll(tcchistories1);
                                    adapter.notifyDataSetChanged();
                                }

                                @Override
                                public void onErrorResponse(VolleyError volleyError) {
                                    Log.i("aaa", "onErrorResponse: " + volleyError.getMessage());

                                }
                            }).start();
                } else if (strat.equals("") && !end.equals("")) {
                    VolleyTo volleyTo = new VolleyTo();
                    volleyTo.setUrl("getParkingHistoryByIdAndOutTime")
                            .setJsonObject("parkingid", 1)
                            .setJsonObject("outTime1", "")
                            .setJsonObject("outTime2", end)
                            .setVolleyLo(new VolleyLo() {
                                @Override
                                public void onResponse(JSONObject jsonObject) {
                                    List<Tcchistory> tcchistories1 = new Gson().fromJson(jsonObject.optJSONArray(Utils.Rows).toString()
                                            , new TypeToken<List<Tcchistory>>() {
                                            }.getType());
                                    adapter.setCount((random.nextInt(2) + 1) % 2 == 0 ? 5 : 6);
                                    tcchistories.clear();
                                    tcchistories.addAll(tcchistories1);
                                    adapter.notifyDataSetChanged();
                                }

                                @Override
                                public void onErrorResponse(VolleyError volleyError) {

                                }
                            }).start();
                }
            }
        });

    }

    private void setVolley() {
        VolleyTo volleyTo = new VolleyTo();
        volleyTo.setUrl("getParkingHistoryById")
                .setJsonObject("parkingid", 1)
                .setVolleyLo(new VolleyLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        tcchistories = new Gson().fromJson(jsonObject.optJSONArray(Utils.Rows).toString()
                                , new TypeToken<List<Tcchistory>>() {
                                }.getType());
                        setListViewAdapter();
                    }

                    @Override
                    public void onErrorResponse(VolleyError volleyError) {

                    }
                }).start();
    }

    TccHistoryAdapter adapter;

    Random random = new Random();

    private void setListViewAdapter() {
        adapter = new TccHistoryAdapter(this, tcchistories, (random.nextInt(2) + 1) % 2 == 0 ? 5 : 6);
        listView.setAdapter(adapter);
    }


    private void initView() {
        itemChange = findViewById(R.id.item_change);
        title = findViewById(R.id.title);
        title1 = findViewById(R.id.title1);
        layoutStart = findViewById(R.id.layout_start);
        layoutEnd = findViewById(R.id.layout_end);
        btQuery = findViewById(R.id.bt_query);
        listView = findViewById(R.id.list_view_tcc);
        tvMore = findViewById(R.id.tv_more);
        etStart = findViewById(R.id.et_start);
        etEnd = findViewById(R.id.et_end);
    }
}
