package com.example.smartcityb_1.fragemnt;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.example.smartcityb_1.AppClient;
import com.example.smartcityb_1.R;
import com.example.smartcityb_1.activity.AppHomeActivity;
import com.example.smartcityb_1.adapter.MoOrderAdapter;
import com.example.smartcityb_1.bean.MyOrder;
import com.example.smartcityb_1.net.VolleyLo;
import com.example.smartcityb_1.net.VolleyTo;
import com.example.smartcityb_1.util.Utils;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

/**
 * @Login Name win10
 * @Create by 张瀛煜 on 2020/10/8 at 15:52
 */
public class MyOderActivity extends Fragment {

    private ImageView itemChange;
    private TextView title;
    private TextView title1;
    private TextView tvWzf;
    private TextView tvYzf;
    private ExpandableListView expandListOrder;

    public MyOderActivity() {
    }

    public MyOderActivity(AppHomeActivity appHomeActivity) {
        this.appHomeActivity = appHomeActivity;
    }

    private AppHomeActivity appHomeActivity;

    public static MyOderActivity newInstance(AppHomeActivity appHomeActivity) {
        return new MyOderActivity(appHomeActivity);
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return
                inflater.inflate(R.layout.my_order_fragemnt, container, false);


    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
        setVolley();
        title.setText("我的订单");
        itemChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                appHomeActivity.switchFragemnt(appHomeActivity.fragments.get(3)).commit();
            }
        });
        tvWzf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvWzf.setBackgroundResource(R.drawable.text_line);
                tvYzf.setBackgroundResource(R.drawable.text_no_line);
                index = 0;
                setAdapet();
            }
        });
        tvYzf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvWzf.setBackgroundResource(R.drawable.text_no_line);
                tvYzf.setBackgroundResource(R.drawable.text_line);
                index = 1;
                setAdapet();
            }
        });
    }

    List<MyOrder> myOrders;

    public void setVolley() {
        VolleyTo volleyTo = new VolleyTo();
        volleyTo.setUrl("getOrderBus")
                .setJsonObject("name", AppClient.username)
                .setVolleyLo(new VolleyLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        myOrders = new Gson().fromJson(jsonObject.optJSONArray(Utils.Row).toString()
                                , new TypeToken<List<MyOrder>>() {
                                }.getType());
                        Collections.sort(myOrders, new Comparator<MyOrder>() {
                            @Override
                            public int compare(MyOrder o1, MyOrder o2) {
                                return o2.getId()-o1.getId();
                            }
                        });
                        setAdapet();

                    }

                    @Override
                    public void onErrorResponse(VolleyError volleyError) {

                    }
                }).start();
    }

    int index = 0;

    private void setAdapet() {

        List<MyOrder> myOrderArrayList = new ArrayList<>();
        for (int i = 0; i < myOrders.size(); i++) {
            MyOrder myOrder = myOrders.get(i);
            if (index == 0) {
                if (myOrder.getIsPay().equals("N")) {
                    myOrderArrayList.add(myOrder);
                }
            } else {
                if (myOrder.getIsPay().equals("Y")) {
                    myOrderArrayList.add(myOrder);
                }
            }
        }
        expandListOrder.setAdapter(new MoOrderAdapter(myOrderArrayList));
    }

    private void initView() {
        itemChange = getView().findViewById(R.id.item_change);
        title = getView().findViewById(R.id.title);
        title1 = getView().findViewById(R.id.title1);
        tvWzf = getView().findViewById(R.id.tv_wzf);
        tvYzf = getView().findViewById(R.id.tv_yzf);

        expandListOrder = getView().findViewById(R.id.expand_list_order);
        expandListOrder.setGroupIndicator(null);
    }
}
