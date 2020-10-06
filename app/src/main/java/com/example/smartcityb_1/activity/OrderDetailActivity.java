package com.example.smartcityb_1.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.example.smartcityb_1.R;
import com.example.smartcityb_1.adapter.OrderDetailsAdatper;
import com.example.smartcityb_1.bean.OrderDetails;
import com.example.smartcityb_1.bean.OrderTitle;
import com.example.smartcityb_1.net.VolleyLo;
import com.example.smartcityb_1.net.VolleyTo;
import com.example.smartcityb_1.util.Utils;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONObject;

import java.util.List;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

/**
 * @Login Name win10
 * @Create by 张瀛煜 on 2020/10/5 at 20:53
 */
public class OrderDetailActivity extends AppCompatActivity {
    OrderTitle orderTitle;
    private TextView itemChange;
    private TextView title;
    private TextView title1;
    private ListView listView;
    private TextView tvDdh;
    private TextView tvTime;
    private TextView tvMoney;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.order_details_layout);
        orderTitle = (OrderTitle) getIntent().getSerializableExtra("date");
        initView();
        title.setText("订单详情");
        itemChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        setVolley();
        tvTime.setText("交易日期：" + orderTitle.getDate().replace(".0",""));
        tvDdh.setText("订单号：" + orderTitle.getId());
        tvMoney.setText("总金额：" + orderTitle.getCost());
    }

    List<OrderDetails> orderDetails;

    private void setVolley() {
        VolleyTo volleyTo = new VolleyTo();
        volleyTo.setUrl("getOrderById")
                .setJsonObject("id", orderTitle.getId())
                .setVolleyLo(new VolleyLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        orderDetails = new Gson().fromJson(jsonObject.optJSONArray(Utils.Rows).toString()
                                , new TypeToken<List<OrderDetails>>() {
                                }.getType());
                        listView.setAdapter(new OrderDetailsAdatper(OrderDetailActivity.this, orderDetails));

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
        listView = findViewById(R.id.list_view);
        tvDdh = findViewById(R.id.tv_ddh);
        tvTime = findViewById(R.id.tv_time);
        tvMoney = findViewById(R.id.tv_money);
    }
}
