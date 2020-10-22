package com.example.smartcityb_1.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.example.smartcityb_1.AppClient;
import com.example.smartcityb_1.R;
import com.example.smartcityb_1.bean.BusTitle;
import com.example.smartcityb_1.net.VolleyLo;
import com.example.smartcityb_1.net.VolleyTo;

import org.json.JSONObject;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

/**
 * @Login Name win10
 * @Create by 张瀛煜 on 2020/10/8 at 15:19
 */
public class BusDetailsActivity4 extends AppCompatActivity {
    private ImageView itemChange;
    private TextView title;
    private TextView title1;
    private TextView tvLine;
    private TextView tvName;
    private TextView tvTel;
    private TextView tvUp;
    private TextView tvRl;
    private TextView tvNext;
    private TextView tvDown;
    BusTitle busTitle;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bus_details_layout4);
        AppClient.add2(this);
        initView();
        title.setText("定制班车");
        itemChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        //    Intent intent = new Intent(BusDetailsActivity3.this, BusDetailsActivity4.class);
        //                intent.putExtra("date", busTitle);
        //                intent.putExtra("time", getIntent().getStringExtra("time"));
        //                intent.putExtra("name", etName.getText().toString());
        //                intent.putExtra("tel", etTel.getText().toString());
        //                intent.putExtra("up", etUp.getSelectedItem().toString());
        //                intent.putExtra("down", etDown.getSelectedItem().toString());
        //                startActivity(intent);
        busTitle = (BusTitle) getIntent().getSerializableExtra("date");
        tvName.setText("乘客姓名：" + getIntent().getStringExtra("name"));
        tvTel.setText("手机号码：" + getIntent().getStringExtra("tel"));
        tvRl.setText("乘车日期：" + getIntent().getStringExtra("time"));
        tvUp.setText("上车地点：" + getIntent().getStringExtra("up"));
        tvDown.setText("下车地点：" + getIntent().getStringExtra("down"));
        tvNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                VolleyTo volleyTo = new VolleyTo();
                volleyTo.setUrl("setOrderBus")
                        //{busid:1,"name":"abc","phone":"1234","upsite":"火车站","downsite":"阳光新天地","traveltime":"2020-10-5,2020-10-6","isPay":"N"}
                        .setJsonObject("busid", busTitle.getBusid())
                        .setJsonObject("name", getIntent().getStringExtra("name"))
                        .setJsonObject("phone", getIntent().getStringExtra("tel"))
                        .setJsonObject("upsite", getIntent().getStringExtra("up"))
                        .setJsonObject("downsite", getIntent().getStringExtra("down"))
                        .setJsonObject("traveltime", getIntent().getStringExtra("time"))
                        .setJsonObject("isPay", "N")
                        .setVolleyLo(new VolleyLo() {
                            @Override
                            public void onResponse(JSONObject jsonObject) {
                                if (jsonObject.optString("RESULT").equals("S")) {
                                    Toast.makeText(BusDetailsActivity4.this, "提交成功", Toast.LENGTH_SHORT).show();
                                    AppClient.finallAll2();
                                } else {
                                    Toast.makeText(BusDetailsActivity4.this, "提交失败", Toast.LENGTH_SHORT).show();

                                }

                            }

                            @Override
                            public void onErrorResponse(VolleyError volleyError) {
                                Toast.makeText(BusDetailsActivity4.this, "提交失败", Toast.LENGTH_SHORT).show();

                            }
                        }).start();

            }
        });
    }

    private void initView() {
        itemChange = findViewById(R.id.item_change);
        title = findViewById(R.id.title);
        title1 = findViewById(R.id.title1);
        tvLine = findViewById(R.id.tv_line);
        tvName = findViewById(R.id.tv_name);
        tvTel = findViewById(R.id.tv_tel);
        tvUp = findViewById(R.id.tv_up);
        tvRl = findViewById(R.id.tv_rl);
        tvNext = findViewById(R.id.tv_next);
        tvDown = findViewById(R.id.tv_down);
    }
}
