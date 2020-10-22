package com.example.smartcityb_1.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.example.smartcityb_1.R;
import com.example.smartcityb_1.bean.UserInfos;
import com.example.smartcityb_1.net.VolleyImage;
import com.example.smartcityb_1.net.VolleyLo;
import com.example.smartcityb_1.net.VolleyLoImage;
import com.example.smartcityb_1.net.VolleyTo;
import com.example.smartcityb_1.util.Utils;

import org.json.JSONObject;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

/**
 * @Login Name win10
 * @Create by 张瀛煜 on 2020/10/8 at 10:24
 */
public class MotifInfoActivity extends AppCompatActivity {
    private ImageView itemChange;
    private TextView title;
    private TextView title1;
    private LinearLayout lauyoutPhoto;
    private ImageView ivPhoto;
    private LinearLayout lauyoutName;
    private EditText etNick;
    private LinearLayout lauyoutSex;
    private EditText etSex;
    private LinearLayout lauyoutTel;
    private EditText etTel;
    private LinearLayout lauyoutId;
    private TextView tvId;
    private TextView tvSave;

    UserInfos userInfos;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_info_layout);
        initView();
        userInfos = (UserInfos) getIntent().getSerializableExtra("info");
        title.setText("个人信息");
        itemChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        etNick.setText(userInfos.getName());
        etTel.setText(userInfos.getPhone());
        etSex.setText(userInfos.getSex());
        etSex.setSelection(userInfos.getSex().length());
        etTel.setSelection(userInfos.getPhone().length());
        etNick.setSelection(userInfos.getName().length());
        StringBuilder stringBuilder = new StringBuilder(userInfos.getId());
        stringBuilder.replace(2, 14, "************");
        tvId.setText(stringBuilder.toString());
        VolleyImage volleyImage = new VolleyImage();
        volleyImage.setUrl(userInfos.getAvatar())
                .setVolleyLoImage(new VolleyLoImage() {
                    @Override
                    public void onResponse(Bitmap bitmap) {
                        ivPhoto.setImageBitmap(bitmap);
                    }

                    @Override
                    public void onErrorResponse(VolleyError volleyError) {

                    }
                }).start();
        tvSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(etNick.getText()) || TextUtils.isEmpty(etSex.getText()) || TextUtils.isEmpty(etTel.getText())) {
                    Utils.showDialog("内容不能有空", MotifInfoActivity.this);
                    return;
                }
                VolleyTo volleyTo = new VolleyTo();
                volleyTo.setUrl("setUserInfo")
                        //{userid:"1","name":"","avatar":"","phone":"phone","id":"1","gender":""}
                        .setJsonObject("userid", userInfos.getUserid())
                        .setJsonObject("name", etNick.getText().toString())
                        .setJsonObject("avatar", ivPhoto.getTag() == null ? "" : ivPhoto.getTag().toString())
                        .setJsonObject("phone", etTel.getText().toString())
                        .setJsonObject("gender", etSex.getText().toString())
                        .setJsonObject("id", "")
                       // .setDialog(MotifInfoActivity.this)
                        .setVolleyLo(new VolleyLo() {
                            @Override
                            public void onResponse(JSONObject jsonObject) {
                                if (jsonObject.optString("RESULT").equals("S")) {
                                    Utils.showDialog("保存成功", MotifInfoActivity.this);
                                    Intent intent  = new Intent();
                                    setResult(RESULT_OK,intent);
                                    finish();
                                } else {
                                    Utils.showDialog("保存失败", MotifInfoActivity.this);
                                }
                            }

                            @Override
                            public void onErrorResponse(VolleyError volleyError) {
                                Utils.showDialog("保存失败", MotifInfoActivity.this);
                            }
                        }).start();

            }
        });

        lauyoutPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MotifInfoActivity.this, MotifImage.class);
                intent.putExtra("infos", userInfos.getAvatar());
                startActivityForResult(intent, 1);
            }
        });

    }

    Integer images[] = {R.mipmap.user1, R.mipmap.user2,
            R.mipmap.user3, R.mipmap.user4, R.mipmap.user5, R.mipmap.user6, R.mipmap.user7, R.mipmap.user8};

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        switch (requestCode) {
            case 1:
                if (resultCode == RESULT_OK) {
                    ivPhoto.setImageResource(images[data.getIntExtra("index", 0)]);
                    ivPhoto.setTag(data.getStringExtra("date"));
                }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void initView() {
        itemChange = findViewById(R.id.item_change);
        title = findViewById(R.id.title);
        title1 = findViewById(R.id.title1);
        lauyoutPhoto = findViewById(R.id.lauyout_photo);
        ivPhoto = findViewById(R.id.iv_photo);
        lauyoutName = findViewById(R.id.lauyout_name);
        etNick = findViewById(R.id.et_nick);
        lauyoutSex = findViewById(R.id.lauyout_sex);
        etSex = findViewById(R.id.et_sex);
        lauyoutTel = findViewById(R.id.lauyout_tel);
        etTel = findViewById(R.id.et_tel);
        lauyoutId = findViewById(R.id.lauyout_id);
        tvId = findViewById(R.id.tv_id);
        tvSave = findViewById(R.id.tv_save);
    }
}
