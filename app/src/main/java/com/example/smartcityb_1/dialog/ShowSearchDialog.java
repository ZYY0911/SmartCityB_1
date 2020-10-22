package com.example.smartcityb_1.dialog;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.example.smartcityb_1.R;
import com.example.smartcityb_1.activity.AppHomeActivity;
import com.example.smartcityb_1.adapter.SearchServiceAdapter;
import com.example.smartcityb_1.bean.ServiceHome;
import com.example.smartcityb_1.net.VolleyLo;
import com.example.smartcityb_1.net.VolleyTo;
import com.example.smartcityb_1.util.OnUpDate;
import com.example.smartcityb_1.util.Utils;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

/**
 * @Login Name win10
 * @Create by 张瀛煜 on 2020/10/2 at 15:22
 */
public class ShowSearchDialog extends DialogFragment {
    private String value;
    private TextView tvFinish;
    private List<ServiceHome> serviceHomes;
    private GridView girdView;
    private AppHomeActivity appMainActivity;

    public ShowSearchDialog(String value, AppHomeActivity appMainActivity) {
        this.value = value;
        this.appMainActivity = appMainActivity;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.search_dialog_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
        setVolley_Service();

    }

    private void setVolley_Service() {
        VolleyTo volleyTo = new VolleyTo();
        volleyTo.setUrl("getServiceInOrder")
                .setJsonObject("order", 2)
                .setVolleyLo(new VolleyLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        serviceHomes = new Gson().fromJson(jsonObject.optJSONArray("ROWS_DETAIL").toString()
                                , new TypeToken<List<ServiceHome>>() {
                                }.getType());
                        setVolley();
                    }

                    @Override
                    public void onErrorResponse(VolleyError volleyError) {

                    }
                }).start();
    }

    private void setVolley() {
        for (int i = 0; i < serviceHomes.size(); i++) {
            final ServiceHome serviceHome = serviceHomes.get(i);
            VolleyTo volleyTo = new VolleyTo();
            final int finalI = i;
            volleyTo.setUrl("service_info")
                    .setJsonObject("serviceid", serviceHome.getId())
                    .setVolleyLo(new VolleyLo() {
                        @Override
                        public void onResponse(JSONObject jsonObject) {
                            JSONObject jsonObject1 = jsonObject.optJSONArray(Utils.Rows).optJSONObject(0);
                            serviceHome.setName(jsonObject1.optString("serviceName"));
                            serviceHome.setImage(jsonObject1.optString("icon"));
                            serviceHomes.set(finalI, serviceHome);
                            if (finalI == serviceHomes.size() - 1) {
                                setView();

                            }
                        }

                        @Override
                        public void onErrorResponse(VolleyError volleyError) {

                        }
                    }).start();
        }

    }

    private List<ServiceHome> searchList;

    private void setView() {
        searchList = new ArrayList<>();

        for (int i = 0; i < serviceHomes.size(); i++) {
            ServiceHome serviceHome = serviceHomes.get(i);
            try {
                if (serviceHome.getName().contains(value)) {
                    searchList.add(serviceHome);
                }
            } catch (Exception ex) {
                continue;
            }

        }

        SearchServiceAdapter searchServiceAdapter = new SearchServiceAdapter(getActivity(), searchList);
        girdView.setAdapter(searchServiceAdapter);
        searchServiceAdapter.setOnUpDate(new OnUpDate() {
            @Override
            public void upDate(String name) {
                if (name.equals("更多服务")) {
                    appMainActivity.replaceService();
                } else if (name.equals("新闻中心")) {
                    appMainActivity.replaceNews();
                }
            }
        });
        tvFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getDialog().dismiss();
            }
        });
    }

    private void initView() {
        tvFinish = getView().findViewById(R.id.tv_finish);
        girdView = getView().findViewById(R.id.gird_view);
    }
}
