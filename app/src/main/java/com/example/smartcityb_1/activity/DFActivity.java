package com.example.smartcityb_1.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.example.smartcityb_1.R;
import com.example.smartcityb_1.bean.HoDetails;
import com.example.smartcityb_1.bean.UserInfos;
import com.example.smartcityb_1.net.VolleyLo;
import com.example.smartcityb_1.net.VolleyTo;
import com.example.smartcityb_1.util.Utils;
import com.google.gson.Gson;

import org.json.JSONObject;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

/**
 * @Login Name win10
 * @Create by 张瀛煜 on 2020/10/9 at 8:02
 */
public class DFActivity extends AppCompatActivity {
    private ImageView itemChange;
    private TextView title;
    private TextView title1;
    private TextView tvText;
    private HoDetails hoDetails;
    private UserInfos userInfos;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.df_layout);
        initView();
        title.setText("电费");
        itemChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        hoDetails = (HoDetails) getIntent().getSerializableExtra("date");
        //缴费单位：XXXX
        //缴费户号：XXXX
        //户名：XXXX
        //住址：XXXXX
        //当前可余额：XXXX
        //当前欠费余额：XXXX

        VolleyTo volleyTo = new VolleyTo();
        volleyTo.setUrl("getUserInfo")
                .setJsonObject("userid", hoDetails.getUserid())
//                .setDialog(this)
                .setVolleyLo(new VolleyLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        userInfos = new Gson().fromJson(jsonObject.optJSONArray(Utils.Row).optJSONObject(0).toString()
                                , UserInfos.class);

                        int baleance = hoDetails.getBanlance();
                        int cost = hoDetails.getCost();
                        int now = baleance - cost;
                        tvText.setText("缴费单位：" + hoDetails.getUnit() + "\n缴费户号：" + hoDetails.getAccountId()
                                + "\n户名：" + userInfos.getName() + "\n地址：" + hoDetails.getAccountAddress() + "\n当前可用余额：" + baleance + "元\n" +
                                "当前欠费余额：" + (now > 0 ? "无欠费" : now + "元"));
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
        tvText = findViewById(R.id.tv_text);
    }
}
