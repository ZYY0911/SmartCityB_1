package com.example.smartcityb_1.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.example.smartcityb_1.AppClient;
import com.example.smartcityb_1.R;
import com.example.smartcityb_1.bean.CommitList;
import com.example.smartcityb_1.bean.NewsList;
import com.example.smartcityb_1.bean.UserInfos;
import com.example.smartcityb_1.bean.UserNum;
import com.example.smartcityb_1.net.VolleyImage;
import com.example.smartcityb_1.net.VolleyLo;
import com.example.smartcityb_1.net.VolleyLoImage;
import com.example.smartcityb_1.net.VolleyTo;
import com.example.smartcityb_1.util.Utils;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

/**
 * @Login Name win10
 * @Create by 张瀛煜 on 2020/10/2 at 15:54
 */
public class ReadNewActivity extends AppCompatActivity {
    private ImageView itemChange;
    private TextView title;
    private TextView title1;
    private TextView tvContent;
    private TextView tvCommitNum;
    private LinearLayout layoutCommit;
    private LinearLayout layoutTuijian;
    private EditText etSearch;
    private NewsList newsList;
    private ImageView ivPhoto;
    private List<CommitList> commitLists;
    private int id;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.read_new_layout);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        initView();
        itemChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        id = getIntent().getIntExtra("id", 0);
        Log.i("bbb", "onCreate: "+id);
        if (id != 0) {
            VolleyTo volleyTo = new VolleyTo();
            volleyTo.setUrl("getNEWSContent")
                    .setJsonObject("newsid", id)
                    .setVolleyLo(new VolleyLo() {
                        @Override
                        public void onResponse(JSONObject jsonObject) {
                            newsList = new Gson().fromJson(jsonObject.optJSONArray("ROWS_DETAIL").optJSONObject(0).toString()
                                    , NewsList.class);
                            setShow();
                        }

                        @Override
                        public void onErrorResponse(VolleyError volleyError) {

                        }
                    }).start();
        } else {
            newsList = (NewsList) getIntent().getSerializableExtra("info");
            setShow();
        }

    }

    private void setShow() {
        setVolley();
        title.setText(newsList.getTitle());
        VolleyImage volleyImage = new VolleyImage();
        volleyImage.setUrl(newsList.getPicture())
                .setVolleyLoImage(new VolleyLoImage() {
                    @Override
                    public void onResponse(Bitmap bitmap) {
                        ivPhoto.setImageBitmap(bitmap);
                    }

                    @Override
                    public void onErrorResponse(VolleyError volleyError) {

                    }
                }).start();
        tvContent.setText("    " + newsList.getAbstractX());
        setVolley_NewsList();
        etSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (null != event && KeyEvent.KEYCODE_ENTER == event.getKeyCode()) {
                    switch (event.getAction()) {
                        case KeyEvent.ACTION_UP:
                            VolleyTo volleyTo = new VolleyTo();
                            volleyTo.setUrl("publicComit")
                                    //{"username":"user1","newsid":"1","commit":"评论","commitTime":"yyyy.MM.dd HH:mm:ss"}
                                    .setJsonObject("username", AppClient.username)
                                    .setJsonObject("newsid", newsList.getNewsid())
                                    .setJsonObject("commit", etSearch.getText().toString())
                                    .setJsonObject("commitTime", Utils.simpleDate("yyyy.MM.dd HH:mm:ss", new Date()))
                                    .setVolleyLo(new VolleyLo() {
                                        @Override
                                        public void onResponse(JSONObject jsonObject) {
                                            setVolley_Commit();
                                            etSearch.setText("");
                                        }

                                        @Override
                                        public void onErrorResponse(VolleyError volleyError) {

                                        }
                                    }).start();
                            return true;
                        default:
                            return true;
                    }
                }
                return false;
            }
        });
    }

    static class ViewHolder {
        private ImageView itemImage;
        private TextView itemName;
        private TextView itemMag;
        private TextView itemTime;


    }


    List<UserNum> userNums;

    private void setVolley() {
        VolleyTo volleyTo = new VolleyTo();
        volleyTo.setUrl("getUsers")
                .setVolleyLo(new VolleyLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        userNums = new Gson().fromJson(jsonObject.optJSONArray("ROWS_DETAIL").toString()
                                , new TypeToken<List<UserNum>>() {
                                }.getType());
                        setVolley_Commit();
                    }

                    @Override
                    public void onErrorResponse(VolleyError volleyError) {

                    }
                }).start();
    }

    UserInfos userInfos;

    private void setVolley_UserInfo() {
        VolleyTo volleyTo = new VolleyTo();
        volleyTo.setUrl("getUserInfo")
                .setJsonObject("userid", getUserName(AppClient.username))
                .setVolleyLo(new VolleyLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        userInfos = new Gson().fromJson(jsonObject.optJSONArray(Utils.Rows).optJSONObject(0).toString(), UserInfos.class);
                    }

                    @Override
                    public void onErrorResponse(VolleyError volleyError) {

                    }
                }).start();
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

    private void setVolley_Commit() {
        VolleyTo volleyTo = new VolleyTo();
        volleyTo.setUrl("getCommitById")
                .setJsonObject("id", newsList.getNewsid())
                .setVolleyLo(new VolleyLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        commitLists = new Gson().fromJson(jsonObject.optJSONArray("ROWS_DETAIL").toString()
                                , new TypeToken<List<CommitList>>() {
                                }.getType());
                        setCommitView();
                    }

                    @Override
                    public void onErrorResponse(VolleyError volleyError) {

                    }
                }).start();
    }

    private void setCommitView() {
        layoutCommit.removeAllViews();
        tvCommitNum.setText("    " + commitLists.size() + "");
        for (int i = 0; i < commitLists.size(); i++) {
            View view = LayoutInflater.from(ReadNewActivity.this).inflate(R.layout.commit_item, null);
            final ViewHolder holder = new ViewHolder();
            holder.itemImage = view.findViewById(R.id.item_image);
            holder.itemName = view.findViewById(R.id.item_name);
            holder.itemMag = view.findViewById(R.id.item_mag);
            holder.itemTime = view.findViewById(R.id.item_time);
            CommitList commitList = commitLists.get(i);
            holder.itemMag.setText(commitList.getCommit());
            holder.itemTime.setText(commitList.getCommitTime());
            VolleyTo volleyTo = new VolleyTo();
            volleyTo.setUrl("getUserInfo")
                    .setJsonObject("userid", getUserName(commitList.getReviewer()))
                    .setVolleyLo(new VolleyLo() {
                        @Override
                        public void onResponse(JSONObject jsonObject) {
                            userInfos = new Gson().fromJson(jsonObject.optJSONArray(Utils.Rows).optJSONObject(0).toString(), UserInfos.class);
                            holder.itemName.setText(userInfos.getName());
                            VolleyImage volleyImage = new VolleyImage();
                            volleyImage.setUrl(userInfos.getAvatar())
                                    .setVolleyLoImage(new VolleyLoImage() {
                                        @Override
                                        public void onResponse(Bitmap bitmap) {
                                            holder.itemImage.setImageBitmap(bitmap);
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
//            UserInfos user = getUserName(commitList.getReviewer());
            holder.itemName.setText(commitList.getReviewer());

            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            view.setLayoutParams(layoutParams);
            layoutCommit.addView(view);

        }
    }

    List<NewsList> newsLists;

    private void setVolley_NewsList() {
        VolleyTo volleyTo = new VolleyTo();
        volleyTo.setUrl("getNEWsList")
                .setVolleyLo(new VolleyLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        newsLists = new Gson().fromJson(jsonObject.optJSONArray("ROWS_DETAIL").toString()
                                , new TypeToken<List<NewsList>>() {
                                }.getType());
                        setVolley_Recommend();
                    }

                    @Override
                    public void onErrorResponse(VolleyError volleyError) {

                    }
                }).start();
    }

    List<Integer> recommendNews;

    private void setVolley_Recommend() {
        recommendNews = new ArrayList<>();
        VolleyTo volleyTo = new VolleyTo();
        volleyTo.setUrl("getRecommend")
                .setVolleyLo(new VolleyLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        JSONArray jsonArray = jsonObject.optJSONArray(Utils.Rows);
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject jsonObject1 = jsonArray.optJSONObject(i);
                            recommendNews.add(Integer.parseInt(jsonObject1.optString("newsid")));
                        }
                        setNewType();
                    }

                    @Override
                    public void onErrorResponse(VolleyError volleyError) {

                    }
                }).start();
    }

    private void setNewType() {
        final List<NewsList> newsListList = new ArrayList<>();
        for (int j = 0; j < newsLists.size(); j++) {
            NewsList newsList1 = newsLists.get(j);
            if (recommendNews.contains(Integer.parseInt(newsList1.getNewsid()))) {
                newsListList.add(newsList1);
            }
        }
        layoutTuijian.removeAllViews();
        Random random = new Random();
        int id = Integer.parseInt(newsList.getNewsid());
        for (int j = 0; j < random.nextInt(3) + 1; j++) {
            final NewsList newsList = newsListList.get(j);
            View view = LayoutInflater.from(this).inflate(R.layout.news_item, null);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
            view.setLayoutParams(layoutParams);
            final ViewHolderItem holder = new ViewHolderItem();
            holder.itemImage = view.findViewById(R.id.item_image);
            holder.itemTitle = view.findViewById(R.id.item_title);
            holder.itemContext = view.findViewById(R.id.item_context);
            holder.itemMsg = view.findViewById(R.id.item_msg);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(ReadNewActivity.this, ReadNewActivity.class);
                    intent.putExtra("info", newsList);
                    startActivity(intent);
                }
            });
            VolleyImage volleyImage = new VolleyImage();
            volleyImage.setUrl(newsList.getPicture())
                    .setVolleyLoImage(new VolleyLoImage() {
                        @Override
                        public void onResponse(Bitmap bitmap) {
                            holder.itemImage.setImageBitmap(bitmap);
                        }

                        @Override
                        public void onErrorResponse(VolleyError volleyError) {

                        }
                    }).start();
            holder.itemTitle.setText(newsList.getTitle());
            holder.itemContext.setText(newsList.getAbstractX());
            VolleyTo volleyTo = new VolleyTo();
            volleyTo.setUrl("getCommitById")
                    .setJsonObject("id", newsList.getNewsid())
                    .setVolleyLo(new VolleyLo() {
                        @Override
                        public void onResponse(JSONObject jsonObject) {
                            JSONArray jsonArray = jsonObject.optJSONArray("ROWS_DETAIL");
                            int all = jsonArray.length();
                            setVolley_Public(all, holder, Integer.parseInt(newsList.getNewsid()));
                        }

                        @Override
                        public void onErrorResponse(VolleyError volleyError) {

                        }
                    }).start();

            layoutTuijian.addView(view);
        }

    }

    private void setVolley_Public(final int all, final ViewHolderItem holder, int id) {
        VolleyTo volleyTo = new VolleyTo();
        volleyTo.setUrl("getNewsInfoById")
                .setJsonObject("newsid", id)
                .setVolleyLo(new VolleyLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        JSONObject jsonArray = jsonObject.optJSONArray("ROWS_DETAIL").optJSONObject(0);
                        holder.itemMsg.setText("总评：" + all + "发布日期：" + jsonArray.optString("publicTime"));
                    }

                    @Override
                    public void onErrorResponse(VolleyError volleyError) {

                    }
                }).start();

    }

    static class ViewHolderItem {

        private ImageView itemImage;
        private TextView itemTitle;
        private TextView itemContext;
        private TextView itemMsg;
    }


    int index = 0;

    private UserNum setMyInfo(String username) {
        for (int i = 0; i < userNums.size(); i++) {
            UserNum user = userNums.get(i);
            if (user.getUserid().equals(username)) {
                index = i;
                return user;
            }
        }
        return null;
    }

    private void initView() {
        itemChange = findViewById(R.id.item_change);
        title = findViewById(R.id.title);
        title1 = findViewById(R.id.title1);
        tvContent = findViewById(R.id.tv_content);
        tvCommitNum = findViewById(R.id.tv_commit_num);
        layoutCommit = findViewById(R.id.layout_commit);
        layoutTuijian = findViewById(R.id.layout_tuijian);
        etSearch = findViewById(R.id.et_search);
        ivPhoto = findViewById(R.id.iv_photo);
    }
}
