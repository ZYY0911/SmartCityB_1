package com.example.smartcityb_1.activity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.smartcityb_1.AppClient;
import com.example.smartcityb_1.R;
import com.example.smartcityb_1.util.Utils;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

/**
 * @Login Name win10
 * @Create by 张瀛煜 on 2020/10/5 at 17:02
 */
public class NetActivity extends AppCompatActivity {
    private TextView title;
    private EditText etIp;
    private EditText etPort;
    private Button btSave;
    private Button btExit;
    private SharedPreferences preferences;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.net_layout);
        initView();
        title.setText("网络设置");
        preferences = AppClient.preferences;
        etIp.setText(preferences.getString(AppClient.Ip, ""));
        etPort.setText(preferences.getString(AppClient.PORT, ""));
        btExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        btSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(etIp.getText()) || TextUtils.isEmpty(etPort.getText())) {
                    Utils.showDialog("Ip或端口号不能为空", NetActivity.this);
                    return;
                }
                preferences.edit().putString(AppClient.Ip, etIp.getText().toString()).apply();
                preferences.edit().putString(AppClient.PORT, etPort.getText().toString()).apply();
                Utils.showToast("设置成功", NetActivity.this);
               // finish();
            }
        });
    }

    private void initView() {
        title = findViewById(R.id.title);
        etIp = findViewById(R.id.et_ip);
        etPort = findViewById(R.id.et_port);
        btSave = findViewById(R.id.bt_save);
        btExit = findViewById(R.id.bt_exit);
    }
}
