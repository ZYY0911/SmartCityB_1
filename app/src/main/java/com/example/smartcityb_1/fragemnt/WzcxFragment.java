package com.example.smartcityb_1.fragemnt;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.example.smartcityb_1.R;
import com.example.smartcityb_1.activity.AppHomeActivity;
import com.example.smartcityb_1.adapter.WzcxAdapter;
import com.example.smartcityb_1.bean.CarPail;
import com.example.smartcityb_1.net.VolleyLo;
import com.example.smartcityb_1.net.VolleyTo;
import com.example.smartcityb_1.util.Utils;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONObject;

import java.util.List;
import java.util.Random;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

/**
 * @Login Name win10
 * @Create by 张瀛煜 on 2020/10/6 at 9:04
 */
public class WzcxFragment extends Fragment {
    private AppHomeActivity appHomeActivity;
    public String Cp;
    private TextView itemChange;
    private TextView title;
    private TextView title1;
    private ListView listView;
    private Button btQuery;

    public WzcxFragment(AppHomeActivity appHomeActivity) {
        this.appHomeActivity = appHomeActivity;
    }

    public WzcxFragment() {
    }

    public static WzcxFragment newInstance(AppHomeActivity appHomeActivity) {
        return new WzcxFragment(appHomeActivity);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.wzcx_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
        setVolley_info();
    }


    @Override
    public void onHiddenChanged(boolean hidden) {
        Log.i("aaa", "onHiddenChanged: " + hidden);
        if (!hidden) {
            setVolley_info();
            Log.i("ddd", "onHiddenChanged: ");
        }
    }

    List<CarPail> carPails;

    private void setVolley_info() {
        VolleyTo volleyTo1 = new VolleyTo();
        volleyTo1.setUrl("getViolationsByCarId")
                .setJsonObject("carid", Cp)
                .setVolleyLo(new VolleyLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        carPails = new Gson().fromJson(jsonObject.optJSONArray(Utils.Rows).toString()
                                , new TypeToken<List<CarPail>>() {
                                }.getType());
                        setListView();
                    }

                    @Override
                    public void onErrorResponse(VolleyError volleyError) {

                    }
                }).start();
    }

    WzcxAdapter adapter;


    private void setListView() {
        Random random = new Random();
        int count = ((random.nextInt(2) + 1) % 2 == 0 ? 5 : 6);
        if (carPails.size() < 5) {
            count = carPails.size();
        }

        adapter = new WzcxAdapter(appHomeActivity, carPails, count);
        listView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        btQuery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adapter.getMyCunt(carPails.size());
                adapter.notifyDataSetChanged();
            }
        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                WzDetailsFragement wzDetailsFragement = (WzDetailsFragement) appHomeActivity.fragments.get(29);
                wzDetailsFragement.carPail = carPails.get(position);
                appHomeActivity.switchFragemnt(appHomeActivity.fragments.get(29)).commit();
            }
        });
        itemChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                appHomeActivity.switchFragemnt(appHomeActivity.fragments.get(24)).commit();
            }
        });
        title.setText("违章查询");
    }

    private void initView() {
        itemChange = getView().findViewById(R.id.item_change);
        title = getView().findViewById(R.id.title);
        title1 = getView().findViewById(R.id.title1);
        listView = getView().findViewById(R.id.list_view);
        btQuery = getView().findViewById(R.id.bt_query);
    }
}

