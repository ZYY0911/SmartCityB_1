package com.example.smartcityb_1.activity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.example.smartcityb_1.R;
import com.example.smartcityb_1.adapter.AllTrafficAdapter;
import com.example.smartcityb_1.bean.MyBean;
import com.example.smartcityb_1.net.VolleyImage;
import com.example.smartcityb_1.net.VolleyLo;
import com.example.smartcityb_1.net.VolleyLoImage;
import com.example.smartcityb_1.net.VolleyTo;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

/**
 * @Login Name win10
 * @Create by 张瀛煜 on 2020/10/2 at 9:55
 */
public class AllTrafficActivity extends AppCompatActivity {
    private ImageView itemChange;
    private TextView title;
    private TextView title1;
    private ListView listView;
    private ImageView ivPhoto;
    private List<MyBean> strings;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.alltraffic_layout);
        initView();
        setVolley();
        title.setText("地铁总览");
        itemChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void setVolley() {
        strings = new ArrayList<>();
        VolleyTo volleyTo = new VolleyTo();
        volleyTo.setUrl("getAllSubways")
                .setVolleyLo(new VolleyLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        strings = new Gson().fromJson(jsonObject.optJSONArray("ROWS_DETAIL").toString()
                                , new TypeToken<List<MyBean>>() {
                                }.getType());
                        setLsitView();
                    }

                    @Override
                    public void onErrorResponse(VolleyError volleyError) {

                    }
                }).start();

    }

    private void setLsitView() {
        listView.setAdapter(new AllTrafficAdapter(this, strings));
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                getImage(strings.get(position).getId());
            }
        });
        getImage(1);

    }


    private void getImage(int id) {
        VolleyTo volleyTo = new VolleyTo();
        volleyTo.setUrl("getSubwaysImage")
                .setJsonObject("id", id)
                .setVolleyLo(new VolleyLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        VolleyImage volleyImage = new VolleyImage();
                        volleyImage.setUrl(jsonObject.optString("image"))
                                .setVolleyLoImage(new VolleyLoImage() {
                                    @Override
                                    public void onResponse(Bitmap bitmap) {
                                        ivPhoto.setImageBitmap(bitmap);
                                    }

                                    @Override
                                    public void onErrorResponse(VolleyError volleyError) {

                                    }
                                }).start();
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
        ivPhoto = findViewById(R.id.iv_photo);
    }
}
