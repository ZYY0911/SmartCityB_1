package com.example.smartcityb_1.activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.smartcityb_1.AppClient;
import com.example.smartcityb_1.R;
import com.example.smartcityb_1.fragemnt.HomeFragment;
import com.example.smartcityb_1.fragemnt.MyCenterFragment;
import com.example.smartcityb_1.fragemnt.YlFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

/**
 * @Login Name win10
 * @Create by 张瀛煜 on 2020/10/5 at 17:17
 */
public class AppHomeActivity extends AppCompatActivity {
    private LinearLayout titleLayout;
    private TextView tvTitle;
    private EditText etSearch;
    private FrameLayout frameLayout;
    private BottomNavigationView bottomNav;

    @SuppressLint("WrongConstant")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.app_main_layout);
        AppClient.add(this);
        initView();
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        bottomNav.setLabelVisibilityMode(1);
        getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, new HomeFragment(AppHomeActivity.this)).commit();
        bottomNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.action_home:
                        getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, new HomeFragment(AppHomeActivity.this)).commit();
                        break;
                    case R.id.action_center:
                        getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, new MyCenterFragment()).commit();
                        break;
                    case R.id.action_dj:
                        replace(new YlFragment(), false);
                        break;
                }
                return true;
            }
        });

    }

    public void replace(Fragment fragment, boolean isShow) {
        getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, fragment).commit();
        titleLayout.setVisibility(isShow ? View.VISIBLE : View.GONE);
    }

    private void initView() {
        titleLayout = findViewById(R.id.title_layout);
        tvTitle = findViewById(R.id.tv_title);
        etSearch = findViewById(R.id.et_search);
        frameLayout = findViewById(R.id.frame_layout);
        bottomNav = findViewById(R.id.bottom_nav);
    }
}
