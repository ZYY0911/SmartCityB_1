package com.example.smartcityb_1.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.smartcityb_1.R;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

/**
 * @Login Name win10
 * @Create by 张瀛煜 on 2020/10/6 at 10:17
 */
public class YYYLYActivity extends AppCompatActivity {
    private TextView itemChange;
    private EditText etSearch;
    private GridView girdView;
    private LinearLayout layoutYyyl;
    private LinearLayout layoutYyyl2;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.yyyly_layout);
        initView();
        itemChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        layoutYyyl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(YYYLYActivity.this,YYLyDetailsActivity.class);
                intent.putExtra("index",1);
                startActivity(intent);
            }
        });
        layoutYyyl2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(YYYLYActivity.this,YYLyDetailsActivity.class);
                intent.putExtra("index",2);
                startActivity(intent);
            }
        });


    }

    private void initView() {
        itemChange = findViewById(R.id.item_change);

        etSearch = findViewById(R.id.et_search);
        layoutYyyl = findViewById(R.id.layout_yyyl);
        layoutYyyl2 = findViewById(R.id.layout_yyyl2);
    }
}
