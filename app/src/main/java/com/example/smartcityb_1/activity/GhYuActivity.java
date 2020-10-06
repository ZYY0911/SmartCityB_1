package com.example.smartcityb_1.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.example.smartcityb_1.AppClient;
import com.example.smartcityb_1.R;
import com.example.smartcityb_1.adapter.GhAdapter;


import com.example.smartcityb_1.bean.CarInfo;
import com.example.smartcityb_1.bean.DepList;
import com.example.smartcityb_1.bean.DutByDempart;
import com.example.smartcityb_1.bean.Hospitallist;
import com.example.smartcityb_1.net.VolleyLo;
import com.example.smartcityb_1.net.VolleyTo;
import com.example.smartcityb_1.util.OnUpDate2;
import com.example.smartcityb_1.util.Utils;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONObject;

import java.util.List;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

/**
 * @Login Name win10
 * @Create by 张瀛煜 on 2020/10/6 at 15:39
 */
public class GhYuActivity extends AppCompatActivity {
    private ImageView itemChange;
    private TextView title;
    private TextView title1;
    private TextView tvZj;
    private TextView tvPt;
    private TextView tvZjDate;
    private LinearLayout layoutPt;
    private ListView listView;
    private Hospitallist hospitallist;
    private CarInfo carInfo;
    private DepList depList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gh_layout);
        initView();
        AppClient.add2(this);
        itemChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        title.setText("预约挂号");
//        intent.putExtra("info", hospitallist);
//        intent.putExtra("carInfos", carInfo);
//        intent.putExtra("Ks", depLists.get(position));
        hospitallist = (Hospitallist) getIntent().getSerializableExtra("info");
        carInfo = (CarInfo) getIntent().getSerializableExtra("carInfos");
        depList = (DepList) getIntent().getSerializableExtra("Ks");
        tvZj.setTextColor(Color.RED);
        tvZj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvZj.setTextColor(Color.RED);
                tvPt.setTextColor(Color.BLACK);
                tvZjDate.setVisibility(View.VISIBLE);
                layoutPt.setVisibility(View.GONE);
            }
        });
        tvPt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvZj.setTextColor(Color.BLACK);
                tvPt.setTextColor(Color.RED);
                tvZjDate.setVisibility(View.GONE);
                layoutPt.setVisibility(View.VISIBLE);
            }
        });
        setVoley();

    }

    List<DutByDempart> dutByDemparts;

    private void setVoley() {
        VolleyTo volleyTo = new VolleyTo();
        volleyTo.setUrl("getDutyByDepartmentId")
                .setJsonObject("hospitalId", hospitallist.getHospitalId())
                .setJsonObject("departmentId", depList.getDeptId())
                .setVolleyLo(new VolleyLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        dutByDemparts = new Gson().fromJson(jsonObject.optJSONArray(Utils.Rows).toString()
                                , new TypeToken<List<DutByDempart>>() {
                                }.getType());
                        Log.i("aaa", "onResponse: "+dutByDemparts.size());
                        GhAdapter adapter = new GhAdapter(GhYuActivity.this, dutByDemparts, depList.getDeptName());
                        listView.setAdapter(adapter);
                        adapter.setOnUpDate2(new OnUpDate2() {
                            @Override
                            public void upDate(String name, int index) {
                                //{"pid":"371402199902041133","name":"赵子涵",
                                // "phone":"13505347777","doctorId":2,"appTime":"2020-10-2 周四 下午14：00"}
                                Intent intent = new Intent(GhYuActivity.this, YyghSuccsssFulActivity.class);
                                intent.putExtra("carInfos", carInfo);
                                intent.putExtra("doctor", dutByDemparts.get(index));
                                intent.putExtra("ks", depList.getDeptName());
                                startActivity(intent);
                            }
                        });
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
        tvZj = findViewById(R.id.tv_zj);
        tvPt = findViewById(R.id.tv_pt);
        tvZjDate = findViewById(R.id.tv_zj_date);
        layoutPt = findViewById(R.id.layout_pt);
        listView = findViewById(R.id.list_view);
    }
}
