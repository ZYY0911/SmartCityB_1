package com.example.smartcityb_1.fragemnt;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.example.smartcityb_1.R;
import com.example.smartcityb_1.activity.AppHomeActivity;
import com.example.smartcityb_1.adapter.HoAdapter;
import com.example.smartcityb_1.bean.AccoutGroup;
import com.example.smartcityb_1.bean.HoDetails;
import com.example.smartcityb_1.net.VolleyLo;
import com.example.smartcityb_1.net.VolleyTo;
import com.example.smartcityb_1.util.OnClickItem;
import com.example.smartcityb_1.util.Utils;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

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
 * @Create by 张瀛煜 on 2020/10/8 at 16:29
 */
public class HoManager extends Fragment {
    private ImageView itemChange;
    private TextView title;
    private TextView title1;
    private ExpandableListView hhExpand;
    private TextView addNew;

    public HoManager() {

    }

    private AppHomeActivity appHomeActivity;

    public HoManager(AppHomeActivity appHomeActivity) {
        this.appHomeActivity = appHomeActivity;
    }


    public static HoManager newInstance(AppHomeActivity appHomeActivity) {
        return new HoManager(appHomeActivity);
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.ho_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
        title.setText("户号管理");
        setVolley();
        itemChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                appHomeActivity.switchFragemnt(appHomeActivity.fragments.get(5)).commit();
            }
        });
        addNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                appHomeActivity.switchFragemnt(appHomeActivity.fragments.get(19)).commit();

            }
        });
        hhExpand.setGroupIndicator(null);

    }


    Map<AccoutGroup, List<HoDetails>> map;

    List<AccoutGroup> accoutGroups;

    public void setVolley() {
        VolleyTo volleyTo = new VolleyTo();
        volleyTo.setUrl("accountGroup")
                .setJsonObject("userid", 1)
                .setVolleyLo(new VolleyLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        accoutGroups = new Gson().fromJson(jsonObject.optJSONArray(Utils.Row).toString()
                                , new TypeToken<List<AccoutGroup>>() {
                                }.getType());
                        setVoley_DetaLi();
                    }

                    @Override
                    public void onErrorResponse(VolleyError volleyError) {

                    }
                }).start();
    }

    List<HoDetails> hoDetails;

    private void setVoley_DetaLi() {
        VolleyTo volleyTo = new VolleyTo();
        volleyTo.setUrl("accountByUserId")
                .setJsonObject("userid", 1)
                .setVolleyLo(new VolleyLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        hoDetails = new Gson().fromJson(jsonObject.optJSONArray(Utils.Row).toString()
                                , new TypeToken<List<HoDetails>>() {
                                }.getType());
                        setDate();
                    }

                    @Override
                    public void onErrorResponse(VolleyError volleyError) {

                    }
                }).start();

    }

    @Override
    public void onResume() {
        super.onResume();

    }

    private void setDate() {
        map = new HashMap<>();
        for (int i = 0; i < accoutGroups.size(); i++) {
            AccoutGroup accoutGroup = accoutGroups.get(i);
            List<HoDetails> detailsList = new ArrayList<>();
            for (int j = 0; j < hoDetails.size(); j++) {
                HoDetails hoDetail = hoDetails.get(j);
                if (hoDetail.getGroupId() == accoutGroup.getIndex()) {
                    detailsList.add(hoDetail);
                }
            }
            map.put(accoutGroup, detailsList);
        }
        setExpand_List();
    }

    HoAdapter adapter;

    private void setExpand_List() {
        adapter = new HoAdapter(map, accoutGroups);
        hhExpand.setAdapter(adapter);
        adapter.setOnClickItem(new OnClickItem() {
            @Override
            public void onClick(int position, String name) {

                appHomeActivity.switchFragemnt(appHomeActivity.fragments.get(20)).commit();
            }
        });
    }

    private void initView() {
        itemChange = getView().findViewById(R.id.item_change);
        title = getView().findViewById(R.id.title);
        title1 = getView().findViewById(R.id.title1);
        hhExpand = getView().findViewById(R.id.hh_expand);
        addNew = getView().findViewById(R.id.add_new);
    }
}

