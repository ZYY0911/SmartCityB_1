package com.example.smartcityb_1.fragemnt;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.example.smartcityb_1.R;
import com.example.smartcityb_1.activity.AppHomeActivity;
import com.example.smartcityb_1.activity.BusDetailsActivity;
import com.example.smartcityb_1.adapter.SmartBusAdapter;
import com.example.smartcityb_1.bean.BusStation;
import com.example.smartcityb_1.bean.BusTitle;
import com.example.smartcityb_1.net.VolleyLo;
import com.example.smartcityb_1.net.VolleyTo;
import com.example.smartcityb_1.util.OnClickItem;
import com.example.smartcityb_1.util.Utils;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

/**
 * @Login Name win10
 * @Create by 张瀛煜 on 2020/10/8 at 11:24
 */
public class SmartBus extends Fragment {

    private TextView title;
    private TextView title1;
    private ExpandableListView expandList;
    private ImageView itemChange;

    public SmartBus() {
    }

    public SmartBus(AppHomeActivity appHomeActivity) {
        this.appHomeActivity = appHomeActivity;
    }

    private AppHomeActivity appHomeActivity;

    public static SmartBus newInstance(AppHomeActivity appHomeActivity) {
        return new SmartBus(appHomeActivity);
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.smart_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
        title.setText("智慧巴士");
        title1.setText("我的订单");
        itemChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                appHomeActivity.backOnClick();
            }
        });
        setVolley();
        title1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyOderActivity activity = (MyOderActivity) appHomeActivity.fragments.get(4);
                activity.setVolley();
                appHomeActivity.switchFragemnt(appHomeActivity.fragments.get(4)).commit();
            }
        });
    }

    List<BusTitle> busTitles;
    Map<BusTitle, List<BusStation>> map;


    private void setVolley() {
        Log.i("aaa", "onErrorResponse: " );
        map = new HashMap<>();
        VolleyTo volleyTo = new VolleyTo();
        volleyTo.setUrl("busList")

//                .setDialog(getActivity())
                .setVolleyLo(new VolleyLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        busTitles = new Gson().fromJson(jsonObject.optJSONArray(Utils.Row).toString()
                                , new TypeToken<List<BusTitle>>() {
                                }.getType());
                        Log.i("aaa", "onResponse: busList" + busTitles.size());
                        setBusStation();
                    }

                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        Log.i("aaa", "onErrorResponse: " );

                    }
                }).start();
    }

    private void setBusStation() {
        for (int i = 0; i < busTitles.size(); i++) {
            final BusTitle busTitle = busTitles.get(i);
            VolleyTo volleyTo = new VolleyTo();
            volleyTo.setUrl("busStationById")
                    .setJsonObject("busid", busTitle.getBusid())
                    .setVolleyLo(new VolleyLo() {
                        @Override
                        public void onResponse(JSONObject jsonObject) {
                            List<BusStation> busStations = new Gson().fromJson(jsonObject.optJSONArray(Utils.Rows).toString()
                                    , new TypeToken<List<BusStation>>() {
                                    }.getType());
                            map.put(busTitle, busStations);
                            Log.i("aa", "onResponse: ");
                            if (map.size() == busTitles.size()) {
                                SmartBusAdapter adapter = new SmartBusAdapter(busTitles, map);
                                expandList.setAdapter(adapter);
                                adapter.setOnClickItem(new OnClickItem() {
                                    @Override
                                    public void onClick(int position, String name) {
                                        Intent intent = new Intent(getActivity(), BusDetailsActivity.class);
                                        intent.putExtra("date", busTitles.get(position));
                                        startActivity(intent);
                                    }
                                });
                            }
                        }

                        @Override
                        public void onErrorResponse(VolleyError volleyError) {

                        }
                    }).start();
        }
    }


    private void initView() {
        title = getView().findViewById(R.id.title);
        title1 = getView().findViewById(R.id.title1);
        expandList = getView().findViewById(R.id.expand_list);
        itemChange = getView().findViewById(R.id.item_change);
        expandList.setGroupIndicator(null);
    }
}
