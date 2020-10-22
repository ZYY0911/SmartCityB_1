package com.example.smartcityb_1.fragemnt;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.smartcityb_1.R;
import com.example.smartcityb_1.activity.AppHomeActivity;
import com.example.smartcityb_1.activity.LjLayout;
import com.example.smartcityb_1.adapter.HbAdapter;
import com.example.smartcityb_1.util.OnClickItem;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

/**
 * @Login Name win10
 * @Create by 张瀛煜 on 2020/10/8 at 16:57
 */
public class LJWPASFragment extends Fragment {
    private ImageView itemChange;
    private TextView title;
    private TextView title1;
    private GridView ljzsGird;

    public LJWPASFragment() {

    }

    private AppHomeActivity appHomeActivity;

    public LJWPASFragment(AppHomeActivity appHomeActivity) {
        this.appHomeActivity = appHomeActivity;
    }

    public static LJWPASFragment newInstance(AppHomeActivity appHomeActivity) {
        return new LJWPASFragment(appHomeActivity);
    }

    private int imags[] = {R.drawable.khslj, R.drawable.cylj, R.drawable.yhlj, R.drawable.qtlj, R.drawable.glj, R.drawable.slj};

    private List<String> strings;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.ljwpzs_layout, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
        title.setText("垃圾物品展示");
        itemChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                appHomeActivity.switchFragemnt(appHomeActivity.fragments.get(6)).commit();
            }
        });
        strings = new ArrayList<>();
        strings.add("可回收垃圾");
        strings.add("厨余垃圾");
        strings.add("有害垃圾");
        strings.add("其他垃圾");
        strings.add("干垃圾");
        strings.add("湿垃圾");
        HbAdapter adapter = new HbAdapter(getActivity(), strings, imags);
        ljzsGird.setAdapter(adapter);
        adapter.setOnClickItem(new OnClickItem() {
            @Override
            public void onClick(int position, String name) {
                Intent intent
                        = new Intent(appHomeActivity, LjLayout.class);
                intent.putExtra("name", name);
                startActivity(intent);
            }
        });

    }

    private void initView() {
        itemChange = getView().findViewById(R.id.item_change);
        title = getView().findViewById(R.id.title);
        title1 = getView().findViewById(R.id.title1);
        ljzsGird = getView().findViewById(R.id.ljzs_gird);
    }
}
