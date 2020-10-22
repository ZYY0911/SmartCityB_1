package com.example.smartcityb_1.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.smartcityb_1.AppClient;
import com.example.smartcityb_1.R;
import com.example.smartcityb_1.fragemnt.AadGroype;
import com.example.smartcityb_1.fragemnt.AddGroupChilde;
import com.example.smartcityb_1.fragemnt.AllServiceFragemnt;
import com.example.smartcityb_1.fragemnt.CarFragment;
import com.example.smartcityb_1.fragemnt.DFFragement;
import com.example.smartcityb_1.fragemnt.DFFragment1;
import com.example.smartcityb_1.fragemnt.EmptyFragment;
import com.example.smartcityb_1.fragemnt.EmptyFragment2;
import com.example.smartcityb_1.fragemnt.EmptyFragment3;
import com.example.smartcityb_1.fragemnt.EmptyFragment4;
import com.example.smartcityb_1.fragemnt.FjhsJFragment;
import com.example.smartcityb_1.fragemnt.HbFragment;
import com.example.smartcityb_1.fragemnt.HoManager;
import com.example.smartcityb_1.fragemnt.HomeFragment;
import com.example.smartcityb_1.fragemnt.LJWPASFragment;
import com.example.smartcityb_1.fragemnt.LiefMoney;
import com.example.smartcityb_1.fragemnt.MyCenterFragment;
import com.example.smartcityb_1.fragemnt.MyOderActivity;
import com.example.smartcityb_1.fragemnt.MzFragment;
import com.example.smartcityb_1.fragemnt.NewsFragment;
import com.example.smartcityb_1.fragemnt.SFDragment1;
import com.example.smartcityb_1.fragemnt.SfFragment;
import com.example.smartcityb_1.fragemnt.SmartBus;
import com.example.smartcityb_1.fragemnt.TccFragment;
import com.example.smartcityb_1.fragemnt.WzDetailsFragement;
import com.example.smartcityb_1.fragemnt.WzcxFragment;
import com.example.smartcityb_1.fragemnt.XxylFragment;
import com.example.smartcityb_1.fragemnt.YlFragment;
import com.example.smartcityb_1.fragemnt.YylsFragment;
import com.example.smartcityb_1.fragemnt.YysmFragemnt;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

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
    public List<Fragment> fragments;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.app_main_layout);

        fragments = new ArrayList<>();
        fragments.add(HomeFragment.newInstance(this));//0
        fragments.add(TccFragment.newInstance(this));//1
        fragments.add(MyCenterFragment.newInstance(this));
        fragments.add(SmartBus.newInstance(this));//3
        fragments.add(MyOderActivity.newInstance(this));
        fragments.add(LiefMoney.newInstance(this));//5
        fragments.add(HbFragment.newInstance(this));
        fragments.add(EmptyFragment.newInstance(this));//7
        fragments.add(EmptyFragment2.newInstance(this));
        fragments.add(EmptyFragment3.newInstance(this));//9
        fragments.add(LJWPASFragment.newInstance(this));//10
        fragments.add(YysmFragemnt.newInstance(this));//11
        fragments.add(YylsFragment.newInstance(this));//12
        fragments.add(XxylFragment.newInstance(this));
        fragments.add(FjhsJFragment.newInstance(this));//14


        fragments.add(SfFragment.newInstance(this));//15
        fragments.add(HoManager.newInstance(this));//16
        fragments.add(DFFragement.newInstance(this));//17
        fragments.add(EmptyFragment4.newInstance(this));//18
        fragments.add(AadGroype.newInstance(this));//19
        fragments.add(AddGroupChilde.newInstance(this));//20
        fragments.add(SFDragment1.newInstance(this));//21
        fragments.add(DFFragment1.newInstance(this));//22

        fragments.add(MzFragment.newInstance(this));//23
        fragments.add(CarFragment.newInstance(this));
        fragments.add(AllServiceFragemnt.newInstance(this));
        fragments.add(NewsFragment.newInstance(this));//26
        fragments.add(YlFragment.newInstance(this));
        fragments.add(WzcxFragment.newInstance(this));//28;
        fragments.add(WzDetailsFragement.newInstance(this));
        AppClient.add(this);
        initView();
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        //bottomNav.setLabelVisibilityMode(1);

        switchFragemnt(fragments.get(0)).commit();
        bottomNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.action_home:
                        switchFragemnt(fragments.get(0)).commit();
                        break;
                    case R.id.action_center:
                        switchFragemnt(fragments.get(2)).commit();
//                        titleLayout.setVisibility(View.VISIBLE);
                        break;
                    case R.id.action_dj:
                        switchFragemnt(fragments.get(27)).commit();
//                         replace(new YlFragment(), false);
                        break;
                    case R.id.action_new:
                        switchFragemnt(fragments.get(26)).commit();
//                        getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, new NewsFragment(AppHomeActivity.this)).commit();
//                        titleLayout.setVisibility(View.GONE);
                        break;
                    case R.id.action_service:
                        switchFragemnt(fragments.get(25)).commit();

//                        getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, new AllServiceFragemnt(AppHomeActivity.this)).commit();
//                        titleLayout.setVisibility(View.GONE);
                        break;
                }
                return true;
            }
        });

        etSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (null != event && KeyEvent.KEYCODE_ENTER == event.getKeyCode()) {
                    switch (event.getAction()) {
                        case KeyEvent.ACTION_UP:
                            Intent intent = new Intent(AppHomeActivity.this, SerachNewsActivity.class);
                            intent.putExtra("info", etSearch.getText().toString());
                            startActivity(intent);
                            return true;
                        default:
                            return true;
                    }
                }
                return false;
            }
        });

    }

    Fragment currentFragment = new Fragment();

    public FragmentTransaction switchFragemnt(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        if (!fragment.isAdded()) {
            if (currentFragment != null) {
                transaction.hide(currentFragment);
            }
            transaction.add(R.id.frame_layout, fragment, fragment.getClass().getName());

        } else {
            transaction.hide(currentFragment).show(fragment);
        }
        if (!fragment.getClass().getName().equals(fragments.get(0).getClass().getName())) {
            titleLayout.setVisibility(View.GONE);
        } else {
            titleLayout.setVisibility(View.VISIBLE);
        }
        currentFragment = fragment;
        return transaction;
    }

    public void backOnClick() {
        switchFragemnt(fragments.get(0)).commit();
    }


    public void replaceNews() {
        getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, new NewsFragment(AppHomeActivity.this)).commit();
        titleLayout.setVisibility(View.GONE);
        bottomNav.setSelectedItemId(R.id.action_new);
    }

    public void replaceService() {
        getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, new AllServiceFragemnt(AppHomeActivity.this)).commit();
        titleLayout.setVisibility(View.GONE);
        bottomNav.setSelectedItemId(R.id.action_service);
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
