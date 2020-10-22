package com.example.smartcityb_1.activity;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.smartcityb_1.R;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

/**
 * @Login Name win10
 * @Create by 张瀛煜 on 2020/10/6 at 10:56
 */
public class AllServiceUrl extends AppCompatActivity {
    private WebView webView;
    private String url;
    private ImageView itemChange;
    private TextView title;
    private TextView title1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.wervic_web);
        initView();
        title.setText(getIntent().getStringExtra("date"));
        itemChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        url = getIntent().getStringExtra("infos");
        webView.loadUrl(url);
        
    }

    private void initView() {
        webView = findViewById(R.id.web_view);
        itemChange = findViewById(R.id.item_change);
        title = findViewById(R.id.title);
        title1 = findViewById(R.id.title1);
    }
}
