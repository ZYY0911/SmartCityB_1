package com.example.smartcityb_1.fragemnt;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.smartcityb_1.R;
import com.example.smartcityb_1.activity.AppHomeActivity;
import com.example.smartcityb_1.adapter.FjHsjAdatper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

/**
 * @Login Name win10
 * @Create by 张瀛煜 on 2020/10/8 at 17:45
 */
public class FjhsJFragment extends Fragment {
    private ImageView itemChange;
    private TextView title;
    private TextView title1;
    private ExpandableListView expandList;

    public FjhsJFragment() {

    }

    private AppHomeActivity appHomeActivity;

    public FjhsJFragment(AppHomeActivity appHomeActivity) {
        this.appHomeActivity = appHomeActivity;
    }

    public static FjhsJFragment newInstance(AppHomeActivity appHomeActivity) {
        return new FjhsJFragment(appHomeActivity);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fjhsj_layout, container, false);
    }

    Map<String, List<String>> map;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
        itemChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                appHomeActivity.switchFragemnt(appHomeActivity.fragments.get(6)).commit();

            }
        });
        title.setText("附近回收机");
        map = new HashMap<>();
        List<String> strings = new ArrayList<>();
        strings.add("衣服     5元/件");
        strings.add("瓶子      0.5元/个");
        map.put("一号回收机器", strings);
        List<String> strings1 = new ArrayList<>();
        strings1.add("衣服     5元/件");
        strings1.add("瓶子      0.5元/个");
        map.put("二号回收机器", strings1);
        expandList.setAdapter(new FjHsjAdatper(map));
    }

    private void initView() {
        itemChange = getView().findViewById(R.id.item_change);
        title = getView().findViewById(R.id.title);
        title1 = getView().findViewById(R.id.title1);
        expandList = getView().findViewById(R.id.expand_list);
    }
}
