package com.example.smartcityb_1.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.smartcityb_1.AppClient;
import com.example.smartcityb_1.R;
import com.example.smartcityb_1.fragemnt.GlideFragment;
import com.example.smartcityb_1.fragemnt.GlideFragment2;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

/**
 * @Login Name win10
 * @Create by 张瀛煜 on 2020/10/8 at 7:42
 */
public class GlideActivity1 extends AppCompatActivity {
    private ViewPager viewPager;
    private LinearLayout linearLayout;
    private int image[] = {R.mipmap.a, R.mipmap.b, R.mipmap.c, R.mipmap.d, R.mipmap.e};


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences preferences = AppClient.preferences;
        if (preferences.getBoolean(AppClient.IsFirst, true)) {
            preferences.edit().putBoolean(AppClient.IsFirst, true).apply();
        } else {
            startActivity(new Intent(GlideActivity1.this, MainActivity.class));
            finish();
            return;
        }
        setContentView(R.layout.glide_layout);
        initView();

    }

    List<Fragment> imageViews;

    private void initView() {
        viewPager = findViewById(R.id.view_pager);
        linearLayout = findViewById(R.id.linear_layout);
        imageViews = new ArrayList<>();
        for (int i = 0; i < image.length; i++) {
            if (i == image.length - 1) {
                GlideFragment2 glideFragment2 = new GlideFragment2(image[i], false);
                imageViews.add(glideFragment2);
            } else {
                GlideFragment fragment = new GlideFragment(image[i], false);
                imageViews.add(fragment);
            }
            ImageView imageView = new ImageView(this);
            if (i == 0) {
                imageView.setBackgroundResource(R.drawable.tv_slect_yuan);
            } else {
                imageView.setBackgroundResource(R.drawable.tv_noselect_yuan);
            }
            LinearLayout.LayoutParams layoutParams1 = new LinearLayout.LayoutParams(20,20);
            layoutParams1.setMargins(20, 0, 20, 0);
            imageView.setLayoutParams(layoutParams1);
            linearLayout.addView(imageView);
        }
        //imageViews.add(new ImageView(this));
//        viewPager.setAdapter(new PagerAdapter() {
//            @Override
//            public int getCount() {
//                return imageViews.size();
//            }
//
//
//            @Override
//            public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
//                container.removeView((View) object);
//            }
//
//
//            @NonNull
//            @Override
//            public Object instantiateItem(@NonNull ViewGroup container, int position) {
//                ImageView imageView = imageViews.get(position);
//                container.addView(imageView);
//                return imageView;
//            }
//
//            @Override
//            public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
//                return view == object;
//            }
//        });
        viewPager.setAdapter(new ViewPgagerMy(getSupportFragmentManager()));
        //  viewPager.setPageTransformer(false, new CustomPagerTranslation(this));
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
//                if (position == imageViews.size() - 1) {
//                    startActivity(new Intent(GlideActivity1.this, MainActivity.class));
//                    finish();
//                    return;
//                }
                for (int i = 0; i < linearLayout.getChildCount(); i++) {
                    ImageView textView = (ImageView) linearLayout.getChildAt(i);
                    if (i == position) {
                        textView.setBackgroundResource(R.drawable.tv_slect_yuan);
                    } else {
                        textView.setBackgroundResource(R.drawable.tv_noselect_yuan);
                    }
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    class ViewPgagerMy extends FragmentPagerAdapter {

        public ViewPgagerMy(@NonNull FragmentManager fm) {
            super(fm);
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            return imageViews.get(position);
        }

        @Override
        public int getCount() {
            return imageViews.size();
        }
    }
}
