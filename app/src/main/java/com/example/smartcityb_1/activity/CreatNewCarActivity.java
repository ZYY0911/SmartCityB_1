package com.example.smartcityb_1.activity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;

import com.android.volley.VolleyError;
import com.example.smartcityb_1.R;
import com.example.smartcityb_1.net.VolleyLo;
import com.example.smartcityb_1.net.VolleyTo;
import com.example.smartcityb_1.util.Utils;

import org.json.JSONObject;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

/**
 * @Login Name win10
 * @Create by 张瀛煜 on 2020/10/6 at 14:59
 */
public class CreatNewCarActivity extends AppCompatActivity {
    private ImageView itemChange;
    private TextView title;
    private TextView title1;
    private EditText tvName;
    private EditText tvSex;
    private EditText tvId;
    private EditText tvBirth;
    private EditText tvTel;
    private EditText tvAddress;
    private TextView tvOnline;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_car_layout);

        initView();
        title.setText("创建就诊卡");
        itemChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        tvOnline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(tvId.getText())) {
                    Utils.showDialog("身份证号不能为空", CreatNewCarActivity.this);
                    return;
                }
                VolleyTo volleyTo = new VolleyTo();
                //{name:"杨仪涵",sex:"女",ID:"371402201002078824",birthday:"2010-2-7",tel:"15905346666",address:"八一小区"}
                volleyTo.setUrl("createCase")
                        .setJsonObject("name", tvName.getText().toString())
                        .setJsonObject("sex", tvSex.getText().toString())
                        .setJsonObject("ID", tvId.getText().toString())
                        .setJsonObject("birthday", tvBirth.getText().toString())
                        .setJsonObject("tel", tvTel.getText().toString())
                        .setJsonObject("address", tvAddress.getText().toString())
                        .setVolleyLo(new VolleyLo() {
                            @Override
                            public void onResponse(JSONObject jsonObject) {
                                if (jsonObject.optString("RESULT").equals("S")) {
                                    Utils.showToast("添加成功", CreatNewCarActivity.this);
                                    finish();
                                } else {
                                    Utils.showToast("添加失败", CreatNewCarActivity.this);
                                }
                            }

                            @Override
                            public void onErrorResponse(VolleyError volleyError) {
                                Utils.showToast("添加失败", CreatNewCarActivity.this);
                            }
                        }).start();
            }
        });
        tvBirth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(CreatNewCarActivity.this
                        , new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        tvBirth.setText(year+"-"+(month+1)+"-"+dayOfMonth);
                    }
                },2020,10,6);
                datePickerDialog.show();
            }
        });
    }

    private void initView() {
        itemChange = findViewById(R.id.item_change);
        title = findViewById(R.id.title);
        title1 = findViewById(R.id.title1);
        tvName = findViewById(R.id.tv_name);
        tvSex = findViewById(R.id.tv_sex);
        tvId = findViewById(R.id.tv_id);
        tvBirth = findViewById(R.id.tv_birth);
        tvTel = findViewById(R.id.tv_tel);
        tvAddress = findViewById(R.id.tv_address);
        tvOnline = findViewById(R.id.tv_online);
    }
}
