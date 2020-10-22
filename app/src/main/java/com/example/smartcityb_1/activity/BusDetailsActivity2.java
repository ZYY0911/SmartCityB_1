package com.example.smartcityb_1.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;


import com.example.smartcityb_1.AppClient;
import com.example.smartcityb_1.R;
import com.example.smartcityb_1.adapter.RlADapter;
import com.example.smartcityb_1.bean.BusTitle;
import com.example.smartcityb_1.bean.Rl_Bean;
import com.example.smartcityb_1.util.OnClickItem;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

/**
 * @Login Name win10
 * @Create by 张瀛煜 on 2020/10/8 at 14:44
 */
public class BusDetailsActivity2 extends AppCompatActivity {

    private ImageView itemChange;
    private TextView title;
    private TextView title1;
    private GridView girdView;
    private TextView tvMsg;
    private TextView tvNext;
    BusTitle busTitle;


    List<Rl_Bean> rl_beans;
    List<Rl_Bean> rl_beansselect;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bus_detalis_layout2);AppClient.add2(this);
        initView();
        setDate();
        busTitle = (BusTitle) getIntent().getSerializableExtra("date");
        title.setText("定制班车");
        itemChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        tvNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BusDetailsActivity2.this, BusDetailsActivity3.class);
                intent.putExtra("date", busTitle);
                intent.putExtra("time", str);
                startActivity(intent);
            }
        });

    }

    RlADapter aDapter;
    private List<Integer> integers;

    private void setDate() {
        rl_beansselect = new ArrayList<>();
        rl_beans = new ArrayList<>();
        integers = new ArrayList<>();
        Calendar calendar = Calendar.getInstance();
        for (int i = 1; i <  getWeekDay(calendar); i++) {
            rl_beans.add(new Rl_Bean(0, 0, 0, 1, 1));
            integers.add(1);
        }
        for (int i = 0; i < 42; i++) {
            rl_beans.add(new Rl_Bean(calendar.get(Calendar.DAY_OF_MONTH), (calendar.get(Calendar.MONTH) + 1), i, 2, getBg(calendar)));
            integers.add(getBg(calendar));
            calendar.add(Calendar.DAY_OF_MONTH, 1);

        }
        for (int i = 0; i < rl_beans.size() - 49; i++) {
            integers.add(1);
            rl_beans.add(new Rl_Bean(0, 0, 0, 1, 1));
        }
        aDapter = new RlADapter(BusDetailsActivity2.this, rl_beans);
        girdView.setAdapter(aDapter);
        aDapter.setOnClickItem(new OnClickItem() {
            @Override
            public void onClick(int position, String name) {
                Rl_Bean rl_bean = rl_beans.get(position);
                if (rl_bean.getBg() != 2) {
                    rl_bean.setBg(2);
                    rl_beansselect.add(rl_bean);
                } else {
                    rl_bean.setBg(integers.get(position));
                    rl_beansselect.remove(rl_bean);
                }
                rl_beans.set(position,rl_bean);
                setTExtView();
                aDapter.notifyDataSetChanged();
            }
        });
    }

    String str = "";

    private void setTExtView() {
        Collections.sort(rl_beansselect, new Comparator<Rl_Bean>() {
            @Override
            public int compare(Rl_Bean o1, Rl_Bean o2) {
                return o1.getIndex() - o2.getIndex();
            }
        });

        str = "";
        for (int i = 0; i < rl_beansselect.size(); i++) {
            if (i == 0) {
                str = "2020-" + rl_beansselect.get(i).getMonth() + "-" + rl_beansselect.get(i).getDay();
            } else {
                str += ",2020-" + rl_beansselect.get(i).getMonth() + "-" + rl_beansselect.get(i).getDay();
            }
        }
        tvMsg.setText("已选择日期：\r\n" + str);
    }


    private int getBg(Calendar calendar) {
        if (calendar.get(Calendar.DAY_OF_WEEK) == 7 || calendar.get(Calendar.DAY_OF_WEEK) == 1) {
            return 1;
        } else {
            return 0;
        }
    }

    // * @see #SUNDAY
    //     * @see #MONDAY
    //     * @see #TUESDAY
    //     * @see #WEDNESDAY
    //     * @see #THURSDAY
    //     * @see #FRIDAY
    //     * @see #SATURDAY
    private int getWeekDay(Calendar calendar) {
        return calendar.get(Calendar.DAY_OF_WEEK);
    }


    private void initView() {
        itemChange = findViewById(R.id.item_change);
        title = findViewById(R.id.title);
        title1 = findViewById(R.id.title1);
        girdView = findViewById(R.id.gird_view);
        tvMsg = findViewById(R.id.tv_msg);
        tvNext = findViewById(R.id.tv_next);
    }
}
