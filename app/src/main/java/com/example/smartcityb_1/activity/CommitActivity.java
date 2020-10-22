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
import com.example.smartcityb_1.R;
import com.example.smartcityb_1.bean.User;
import com.example.smartcityb_1.net.VolleyLo;
import com.example.smartcityb_1.net.VolleyTo;
import com.example.smartcityb_1.util.Utils;

import org.json.JSONObject;

import java.util.Date;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

/**
 * @Login Name win10
 * @Create by 张瀛煜 on 2020/10/2 at 11:35
 */
public class CommitActivity extends AppCompatActivity {
    private ImageView itemChange;
    private TextView title;
    private TextView title1;
    private EditText etCommit;
    private TextView tvCount;
    private TextView tvSave;
    private User user;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.commit_layout);
        initView();
        user = (User) getIntent().getSerializableExtra("info");
        title.setText("意见反馈");
        itemChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        etCommit.addTextChangedListener(new TextWatcher() {
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
                        Utils.showToast("只能输入150字", CommitActivity.this);
                        etCommit.setText(leangh.substring(0, 150));
                        etCommit.setSelection(150);
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
                        .setJsonObject("username", user.getUsername())
                        .setJsonObject("content", etCommit.getText().toString())
                        .setJsonObject("time", Utils.simpleDate("yyyy.MM.dd HH:mm:ss", new Date()))
                        .setVolleyLo(new VolleyLo() {
                            @Override
                            public void onResponse(JSONObject jsonObject) {
                                if (jsonObject.optString("RESULT").equals("S")) {
                                    Utils.showToast("发布成功", CommitActivity.this);
                                    finish();
                                } else {
                                    Utils.showToast("发布失败", CommitActivity.this);
                                }
                            }

                            @Override
                            public void onErrorResponse(VolleyError volleyError) {
                                Utils.showToast("发布失败", CommitActivity.this);

                            }
                        }).start();
            }
        });
    }

    private void initView() {
        itemChange = findViewById(R.id.item_change);
        title = findViewById(R.id.title);
        title1 = findViewById(R.id.title1);
        etCommit = findViewById(R.id.et_commit);
        tvCount = findViewById(R.id.tv_count);
        tvSave = findViewById(R.id.tv_save);
    }
}

