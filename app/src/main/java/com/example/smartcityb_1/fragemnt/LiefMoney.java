package com.example.smartcityb_1.fragemnt;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.smartcityb_1.R;
import com.example.smartcityb_1.activity.AppHomeActivity;
import com.example.smartcityb_1.adapter.LieeAdapter;
import com.example.smartcityb_1.util.OnClickItem;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

/**
 * @Login Name win10
 * @Create by 张瀛煜 on 2020/10/8 at 16:21
 */
public class LiefMoney extends Fragment {
    private TextView title;
    private TextView title1;
    private GridView lifeGird;
    private TextView tvHhgl;
    private ImageView itemChange;

    public LiefMoney() {
    }

    public LiefMoney(AppHomeActivity appHomeActivity) {
        this.appHomeActivity = appHomeActivity;
    }

    private AppHomeActivity appHomeActivity;

    public static LiefMoney newInstance(AppHomeActivity appHomeActivity) {
        return new LiefMoney(appHomeActivity);
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.lief_moemy, container, false);
    }

    List<String> strings;

    LieeAdapter adapter;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
        itemChange.setVisibility(View.INVISIBLE);
        title.setText("德州    生活缴费");
        strings = new ArrayList<>();
        strings.add("水费");
        strings.add("电费");
        strings.add("户号管理");
        strings.add("相关资讯");
        adapter = new LieeAdapter(getActivity(), strings);
        lifeGird.setAdapter(adapter);
        adapter.setOnClickItem(new OnClickItem() {
            @Override
            public void onClick(int position, String name) {
                if (name.equals("水费")) {
                    appHomeActivity.switchFragemnt(appHomeActivity.fragments.get(21)).commit();
                } else if (name.equals("户号管理")) {
                    appHomeActivity.switchFragemnt(appHomeActivity.fragments.get(16)).commit();
                } else if (name.equals("电费")) {
                    appHomeActivity.switchFragemnt(appHomeActivity.fragments.get(22)).commit();
                } else {
                    appHomeActivity.switchFragemnt(appHomeActivity.fragments.get(18)).commit();
                }
            }
        });
        tvHhgl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                appHomeActivity.switchFragemnt(appHomeActivity.fragments.get(16)).commit();

            }
        });


    }

    private void initView() {
        title = getView().findViewById(R.id.title);
        title1 = getView().findViewById(R.id.title1);
        lifeGird = getView().findViewById(R.id.life_gird);
        tvHhgl = getView().findViewById(R.id.tv_hhgl);
        itemChange = getView().findViewById(R.id.item_change);
    }
}
