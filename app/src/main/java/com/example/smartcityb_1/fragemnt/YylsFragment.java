package com.example.smartcityb_1.fragemnt;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.example.smartcityb_1.R;
import com.example.smartcityb_1.activity.AppHomeActivity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

/**
 * @Login Name win10
 * @Create by 张瀛煜 on 2020/10/8 at 17:34
 */
public class YylsFragment extends Fragment {
    private ImageView itemChange;
    private TextView title;
    private TextView title1;
    private LinearLayout layoutStart;
    private TextView etStart;
    private LinearLayout layoutEnd;
    private TextView etEnd;
    private Button btQuery;
    private ListView ljListView;

    public YylsFragment() {

    }

    private AppHomeActivity appHomeActivity;

    public YylsFragment(AppHomeActivity appHomeActivity) {
        this.appHomeActivity = appHomeActivity;
    }

    public static YylsFragment newInstance(AppHomeActivity appHomeActivity) {
        return new YylsFragment(appHomeActivity);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return
                inflater.inflate(R.layout.yyls_fragment, container, false);

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
        layoutStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        etStart.setText(year + "-" + (month + 1) + "-" + dayOfMonth);
                    }
                }, 2020, 9, 8);
                datePickerDialog.show();
            }
        });
        layoutEnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        etEnd.setText(year + "-" + (month + 1) + "-" + dayOfMonth);
                    }
                }, 2020, 9, 8);
                datePickerDialog.show();
            }
        });
        title.setText("预约回收垃圾历史");
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
        layoutStart = getView().findViewById(R.id.layout_start);
        etStart = getView().findViewById(R.id.et_start);
        layoutEnd = getView().findViewById(R.id.layout_end);
        etEnd = getView().findViewById(R.id.et_end);
        btQuery = getView().findViewById(R.id.bt_query);
    }
}
