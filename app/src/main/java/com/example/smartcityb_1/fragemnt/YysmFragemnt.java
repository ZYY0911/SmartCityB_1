package com.example.smartcityb_1.fragemnt;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.smartcityb_1.R;
import com.example.smartcityb_1.activity.AppHomeActivity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

/**
 * @Login Name win10
 * @Create by 张瀛煜 on 2020/10/8 at 17:21
 */
public class YysmFragemnt extends Fragment {
    private ImageView itemChange;
    private TextView title;
    private TextView title1;
    private LinearLayout layoutTeim;
    private TextView tvNext;
    private TextView tvSj;

    public YysmFragemnt() {

    }

    private AppHomeActivity appHomeActivity;

    public YysmFragemnt(AppHomeActivity appHomeActivity) {
        this.appHomeActivity = appHomeActivity;
    }

    public static YysmFragemnt newInstance(AppHomeActivity appHomeActivity) {
        return new YysmFragemnt(appHomeActivity);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return
                inflater.inflate(R.layout.yysm_layout, container, false);

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
        layoutTeim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        tvSj.setText(year + "-" + (month + 1) + "-" + dayOfMonth);
                    }
                }, 2020, 9, 8);
                datePickerDialog.show();
            }
        });
        tvNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "预约成功", Toast.LENGTH_SHORT).show();
                appHomeActivity.switchFragemnt(appHomeActivity.fragments.get(6)).commit();

            }
        });
        title.setText("预约上门回收垃圾");
        itemChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                appHomeActivity.switchFragemnt(appHomeActivity.fragments.get(6)).commit();
            }
        });
    }

    private void initView() {
        itemChange = getView().findViewById(R.id.item_change);
        title = getView().findViewById(R.id.title);
        title1 = getView().findViewById(R.id.title1);
        layoutTeim = getView().findViewById(R.id.layout_teim);
        tvNext = getView().findViewById(R.id.tv_next);
        tvSj = getView().findViewById(R.id.tv_sj);
    }
}
