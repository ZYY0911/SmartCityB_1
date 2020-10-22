package com.example.smartcityb_1.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.smartcityb_1.R;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.formatter.PercentFormatter;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

/**
 * @Login Name win10
 * @Create by 张瀛煜 on 2020/10/6 at 10:52
 */
public class JzjcActivity extends AppCompatActivity {
    private ImageView itemChange;
    private TextView title;
    private TextView title1;
    private TextView tvDhl;
    private TextView tvSj;

    Random random = new Random();
    private PieChart pieChart;
    private BarChart barChart;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.jzjc_layout);
        initView();
        itemChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        tvSj.setText("户外活动时间：" + random.nextInt(2) + "h" + random.nextInt(61) + "min");
        tvDhl.setText("运动量：" + random.nextInt(5000) + "");
        setPicChart();
        setBarChart();
        title.setText("集中监测");
    }

    private void setBarChart() {
        List<BarEntry> barEntries = new ArrayList<>();
        barEntries.add(new BarEntry(0, 12));
        barEntries.add(new BarEntry(1, 15));
        barEntries.add(new BarEntry(2, 42));
        barEntries.add(new BarEntry(3, 20));
        BarDataSet dataSet = new BarDataSet(barEntries, "");
        List<Integer> integers = new ArrayList<>();
        for (int i = 0; i < barEntries.size(); i++) {
            integers.add(getColor());
        }
        dataSet.setColors(integers);
        dataSet.setBarBorderWidth(0.3f);
        BarData data = new BarData(dataSet);
        barChart.setData(data);
        XAxis xAxis = barChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setGranularity(1f);
        xAxis.setValueFormatter(new IndexAxisValueFormatter(new String[]{"60~70", "70~80", "80~90", "90+"}));
        barChart.getAxisRight().setEnabled(false);
        barChart.getDescription().setEnabled(false);
        barChart.getLegend().setEnabled(false);
    }

    private int getColor() {
        return Color.rgb(random.nextInt(256), random.nextInt(256), random.nextInt(256));
    }

    private void setPicChart() {
        List<PieEntry> pieEntries = new ArrayList<>();
        pieEntries.add(new PieEntry(70.5f, "已入住"));
        pieEntries.add(new PieEntry(29.5f, "未入住"));
        PieDataSet pieDataSet = new PieDataSet(pieEntries, "");
        pieDataSet.setColors(new int[]{getColor(), getColor()});
        pieDataSet.setValueTextSize(12);
        pieDataSet.setValueFormatter(new PercentFormatter());
        PieData data = new PieData(pieDataSet);
        pieChart.setData(data);
        pieChart.getDescription().setEnabled(false);
        Legend legend = pieChart.getLegend();
        pieChart.setDrawHoleEnabled(false);
        pieChart.setRotationEnabled(false);
        pieChart.setUsePercentValues(true);
        legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.CENTER);

    }

    private void initView() {
        itemChange = findViewById(R.id.item_change);
        title = findViewById(R.id.title);
        title1 = findViewById(R.id.title1);
        tvDhl = findViewById(R.id.tv_dhl);
        tvSj = findViewById(R.id.tv_sj);
        pieChart = findViewById(R.id.pie_chart);
        barChart = findViewById(R.id.bar_chart);
    }
}
