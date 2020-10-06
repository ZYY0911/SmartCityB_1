package com.example.smartcityb_1.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.example.smartcityb_1.AppClient;
import com.example.smartcityb_1.R;
import com.example.smartcityb_1.bean.ActivityInfo;
import com.example.smartcityb_1.bean.HdCommitBean;
import com.example.smartcityb_1.bean.UserInfos;
import com.example.smartcityb_1.bean.UserNum;
import com.example.smartcityb_1.dialog.CommitFragemnt;
import com.example.smartcityb_1.net.VolleyImage;
import com.example.smartcityb_1.net.VolleyLo;
import com.example.smartcityb_1.net.VolleyLoImage;
import com.example.smartcityb_1.net.VolleyTo;
import com.example.smartcityb_1.util.OnUpDate;
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
import java.util.Random;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

/**
 * @Login Name win10
 * @Create by 张瀛煜 on 2020/10/6 at 7:36
 */
public class HdDetailsActivity extends AppCompatActivity {
    private TextView itemChange;
    private TextView title;
    private TextView title1;
    private ImageView ivPhoto;
    private TextView tvMsg;
    private LinearLayout layoutCommit;
    private LinearLayout layoutTuijian;
    private TextView tvBm;
    int id;

    private ActivityInfo activityInfo;
    private TextView tvPlNum;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hd_detalis_layout);
        initView();
        id = getIntent().getIntExtra("index", 0);
        if (id == 0) {
            activityInfo = (ActivityInfo) getIntent().getSerializableExtra("date");
            getAllDate();
        } else {
            VolleyTo volleyTo = new VolleyTo();
            volleyTo.setUrl("getActionById")
                    .setJsonObject("id", id)
                    .setVolleyLo(new VolleyLo() {
                        @Override
                        public void onResponse(JSONObject jsonObject) {
                            activityInfo = new Gson().fromJson(jsonObject.optJSONArray(Utils.Rows).optJSONObject(0).toString()
                                    , ActivityInfo.class);
                            getAllDate();
                        }

                        @Override
                        public void onErrorResponse(VolleyError volleyError) {

                        }
                    }).start();
        }


    }

    private void getAllDate() {
        title.setText(activityInfo.getName());
        VolleyImage volleyImage = new VolleyImage();
        volleyImage.setUrl(activityInfo.getImage())
                .setVolleyLoImage(new VolleyLoImage() {
                    @Override
                    public void onResponse(Bitmap bitmap) {
                        ivPhoto.setImageBitmap(bitmap);
                    }

                    @Override
                    public void onErrorResponse(VolleyError volleyError) {

                    }
                }).start();
        tvMsg.setText(activityInfo.getDescription());
        setVolley_Commit();
        itemChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        tvBm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Utils.showToast("报名成功", HdDetailsActivity.this);
            }
        });
        setVolley_TuiJian();
        title1.setText("评论");
        title1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("aaa", "onClick: " + activityInfo.getId());
                CommitFragemnt fragemnt = new CommitFragemnt(activityInfo.getId() + "", getUserId(AppClient.username));
                fragemnt.show(getSupportFragmentManager(), "aaa");
                fragemnt.setOnUpDate(new OnUpDate() {
                    @Override
                    public void upDate(String name) {
                        if (name.equals("S")) {
                            Utils.showToast("评论成功", HdDetailsActivity.this);
                        } else {
                            Utils.showToast("评论失败", HdDetailsActivity.this);
                        }
                        setVolley_Commit();
                    }
                });

            }
        });
    }


    List<ActivityInfo> activityInfos;

    private void setVolley_TuiJian() {
        VolleyTo volleyTo = new VolleyTo();
        volleyTo.setUrl("getRecommandAction")
                .setVolleyLo(new VolleyLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        activityInfos = new Gson().fromJson(jsonObject.optJSONArray(Utils.Rows).toString()
                                , new TypeToken<List<ActivityInfo>>() {
                                }.getType());
                        setLayout();
                    }

                    @Override
                    public void onErrorResponse(VolleyError volleyError) {

                    }
                }).start();
    }

    private void setLayout() {
        Random random = new Random();
        int num = random.nextInt(3) + 1;
        for (int i = 0; i < activityInfos.size(); i++) {
            ActivityInfo info = activityInfos.get(i);
            if (info.getId() == activityInfo.getId()) {
                activityInfos.remove(i);
            }
        }
        for (int i = 0; i < num; i++) {
            View view = LayoutInflater.from(HdDetailsActivity.this).inflate(R.layout.news_item, null);
            final ViewHolder2 holder = new ViewHolder2();
            holder.itemImage = view.findViewById(R.id.item_image);
            holder.itemTitle = view.findViewById(R.id.item_title);
            holder.itemContext = view.findViewById(R.id.item_context);
            holder.itemMsg = view.findViewById(R.id.item_msg);
            final ActivityInfo activityInfo = activityInfos.get(i);
            VolleyImage volleyImage = new VolleyImage();
            volleyImage.setUrl(activityInfo.getImage())
                    .setVolleyLoImage(new VolleyLoImage() {
                        @Override
                        public void onResponse(Bitmap bitmap) {
                            holder.itemImage.setImageBitmap(bitmap);
                        }

                        @Override
                        public void onErrorResponse(VolleyError volleyError) {

                        }
                    }).start();
            holder.itemTitle.setText(activityInfo.getName());
            holder.itemContext.setText("报名人数：" + activityInfo.getCount() + "\r\n" + "发布日期：" + activityInfo.getTime().replace(".0", ""));
            holder.itemMsg.setText("点赞数：" + activityInfo.getPraiseCount());
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(HdDetailsActivity.this, HdDetailsActivity.class);
                    intent.putExtra("date", activityInfo);
                    startActivity(intent);
                }
            });
            layoutTuijian.addView(view);
        }
    }

    List<HdCommitBean> hdCommitBeans;

    private void setVolley_Commit() {
        VolleyTo volleyTo = new VolleyTo();
        volleyTo.setUrl("getActionCommitById")
                .setJsonObject("id", activityInfo.getId())
                .setVolleyLo(new VolleyLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        hdCommitBeans = new Gson().fromJson(jsonObject.optJSONArray(Utils.Rows).toString()
                                , new TypeToken<List<HdCommitBean>>() {
                                }.getType());
                        tvPlNum.setText("   " + hdCommitBeans.size());
                        setVolley();

                    }

                    @Override
                    public void onErrorResponse(VolleyError volleyError) {

                    }
                }).start();
    }

    private void setCommit_layout() {
        Collections.sort(hdCommitBeans, new Comparator<HdCommitBean>() {
            @Override
            public int compare(HdCommitBean o1, HdCommitBean o2) {
                Date date = null;
                Date date1 = null;
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

                try {
                    date = format.parse(o1.getCommitTime());
                    date1 = format.parse(o2.getCommitTime());
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                return date1.compareTo(date);
            }
        });
        layoutCommit.removeAllViews();
        for (int i = 0; i < hdCommitBeans.size(); i++) {
            View view = LayoutInflater.from(HdDetailsActivity.this).inflate(R.layout.commit_item, null);
            final ViewHolder holder = new ViewHolder();
            holder.itemImage = view.findViewById(R.id.item_image);
            holder.itemName = view.findViewById(R.id.item_name);
            holder.itemMag = view.findViewById(R.id.item_mag);
            holder.itemTime = view.findViewById(R.id.item_time);
            HdCommitBean hdCommitBean = hdCommitBeans.get(i);
            holder.itemMag.setText(hdCommitBean.getCommitContent());
            holder.itemTime.setText(hdCommitBean.getCommitTime());
            holder.itemName.setText(hdCommitBean.getUsername());
            setVolleyInfos(hdCommitBean.getUsername(), holder.itemImage);

            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            view.setLayoutParams(layoutParams);
            layoutCommit.addView(view);
        }
    }

    List<UserNum> userNums;
    UserInfos userInfos;

    private void setVolley() {
        VolleyTo volleyTo = new VolleyTo();
        volleyTo.setUrl("getUsers")
                .setVolleyLo(new VolleyLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        userNums = new Gson().fromJson(jsonObject.optJSONArray(Utils.Rows).toString()
                                , new TypeToken<List<UserNum>>() {
                                }.getType());
                        setCommit_layout();
                    }

                    @Override
                    public void onErrorResponse(VolleyError volleyError) {

                    }
                }).start();
    }


    private String getUserId(String usernmae) {
        for (int i = 0; i < userNums.size(); i++) {
            UserNum userNum = userNums.get(i);
            if (userNum.getUsername().equals(usernmae)) {
                return userNum.getUserid();
            }
        }
        return "1";
    }

    private void setVolleyInfos(String username, final ImageView imageView) {
        VolleyTo volleyTo = new VolleyTo();
        volleyTo.setUrl("getUserInfo")
                .setJsonObject("userid", getUserId(username))
                .setVolleyLo(new VolleyLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        userInfos = new Gson().fromJson(jsonObject.optJSONArray(Utils.Rows).optJSONObject(0).toString()
                                , UserInfos.class);
                        VolleyImage volleyImage = new VolleyImage();
                        volleyImage.setUrl(userInfos.getAvatar())
                                .setVolleyLoImage(new VolleyLoImage() {
                                    @Override
                                    public void onResponse(Bitmap bitmap) {
                                        imageView.setImageBitmap(bitmap);
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


    static class ViewHolder {

        private ImageView itemImage;
        private TextView itemName;
        private TextView itemMag;
        private TextView itemTime;
    }

    static class ViewHolder2 {

        private ImageView itemImage;
        private TextView itemTitle;
        private TextView itemContext;
        private TextView itemMsg;
    }


    private void initView() {
        itemChange = findViewById(R.id.item_change);
        title = findViewById(R.id.title);
        title1 = findViewById(R.id.title1);
        ivPhoto = findViewById(R.id.iv_photo);
        tvMsg = findViewById(R.id.tv_msg);
        layoutCommit = findViewById(R.id.layout_commit);
        layoutTuijian = findViewById(R.id.layout_tuijian);
        tvBm = findViewById(R.id.tv_bm);
        tvPlNum = findViewById(R.id.tv_pl_num);
    }
}
