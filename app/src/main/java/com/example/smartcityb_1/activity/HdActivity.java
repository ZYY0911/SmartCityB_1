package com.example.smartcityb_1.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.android.volley.VolleyError;
import com.example.smartcityb_1.R;
import com.example.smartcityb_1.adapter.HdAdapter;
import com.example.smartcityb_1.bean.ActivityImage;
import com.example.smartcityb_1.bean.ActivityInfo;
import com.example.smartcityb_1.bean.ActivityType;
import com.example.smartcityb_1.net.VolleyImage;
import com.example.smartcityb_1.net.VolleyLo;
import com.example.smartcityb_1.net.VolleyLoImage;
import com.example.smartcityb_1.net.VolleyTo;
import com.example.smartcityb_1.util.Utils;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

/**
 * @Login Name win10
 * @Create by 张瀛煜 on 2020/10/5 at 21:26
 */
public class HdActivity extends AppCompatActivity {
    private ImageView itemChange;
    private TextView title;
    private TextView title1;
    private ViewFlipper viewFlipper;
    private LinearLayout layoutNew;
    private ListView listView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hd_layout);
        initView();
        title.setText("活动");
        itemChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        setVolley_Image();
        setVolley_Type();
    }

    List<ActivityType> activityTypes;

    private void setVolley_Type() {
        VolleyTo volleyTo = new VolleyTo();
        volleyTo.setUrl("getAllActionType")
                .setVolleyLo(new VolleyLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        activityTypes = new Gson().fromJson(jsonObject.optJSONArray(Utils.Rows).toString()
                                , new TypeToken<List<ActivityType>>() {
                                }.getType());
                        for (int i = 0; i < activityTypes.size(); i++) {
                            final TextView textView = new TextView(HdActivity.this);
                            textView.setText(activityTypes.get(i).getTypename());
                            textView.setTextColor(Color.BLACK);
                            textView.setTextSize(20);
                            textView.setGravity(Gravity.CENTER);
                            textView.setBackgroundResource(R.drawable.text_no_line);
                            if (i == 0) {
                                textView.setTextColor(getResources().getColor(R.color.colorPrimary));
                                textView.setBackgroundResource(R.drawable.text_line);
                            }
                            textView.setPadding(10,0,10,0);

                            final int finalJ = i;
                            textView.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    for (int i = 0; i < layoutNew.getChildCount(); i++) {
                                        TextView textView = (TextView) layoutNew.getChildAt(i);
                                        if (i == finalJ) {
                                            textView.setTextColor(getResources().getColor(R.color.colorPrimary));
                                            textView.setBackgroundResource(R.drawable.text_line);
                                        } else {
                                            textView.setTextColor(Color.BLACK);
                                            textView.setBackgroundResource(R.drawable.text_no_line);
                                        }
                                    }
                                    String type = textView.getText().toString();
                                    setLayoutType(activityTypes.get(finalJ));
                                }
                            });
                            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT);
                            layoutParams.setMargins(40, 0, 40, 0);
                            textView.setLayoutParams(layoutParams);
                            layoutNew.addView(textView);
                        }
                        setLayoutType(activityTypes.get(0));
                    }

                    @Override
                    public void onErrorResponse(VolleyError volleyError) {

                    }
                }).start();
    }

    List<ActivityInfo> activityInfos;

    private void setLayoutType(ActivityType type) {
        VolleyTo volleyTo = new VolleyTo();
        volleyTo.setUrl("getActionsByType")
                .setJsonObject("typeid", type.getId())
                .setVolleyLo(new VolleyLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        activityInfos = new Gson().fromJson(jsonObject.optJSONArray(Utils.Rows).toString()
                                , new TypeToken<List<ActivityInfo>>() {
                                }.getType());
                        Collections.sort(activityInfos, new Comparator<ActivityInfo>() {
                            @Override
                            public int compare(ActivityInfo o1, ActivityInfo o2) {
                                Date date = null;
                                Date date1 = null;
                                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

                                try {
                                    date = format.parse(o1.getTime());
                                    date1 = format.parse(o2.getTime());
                                } catch (ParseException e) {
                                    e.printStackTrace();
                                }
                                return date1.compareTo(date);
                            }
                        });
                        listView.setAdapter(new HdAdapter(HdActivity.this, activityInfos));
                        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                Intent intent = new Intent(HdActivity.this, HdDetailsActivity.class);
                                intent.putExtra("date", activityInfos.get(position));
                                startActivity(intent);
                            }
                        });

                    }

                    @Override
                    public void onErrorResponse(VolleyError volleyError) {

                    }
                }).start();

    }


    List<ActivityImage> activityImages;

    private void setVolley_Image() {
        VolleyTo volleyTo = new VolleyTo();
        volleyTo.setUrl("getActionImages")
                .setVolleyLo(new VolleyLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        activityImages = new Gson().fromJson(jsonObject.optJSONArray(Utils.Rows).toString()
                                , new TypeToken<List<ActivityImage>>() {
                                }.getType());
                        setViewFilpper();
                    }

                    @Override
                    public void onErrorResponse(VolleyError volleyError) {

                    }
                }).start();
    }

    private void setViewFilpper() {
        for (int i = 0; i < activityImages.size(); i++) {
            final ImageView imageView = new ImageView(HdActivity.this);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            VolleyImage volleyImage = new VolleyImage();
            final ActivityImage activityImage = activityImages.get(i);
            volleyImage.setUrl(activityImage.getImage())
                    .setVolleyLoImage(new VolleyLoImage() {
                        @Override
                        public void onResponse(Bitmap bitmap) {
                            imageView.setImageBitmap(bitmap);
                        }

                        @Override
                        public void onErrorResponse(VolleyError volleyError) {

                        }
                    }).start();
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(HdActivity.this, HdDetailsActivity.class);
                    intent.putExtra("index", activityImage.getId());
                    startActivity(intent);
                }
            });
            viewFlipper.addView(imageView);
        }
    }

    private void initView() {
        itemChange = findViewById(R.id.item_change);
        title = findViewById(R.id.title);
        title1 = findViewById(R.id.title1);
        viewFlipper = findViewById(R.id.view_flipper);
        layoutNew = findViewById(R.id.layout_new);
        listView = findViewById(R.id.list_view);
    }
}
