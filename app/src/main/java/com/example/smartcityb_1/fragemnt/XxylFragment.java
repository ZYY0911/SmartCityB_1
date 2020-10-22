package com.example.smartcityb_1.fragemnt;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.smartcityb_1.AppClient;
import com.example.smartcityb_1.R;
import com.example.smartcityb_1.activity.AppHomeActivity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

/**
 * @Login Name win10
 * @Create by 张瀛煜 on 2020/10/8 at 17:38
 */
public class XxylFragment extends Fragment {
    private ImageView itemChange;
    private TextView title;
    private TextView title1;
    private TextView tvMore;
    private EditText etName;
    private EditText etTel;
    private EditText etAddress;
    private EditText etTime;

    public XxylFragment() {

    }

    private AppHomeActivity appHomeActivity;

    public XxylFragment(AppHomeActivity appHomeActivity) {
        this.appHomeActivity = appHomeActivity;
    }

    public static XxylFragment newInstance(AppHomeActivity appHomeActivity) {
        return new XxylFragment(appHomeActivity);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return
                inflater.inflate(R.layout.ylxx_fragment, container, false);

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
        etName.setText(AppClient.name);
        etTel.setText(AppClient.tel);
        etAddress.setText(AppClient.address);
        etTime.setText(AppClient.time);
        tvMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppClient.name = etName.getText().toString();
                AppClient.tel = etTel.getText().toString();
                AppClient.address = etAddress.getText().toString();
                AppClient.time = etTime.getText().toString();
                Toast.makeText(getActivity(), "保存成功", Toast.LENGTH_SHORT).show();
            }
        });
        title.setText("信息预留");
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
        tvMore = getView().findViewById(R.id.tv_more);
        etName = getView().findViewById(R.id.et_name);
        etTel = getView().findViewById(R.id.et_tel);
        etAddress = getView().findViewById(R.id.et_address);
        etTime = getView().findViewById(R.id.et_time);
    }
}
