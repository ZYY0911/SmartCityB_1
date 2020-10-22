package com.example.smartcityb_1.fragemnt;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.example.smartcityb_1.R;
import com.example.smartcityb_1.activity.AllServiceUrl;
import com.example.smartcityb_1.activity.AppHomeActivity;
import com.example.smartcityb_1.activity.HdActivity;
import com.example.smartcityb_1.activity.TrafficActivity;
import com.example.smartcityb_1.adapter.AllServiceAdapter;
import com.example.smartcityb_1.bean.ServiceInfo;
import com.example.smartcityb_1.dialog.ShowSearchDialog;
import com.example.smartcityb_1.net.VolleyLo;
import com.example.smartcityb_1.net.VolleyTo;
import com.example.smartcityb_1.util.OnClickItem;
import com.example.smartcityb_1.util.OnUpDate;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

/**
 * @Login Name win10
 * @Create by 张瀛煜 on 2020/10/2 at 11:45
 */
public class AllServiceFragemnt extends Fragment {
    private EditText etSearch;
    private ExpandableListView expandListView;
    private AppHomeActivity appHomeActivity;

    public AllServiceFragemnt(AppHomeActivity appHomeActivity) {
        this.appHomeActivity = appHomeActivity;
    }

    public AllServiceFragemnt() {
    }

    public static AllServiceFragemnt newInstance(AppHomeActivity appHomeActivity) {
        return new AllServiceFragemnt(appHomeActivity);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.all_service_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
        setVolley();
        etSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (null != event && KeyEvent.KEYCODE_ENTER == event.getKeyCode()) {
                    switch (event.getAction()) {
                        case KeyEvent.ACTION_UP:
                            ShowSearchDialog dialog = new ShowSearchDialog(etSearch.getText().toString(), appHomeActivity);
                            dialog.show(getChildFragmentManager(), "aaa");
                            return true;
                        default:
                            return true;
                    }
                }
                return false;
            }
        });
    }

    private List<String> serviceType;
    private Map<String, List<ServiceInfo>> map;

    private void setVolley() {
        map = new HashMap<>();
        serviceType = new ArrayList<>();
        VolleyTo volleyTo = new VolleyTo();
        volleyTo.setUrl("getAllServiceType")
                .setVolleyLo(new VolleyLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        JSONArray jsonArray = jsonObject.optJSONArray("ROWS_DETAIL");
                        for (int i = 0; i < jsonArray.length(); i++) {
                            serviceType.add(jsonArray.optString(i));
                        }
                        setVolley_Detail();
                    }

                    @Override
                    public void onErrorResponse(VolleyError volleyError) {

                    }
                }).start();
    }

    private void setVolley_Detail() {
        for (int i = 0; i < serviceType.size(); i++) {
            VolleyTo volleyTo = new VolleyTo();
            final int finalI = i;
            volleyTo.setUrl("getServiceByType")
                    .setJsonObject("serviceType", serviceType.get(i))
                    .setVolleyLo(new VolleyLo() {
                        @Override
                        public void onResponse(JSONObject jsonObject) {
                            List<ServiceInfo> serviceInfos = new Gson().fromJson(jsonObject.optJSONArray("ROWS_DETAIL").toString()
                                    , new TypeToken<List<ServiceInfo>>() {
                                    }.getType());
                            map.put(serviceType.get(finalI), serviceInfos);
                            if (map.size() == serviceType.size()) {
                                setExpandListView();
                            }
                        }

                        @Override
                        public void onErrorResponse(VolleyError volleyError) {

                        }
                    }).start();
        }
    }

    private void setExpandListView() {
        AllServiceAdapter allServiceAdapter = new AllServiceAdapter(serviceType, map);
        expandListView.setAdapter(allServiceAdapter);
        allServiceAdapter.setOnClickItem(new OnClickItem() {
            @Override
            public void onClick(int position, String name) {
                if (name.equals("活动")) {
                    startActivity(new Intent(appHomeActivity, HdActivity.class));
                } else if (name.equals("违章查询")) {
                    appHomeActivity.switchFragemnt(appHomeActivity.fragments.get(24)).commit();
                } else if (name.equals("门诊预约")) {
                    appHomeActivity.switchFragemnt(appHomeActivity.fragments.get(23)).commit();
                } else if (name.equals("更多服务")) {
                    appHomeActivity.switchFragemnt(appHomeActivity.fragments.get(25)).commit();
                } else if (name.equals("城市巴士")) {
                    appHomeActivity.switchFragemnt(appHomeActivity.fragments.get(3)).commit();
                } else if (name.equals("停车场")) {
                    appHomeActivity.switchFragemnt(appHomeActivity.fragments.get(1)).commit();
                } else if (name.equals("生活缴费")) {
                    appHomeActivity.switchFragemnt(appHomeActivity.fragments.get(5)).commit();
                } else if (name.equals("地铁查询")) {
                    startActivity(new Intent(getActivity(), TrafficActivity.class));
                }else if (name.equals("新闻中心")){
                    appHomeActivity.switchFragemnt(appHomeActivity.fragments.get(26)).commit();

                }


                else {
                    Intent intent = new Intent(appHomeActivity, AllServiceUrl.class);
                    intent.putExtra("infos", position);
                    intent.putExtra("date", name);
                    startActivity(intent);
                }
            }
        });

    }

    private void initView() {
        etSearch = getView().findViewById(R.id.et_search);
        expandListView = getView().findViewById(R.id.expand_list_view);
        expandListView.setGroupIndicator(null);
    }
}
