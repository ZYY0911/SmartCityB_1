package com.example.smartcityb_1.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.example.smartcityb_1.AppClient;
import com.example.smartcityb_1.R;
import com.example.smartcityb_1.adapter.KsChooseAdapter;
import com.example.smartcityb_1.bean.CarInfo;
import com.example.smartcityb_1.bean.DepList;
import com.example.smartcityb_1.bean.Hospitallist;
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
 * @Create by 张瀛煜 on 2020/10/6 at 15:13
 */
public class KSChooseActivity extends AppCompatActivity {
    private ImageView itemChange;
    private TextView title;
    private TextView title1;
    private ListView listView;
    private Hospitallist hospitallist;
    private CarInfo carInfo;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ks_choose_layout);
        AppClient.add2(this);
        initView();
        hospitallist = (Hospitallist) getIntent().getSerializableExtra("date");
        carInfo = (CarInfo) getIntent().getSerializableExtra("carInfos");
        title.setText("门诊科室");
        itemChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        setVolley();

    }

    List<DepList> depLists;

    private void setVolley() {
        VolleyTo volleyTo = new VolleyTo();
        volleyTo.setUrl("deptList")
                .setJsonObject("hospitalId",hospitallist.getHospitalId())
                .setVolleyLo(new VolleyLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        depLists = new Gson().fromJson(jsonObject.optJSONArray(Utils.Rows).toString()
                                , new TypeToken<List<DepList>>() {
                                }.getType());
                        serListview();
                    }

                    @Override
                    public void onErrorResponse(VolleyError volleyError) {

                    }
                }).start();
    }

    private void serListview() {

        listView.setAdapter(new KsChooseAdapter(KSChooseActivity.this, depLists));
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(KSChooseActivity.this, GhYuActivity.class);
                intent.putExtra("info", hospitallist);
                intent.putExtra("carInfos", carInfo);
                Log.i("aaa", "onItemClick: "+depLists.get(position).getDeptName());
                intent.putExtra("Ks", depLists.get(position));
                startActivity(intent);
            }
        });

    }


    private void initView() {
        itemChange = findViewById(R.id.item_change);
        title = findViewById(R.id.title);
        title1 = findViewById(R.id.title1);
        listView = findViewById(R.id.list_view);
    }
}
