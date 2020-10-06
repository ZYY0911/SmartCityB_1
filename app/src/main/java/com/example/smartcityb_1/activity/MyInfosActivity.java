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
 * @Create by 张瀛煜 on 2020/10/5 at 20:22
 */
public class MyInfosActivity extends AppCompatActivity {
    private TextView itemChange;
    private TextView title;
    private TextView title1;
    private LinearLayout lauyoutPhoto;
    private ImageView ivPhoto;
    private LinearLayout lauyoutName;
    private LinearLayout lauyoutSex;
    private LinearLayout lauyoutTel;
    private LinearLayout lauyoutId;
    private TextView tvId;
    private TextView tvSave;
    private UserInfos userInfos;
    private EditText etNick;
    private EditText etSex;
    private EditText etTel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_info_layout);
        initView();
        title.setText("个人信息");
        itemChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        userInfos = (UserInfos) getIntent().getSerializableExtra("date");
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
                    Utils.showDialog("内容不能有空", MyInfosActivity.this);
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
                        .setVolleyLo(new VolleyLo() {
                            @Override
                            public void onResponse(JSONObject jsonObject) {
                                if (jsonObject.optString("RESULT").equals("S")) {
                                    Utils.showDialog("保存成功", MyInfosActivity.this);
                                } else {
                                    Utils.showDialog("保存失败", MyInfosActivity.this);
                                }
                            }

                            @Override
                            public void onErrorResponse(VolleyError volleyError) {
                                Utils.showDialog("保存失败", MyInfosActivity.this);
                            }
                        }).start();

            }
        });

        lauyoutPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //  {
                //            "id": "371402199902041133",
                //            "name": "赵子涵",
                //            "avatar": "http://localhost:8080/mobileA/images/user1.png",
                //            "phone": "13505347779",
                //            "sex": "女"
                //        }
                Intent intent = new Intent(MyInfosActivity.this, MotifImage.class);
                intent.putExtra("infos", userInfos.getAvatar());
                startActivityForResult(intent, 1);
            }
        });

    }

    private Integer images[] = {R.mipmap.user1, R.mipmap.user2,
            R.mipmap.user3, R.mipmap.user4, R.mipmap.user5, R.mipmap.user6, R.mipmap.user7, R.mipmap.user8};

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        switch (requestCode) {
            case 1:
                if (resultCode == RESULT_OK) {
                    ivPhoto.setImageResource(images[data.getIntExtra("index", 0)]);
                    ivPhoto.setTag(data.getStringExtra("date"));
                }
                break;

            default:
                break;
        }
        super.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void initView() {
        itemChange = findViewById(R.id.item_change);
        title = findViewById(R.id.title);
        title1 = findViewById(R.id.title1);
        lauyoutPhoto = findViewById(R.id.lauyout_photo);
        ivPhoto = findViewById(R.id.iv_photo);
        lauyoutName = findViewById(R.id.lauyout_name);
        lauyoutSex = findViewById(R.id.lauyout_sex);
        lauyoutTel = findViewById(R.id.lauyout_tel);
        lauyoutId = findViewById(R.id.lauyout_id);
        tvId = findViewById(R.id.tv_id);
        tvSave = findViewById(R.id.tv_save);
        etNick = findViewById(R.id.et_nick);
        etSex = findViewById(R.id.et_sex);
        etTel = findViewById(R.id.et_tel);
    }
}
