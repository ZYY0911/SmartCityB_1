package com.example.smartcityb_1.fragemnt;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.smartcityb_1.R;
import com.example.smartcityb_1.activity.AppHomeActivity;
import com.example.smartcityb_1.bean.CarPail;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

/**
 * @Login Name win10
 * @Create by 张瀛煜 on 2020/10/6 at 9:19
 */
public class WzDetailsFragement extends Fragment {
    private AppHomeActivity appHomeActivity;
    public CarPail carPail;
    private TextView itemChange;
    private TextView title;
    private TextView title1;
    private TextView tvTime;
    private TextView tvLocation;
    private TextView tvAction;
    private TextView tvTzsh;
    private TextView tvKf;
    private TextView tvMoney;


    public WzDetailsFragement(AppHomeActivity appHomeActivity) {
        this.appHomeActivity = appHomeActivity;
    }

    public WzDetailsFragement() {
    }

    public static WzDetailsFragement newInstance(AppHomeActivity appHomeActivity) {
        return new WzDetailsFragement(appHomeActivity);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.wzcx_details_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
        title.setText("违章详情");
        itemChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                appHomeActivity.switchFragemnt(appHomeActivity.fragments.get(28)).commit();
            }
        });
        tvTime.setText(carPail.getTime());
        tvLocation.setText(carPail.getPlace());
        tvAction.setText(carPail.getDescription());
        tvTzsh.setText(carPail.getNotification());
        tvKf.setText(carPail.getDeductPoints() + "");
        tvMoney.setText(carPail.getCost() + "");

    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        if (!hidden){
            tvTime.setText(carPail.getTime());
            tvLocation.setText(carPail.getPlace());
            tvAction.setText(carPail.getDescription());
            tvTzsh.setText(carPail.getNotification());
            tvKf.setText(carPail.getDeductPoints() + "");
            tvMoney.setText(carPail.getCost() + "");
        }
    }

    private void initView() {
        itemChange = getView().findViewById(R.id.item_change);
        title = getView().findViewById(R.id.title);
        title1 = getView().findViewById(R.id.title1);
        tvTime = getView().findViewById(R.id.tv_time);
        tvLocation = getView().findViewById(R.id.tv_location);
        tvAction = getView().findViewById(R.id.tv_action);
        tvTzsh = getView().findViewById(R.id.tv_tzsh);
        tvKf = getView().findViewById(R.id.tv_kf);
        tvMoney = getView().findViewById(R.id.tv_money);
        title.setText("违章详情");
    }
}
