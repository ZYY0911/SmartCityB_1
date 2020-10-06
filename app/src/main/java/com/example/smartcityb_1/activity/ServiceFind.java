package com.example.smartcityb_1.activity;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TextView;

import com.example.smartcityb_1.R;
import com.example.smartcityb_1.adapter.ServiceFindAdapter;
import com.example.smartcityb_1.bean.YLServiceBean;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

/**
 * @Login Name win10
 * @Create by 张瀛煜 on 2020/10/6 at 10:38
 */
public class ServiceFind extends AppCompatActivity {
    private ImageView itemChange;
    private TextView title;
    private TextView title1;
    private EditText etSearch;
    private ListView listView;
    private RadioButton rb1;
    private RadioButton rb2;
    private List<YLServiceBean> ylServiceBeans;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.service_find_layout);
        initView();

        ylServiceBeans = new ArrayList<>();

        etSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (null != event && KeyEvent.KEYCODE_ENTER == event.getKeyCode()) {
                    switch (event.getAction()) {
                        case KeyEvent.ACTION_UP:
                            setDate();
                            return true;
                        default:
                            return true;
                    }
                }
                return false;
            }
        });
        title.setText("服务查询");
        itemChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void setDate() {
        ylServiceBeans.clear();
        if (rb1.isChecked()) {
            ylServiceBeans.add(new YLServiceBean("张三", "2020-10-5", "白天", "正常"));
            ylServiceBeans.add(new YLServiceBean("张三", "2020-10-4", "白天", "正常"));
            ylServiceBeans.add(new YLServiceBean("张三", "2020-10-3", "白天", "正常"));
            ylServiceBeans.add(new YLServiceBean("张三", "2020-10-2", "白天", "正常"));
            ylServiceBeans.add(new YLServiceBean("张三", "2020-10-1", "白天", "正常"));
        } else {
            ylServiceBeans.add(new YLServiceBean("李四", "2020-10-13", "白天", "正常"));
            ylServiceBeans.add(new YLServiceBean("李四", "2020-10-12", "白天", "正常"));
            ylServiceBeans.add(new YLServiceBean("李四", "2020-10-11", "白天", "正常"));
            ylServiceBeans.add(new YLServiceBean("李四", "2020-10-10", "白天", "正常"));
            ylServiceBeans.add(new YLServiceBean("李四", "2020-10-9", "白天", "正常"));
        }
        listView.setAdapter(new ServiceFindAdapter(ServiceFind.this, ylServiceBeans));
    }

    private void initView() {
        itemChange = findViewById(R.id.item_change);
        title = findViewById(R.id.title);
        title1 = findViewById(R.id.title1);
        etSearch = findViewById(R.id.et_search);
        listView = findViewById(R.id.list_view);
        rb1 = findViewById(R.id.rb_1);
        rb2 = findViewById(R.id.rb_2);
    }
}
