package com.example.smartcityb_1.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.example.smartcityb_1.AppClient;
import com.example.smartcityb_1.R;
import com.example.smartcityb_1.adapter.HCarAdapter;
import com.example.smartcityb_1.adapter.KsChooseAdapter;
import com.example.smartcityb_1.bean.CarInfo;
import com.example.smartcityb_1.bean.UserNum;
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
 * @Create by 张瀛煜 on 2020/10/6 at 14:38
 */
public class ONLineActivity extends AppCompatActivity {
    private ImageView itemChange;
    private TextView title;
    private TextView title1;
    private ListView listView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.online_layout);AppClient.add2(this);
        initView();
        title.setText("就诊卡");
        itemChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        setVolley();

    }

    List<UserNum> userNums;

    private void setVolley() {
        VolleyTo volleyTo = new VolleyTo();
        volleyTo.setUrl("getUsers")
                .setVolleyLo(new VolleyLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        userNums = new Gson().fromJson(jsonObject.optJSONArray(Utils.Rows).toString()
                                , new TypeToken<List<UserNum>>() {
                                }.getType());
                        setVolley_MyInfo();
                    }

                    @Override
                    public void onErrorResponse(VolleyError volleyError) {

                    }
                }).start();
    }

    String ID = "";

    private void setVolley_MyInfo() {
        VolleyTo volleyTo = new VolleyTo();
        volleyTo.setUrl("getUserInfo")
                .setJsonObject("userid", getUserName(AppClient.username))
                .setVolleyLo(new VolleyLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        ID = jsonObject.optJSONArray(Utils.Rows).optJSONObject(0).optString("id");
                        getVolley_Card();
                    }

                    @Override
                    public void onErrorResponse(VolleyError volleyError) {

                    }
                }).start();

    }

    List<CarInfo> carInfos;

    private void getVolley_Card() {
        VolleyTo volleyTo = new VolleyTo();
        volleyTo.setUrl("showCaseById")
                .setJsonObject("ID", ID)
                .setVolleyLo(new VolleyLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        carInfos = new Gson().fromJson(jsonObject.optJSONArray(Utils.Rows).toString()
                                , new TypeToken<List<CarInfo>>() {
                                }.getType());
                        setCarList();
                    }

                    @Override
                    public void onErrorResponse(VolleyError volleyError) {

                    }
                }).start();
    }

    HCarAdapter adapter;

    private void setCarList() {

        adapter = new HCarAdapter(ONLineActivity.this, carInfos);
        listView.setAdapter(adapter);
        adapter.setOnUpDate2(new OnUpDate2() {
            @Override
            public void upDate(String name, int index) {
                if (name.equals("1")) {
                    Intent intent = new Intent(ONLineActivity.this, KSChooseActivity.class);
                    intent.putExtra("date", getIntent().getSerializableExtra("date"));
                    intent.putExtra("carInfos", carInfos.get(index));
                    startActivity(intent);
                } else {
                    startActivity(new Intent(ONLineActivity.this, CreatNewCarActivity.class));
                }
            }
        });


    }

    private String getUserName(String username) {
        for (int i = 0; i < userNums.size(); i++) {
            UserNum userNum = userNums.get(i);
            if (userNum.getUsername().equals(username)) {
                return userNum.getUserid();
            }
        }
        return "1";
    }

    private void initView() {
        itemChange = findViewById(R.id.item_change);
        title = findViewById(R.id.title);
        title1 = findViewById(R.id.title1);
        listView = findViewById(R.id.list_view);
    }
}
