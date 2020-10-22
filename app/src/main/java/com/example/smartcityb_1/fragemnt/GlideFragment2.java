package com.example.smartcityb_1.fragemnt;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.example.smartcityb_1.R;
import com.example.smartcityb_1.activity.AppHomeActivity;
import com.example.smartcityb_1.activity.MainActivity;
import com.example.smartcityb_1.activity.NetActivity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

/**
 * @Login Name win10
 * @Create by 张瀛煜 on 2020/10/9 at 9:04
 */
public class GlideFragment2 extends Fragment {
    private ImageView itemImage;
    private int resource;
    private boolean isShow;
    private Button btSetting;
    private Button btMain;

    public GlideFragment2(int resource, boolean isShow) {
        this.resource = resource;
        this.isShow = isShow;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_main, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
        itemImage.setImageResource(resource);
        btMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), AppHomeActivity.class));
                getActivity().finish();
            }
        });
        btSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), NetActivity.class));
            }
        });

    }

    private void initView() {
        itemImage = getView().findViewById(R.id.item_image);
        btSetting =  getView().findViewById(R.id.bt_setting);
        btMain = getView(). findViewById(R.id.bt_main);
    }
}
