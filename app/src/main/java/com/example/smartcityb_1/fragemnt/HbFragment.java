package com.example.smartcityb_1.fragemnt;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.TextView;


import com.example.smartcityb_1.R;
import com.example.smartcityb_1.activity.AppHomeActivity;
import com.example.smartcityb_1.adapter.HbAdapter;
import com.example.smartcityb_1.util.OnClickItem;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

/**
 * @Login Name win10
 * @Create by 张瀛煜 on 2020/10/8 at 16:39
 */
public class HbFragment extends Fragment {
    private TextView title;
    private TextView title1;
    private ViewPager viewPagerHb;
    private GridView hbGird;

    public HbFragment() {

    }

    private AppHomeActivity appHomeActivity;

    public HbFragment(AppHomeActivity appHomeActivity) {
        this.appHomeActivity = appHomeActivity;
    }

    public static HbFragment newInstance(AppHomeActivity appHomeActivity) {
        return new HbFragment(appHomeActivity);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.hb_fragemnt, container, false);
    }

    private int images[] = {R.mipmap.ljwp, R.mipmap.door_icon, R.mipmap.hs_icon, R.mipmap.msg_icon, R.mipmap.jq};
    List<String> strings;


    HbAdapter adapter;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
        title.setText("智慧环保");
        strings = new ArrayList<>();
        strings.add("垃圾物品展示");
        strings.add("预约上门回收");
        strings.add("预约回收历史");
        strings.add("信息预留");
        strings.add("附近回收机");
        adapter = new HbAdapter(getActivity(), strings, images);
        hbGird.setAdapter(adapter);
        adapter.setOnClickItem(new OnClickItem() {
            @Override
            public void onClick(int position, String name) {
                if (name.equals("垃圾物品展示")) {
                    appHomeActivity.switchFragemnt(appHomeActivity.fragments.get(10)).commit();
                }else if (name.equals("预约上门回收")){
                    appHomeActivity.switchFragemnt(appHomeActivity.fragments.get(11)).commit();

                }else if (name.equals("预约回收历史")){
                    appHomeActivity.switchFragemnt(appHomeActivity.fragments.get(12)).commit();

                }else if (name.equals("信息预留")){
                    appHomeActivity.switchFragemnt(appHomeActivity.fragments.get(13)).commit();

                }else {
                    appHomeActivity.switchFragemnt(appHomeActivity.fragments.get(14)).commit();

                }
            }
        });


    }

    private void initView() {
        title = getView().findViewById(R.id.title);
        title1 = getView().findViewById(R.id.title1);
        viewPagerHb = getView().findViewById(R.id.view_pager_hb);
        hbGird = getView().findViewById(R.id.ljzs_gird);
    }
}
