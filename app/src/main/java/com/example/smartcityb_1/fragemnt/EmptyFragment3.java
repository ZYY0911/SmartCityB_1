package com.example.smartcityb_1.fragemnt;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.example.smartcityb_1.R;
import com.example.smartcityb_1.activity.AppHomeActivity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

/**
 * @Login Name win10
 * @Create by 张瀛煜 on 2020/10/8 at 16:41
 */
public class EmptyFragment3 extends Fragment {
    private ImageView itemChange;
    private TextView title;
    private TextView title1;

    public EmptyFragment3() {
    }

    private AppHomeActivity appHomeActivity;

    public EmptyFragment3(AppHomeActivity appHomeActivity) {
        this.appHomeActivity = appHomeActivity;
    }

    public static EmptyFragment3 newInstance(AppHomeActivity appHomeActivity) {
        return new EmptyFragment3(appHomeActivity);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.emtpy_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
        title.setText("全部服务");
        itemChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                appHomeActivity.switchFragemnt(appHomeActivity.fragments.get(0)).commit();
            }
        });

    }

    private void initView() {
        itemChange =getView(). findViewById(R.id.item_change);
        title = getView(). findViewById(R.id.title);
        title1 = getView(). findViewById(R.id.title1);
    }
}
