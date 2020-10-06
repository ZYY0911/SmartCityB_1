package com.example.smartcityb_1.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.example.smartcityb_1.AppClient;
import com.example.smartcityb_1.R;
import com.example.smartcityb_1.bean.CarInfo;
import com.example.smartcityb_1.bean.DutByDempart;
import com.example.smartcityb_1.net.VolleyLo;
import com.example.smartcityb_1.net.VolleyTo;
import com.example.smartcityb_1.util.Utils;

import org.json.JSONObject;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

/**
 * @Login Name win10
 * @Create by 张瀛煜 on 2020/10/6 at 15:56
 */
public class YyghSuccsssFulActivity extends AppCompatActivity {
    private CarInfo carInfo;
    private DutByDempart dutByDempart;
    private ImageView itemChange;
    private TextView title;
    private TextView title1;
    private TextView tvOnline;
    private TextView tvMsg;
    private String ks;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.yycg_successful_layout);
        AppClient.add2(this);
        initView();
        ks = getIntent().getStringExtra("ks");
        carInfo = (CarInfo) getIntent().getSerializableExtra("carInfos");
        dutByDempart = (DutByDempart) getIntent().getSerializableExtra("doctor");
        VolleyTo volleyTo = new VolleyTo();
        volleyTo.setUrl("appointment")
                //{"pid":"371402199902041133","name":"赵子涵","phone":"13505347777","doctorId":2,"appTime":"2020-10-2 周四 下午14：00"}
                .setJsonObject("pid", carInfo.getID())
                .setJsonObject("name", carInfo.getName())
                .setJsonObject("phone", carInfo.getTel())
                .setJsonObject("doctorId", dutByDempart.getDoctorId())
                .setJsonObject("appTime", dutByDempart.getTime())
                .setVolleyLo(new VolleyLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        if (jsonObject.optString("RESULT").equals("S")) {
                            tvMsg.setText("预约结果"+"\r\n\r\n"+
                                    "预约科室：" + ks + "\r\n"
                                    + "门诊类型：" + dutByDempart.getType() + "\r\n"
                                    + "预约时间：" + jsonObject.optJSONObject("data")
                                    .optString("appTime"));
                        } else {
                            Utils.showToast("预约失败", YyghSuccsssFulActivity.this);
                        }
                    }

                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        Utils.showToast("预约失败", YyghSuccsssFulActivity.this);

                    }
                }).start();

        itemChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        title.setText("预约挂号");

        tvOnline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppClient.finallAll2();
            }
        });

    }

    private void initView() {
        itemChange = findViewById(R.id.item_change);
        title = findViewById(R.id.title);
        title1 = findViewById(R.id.title1);
        tvOnline = findViewById(R.id.tv_online);
        tvMsg = findViewById(R.id.tv_msg);
    }
}
