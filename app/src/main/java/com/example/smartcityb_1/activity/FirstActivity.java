package com.example.smartcityb_1.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.smartcityb_1.AppClient;
import com.example.smartcityb_1.R;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

/**
 * @Login Name win10
 * @Create by 张瀛煜 on 2020/10/5 at 16:34
 */
public class FirstActivity extends AppCompatActivity {
    private ViewPager viewPager;
    private LinearLayout layout;
    private int arr[] = {R.mipmap.a, R.mipmap.b, R.mipmap.c, R.mipmap.d, R.mipmap.e};
    private SharedPreferences preferences;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        preferences = AppClient.preferences;
        if (preferences.getBoolean(AppClient.IsFirst, true)) {
            preferences.edit().putBoolean(AppClient.IsFirst, false).apply();
        } else {
            startActivity(new Intent(FirstActivity.this, MainActivity.class));
            finish();
            return;
        }
        setContentView(R.layout.first_layout);
        initView();
    }

    List<ImageView> imageViews;

    private void initView() {
        viewPager = findViewById(R.id.view_pager);
        layout = findViewById(R.id.layout);
        imageViews = new ArrayList<>();
        for (int i = 0; i < arr.length; i++) {
            ImageView imageView = new ImageView(this);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(30, 30);
            layoutParams.setMargins(30,0,30,0);
            imageView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            imageView.setBackgroundResource(arr[i]);
            ImageView imageView1 = new ImageView(this);
            imageView1.setLayoutParams(layoutParams);
            if (i == 0) {
                imageView1.setBackgroundResource(R.drawable.yuna_select);
            } else {
                imageView1.setBackgroundResource(R.drawable.yuna_noselect);
            }
            layout.addView(imageView1);
            imageViews.add(imageView);
        }
        imageViews.add(new ImageView(this));
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                for (int i = 0; i < layout.getChildCount(); i++) {
                    ImageView imageView = (ImageView) layout.getChildAt(i);
                    if (i == position) {
                        imageView.setBackgroundResource(R.drawable.yuna_select);
                    } else {
                        imageView.setBackgroundResource(R.drawable.yuna_noselect);
                    }
                }
                if (position == imageViews.size() - 1) {
                    startActivity(new Intent(FirstActivity.this,MainActivity.class));
                    finish();
                }

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        viewPager.setAdapter(new PagerAdapter() {
            @Override
            public int getCount() {
                return imageViews.size();
            }


            @Override
            public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
                container.removeView((View) object);
            }

            @NonNull
            @Override
            public Object instantiateItem(@NonNull ViewGroup container, int position) {
                ImageView imageView = imageViews.get(position);
                container.addView(imageView);
                return imageView;
            }

            @Override
            public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
                return view == object;
            }
        });
    }
}
