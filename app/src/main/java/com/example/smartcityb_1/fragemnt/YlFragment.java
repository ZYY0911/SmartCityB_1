package com.example.smartcityb_1.fragemnt;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.smartcityb_1.R;
import com.example.smartcityb_1.activity.AppHomeActivity;
import com.example.smartcityb_1.activity.HjjcActivity;
import com.example.smartcityb_1.activity.JzjcActivity;
import com.example.smartcityb_1.activity.ServiceFind;
import com.example.smartcityb_1.activity.YYYLYActivity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

/**
 * @Login Name win10
 * @Create by 张瀛煜 on 2020/10/6 at 10:13
 */
public class YlFragment extends Fragment {
    private LinearLayout layoutYyyl;
    private LinearLayout layoutHjjc;
    private LinearLayout layoutFwcx;
    private LinearLayout layoutJjjc;
    private AppHomeActivity appHomeActivity;

    public YlFragment(AppHomeActivity appHomeActivity) {
        this.appHomeActivity = appHomeActivity;
    }


    public static YlFragment newInstance(AppHomeActivity appHomeActivity) {
        return new YlFragment(appHomeActivity);
    }

    public YlFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.yl_fragement, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
        layoutYyyl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), YYYLYActivity.class));
            }
        });
        layoutHjjc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), HjjcActivity.class));
            }
        });
        layoutFwcx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), ServiceFind.class));
            }
        });
        layoutJjjc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), JzjcActivity.class));
            }
        });
    }

    private void initView() {
        layoutYyyl = getView().findViewById(R.id.layout_yyyl);
        layoutHjjc = getView().findViewById(R.id.layout_hjjc);
        layoutFwcx = getView().findViewById(R.id.layout_fwcx);
        layoutJjjc = getView().findViewById(R.id.layout_jjjc);
    }
}
