package com.example.smartcityb_1.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.android.volley.VolleyError;
import com.example.smartcityb_1.AppClient;
import com.example.smartcityb_1.R;
import com.example.smartcityb_1.bean.Hospitallist;
import com.example.smartcityb_1.bean.ImagesHospital;
import com.example.smartcityb_1.net.VolleyImage;
import com.example.smartcityb_1.net.VolleyLo;
import com.example.smartcityb_1.net.VolleyLoImage;
import com.example.smartcityb_1.net.VolleyTo;
import com.example.smartcityb_1.util.Utils;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

/**
 * @Login Name win10
 * @Create by 张瀛煜 on 2020/10/6 at 11:46
 */
public class HospitalDetails extends AppCompatActivity {
    private Hospitallist hospitallist;
    private ImageView itemChange;
    private TextView title;
    private TextView title1;
    private ViewFlipper viewFlipper;
    private TextView tvMsg;
    private TextView tvOnline;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hosptial_details_layout);
        hospitallist = (Hospitallist) getIntent().getSerializableExtra("index");
        initView();
        title.setText("医院简介");
        AppClient.add2(this);
        setVolley_Image();
        itemChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        tvMsg.setText("     " + hospitallist.getDesc());
        tvOnline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HospitalDetails.this, ONLineActivity.class);
                intent.putExtra("date",hospitallist);
                startActivity(intent);
            }
        });

    }

    List<String> strings;
    ImagesHospital imagesHospital;

    private void setVolley_Image() {
        strings = new ArrayList<>();
        VolleyTo volleyTo = new VolleyTo();
        volleyTo.setUrl("getImagesByHospitalId")
                .setJsonObject("hospitalId", hospitallist.getHospitalId())
                .setVolleyLo(new VolleyLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        imagesHospital = new Gson().fromJson(jsonObject.optJSONArray(Utils.Rows).optJSONObject(0).toString()
                                , ImagesHospital.class);
                        strings.add(imagesHospital.getImage1());
                        strings.add(imagesHospital.getImage2());
                        strings.add(imagesHospital.getImage3());
                        strings.add(imagesHospital.getImage4());
                        for (int i = 0; i < strings.size(); i++) {
                            final ImageView imageView = new ImageView(HospitalDetails.this);
                            imageView.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
                            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                            VolleyImage volleyImage = new VolleyImage();
                            volleyImage.setUrl(strings.get(i))
                                    .setVolleyLoImage(new VolleyLoImage() {
                                        @Override
                                        public void onResponse(Bitmap bitmap) {
                                            imageView.setImageBitmap(bitmap);
                                            viewFlipper.addView(imageView);
                                        }

                                        @Override
                                        public void onErrorResponse(VolleyError volleyError) {

                                        }
                                    }).start();
                        }
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
        viewFlipper = findViewById(R.id.view_flipper);
        tvMsg = findViewById(R.id.tv_msg);
        tvOnline = findViewById(R.id.tv_online);
    }
}
