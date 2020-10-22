package com.example.smartcityb_1.activity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.example.smartcityb_1.AppClient;
import com.example.smartcityb_1.R;
import com.example.smartcityb_1.bean.UserInfos;
import com.example.smartcityb_1.bean.UserNum;
import com.example.smartcityb_1.net.VolleyLo;
import com.example.smartcityb_1.net.VolleyTo;
import com.example.smartcityb_1.util.Utils;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONObject;

import java.util.Date;
import java.util.List;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

/**
 * @Login Name win10
 * @Create by 张瀛煜 on 2020/10/5 at 21:14
 */
public class YjfkActivity extends AppCompatActivity {
    private ImageView itemChange;
    private TextView title;
    private TextView title1;
    private EditText etMsg;
    private TextView tvCount;
    private TextView tvSave;

    private UserInfos userInfos;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.yjfk_layout);
        userInfos = (UserInfos) getIntent().getSerializableExtra("date");
        initView();
        title.setText("意见反馈");
        itemChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        etMsg.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!TextUtils.isEmpty(s)) {
                    String leangh = s.toString();
                    if (leangh.length() >= 151) {
                        Utils.showToast("只能输入150字", YjfkActivity.this);
                        etMsg.setText(leangh.substring(0, 150));
                        etMsg.setSelection(150);
                        return;
                    }
                    tvCount.setText(leangh.length() + "/150字");
                }
            }
        });
        tvSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                VolleyTo volleyTo = new VolleyTo();
                volleyTo.setUrl("publicOpinion")
                        //{"username":"user1","content":"内容","time":"yyyy.MM.dd HH:mm:ss"}
                        .setJsonObject("userid",getUserName(AppClient.username))
                        .setJsonObject("content", etMsg.getText().toString())
                        .setJsonObject("time", Utils.simpleDate("yyyy.MM.dd HH:mm:ss", new Date()))
                        .setVolleyLo(new VolleyLo() {
                            @Override
                            public void onResponse(JSONObject jsonObject) {
                                if (jsonObject.optString("RESULT").equals("S")) {
                                    Utils.showToast("发布成功", YjfkActivity.this);
                                    finish();
                                } else {
                                    Utils.showToast("发布失败", YjfkActivity.this);
                                }
                            }

                            @Override
                            public void onErrorResponse(VolleyError volleyError) {
                                Utils.showToast("发布失败", YjfkActivity.this);

                            }
                        }).start();
            }
        });
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

    private void initView() {
        itemChange = findViewById(R.id.item_change);
        title = findViewById(R.id.title);
        title1 = findViewById(R.id.title1);
        etMsg = findViewById(R.id.et_msg);
        tvCount = findViewById(R.id.tv_count);
        tvSave = findViewById(R.id.tv_save);
    }
}
