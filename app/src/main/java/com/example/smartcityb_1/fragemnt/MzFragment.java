package com.example.smartcityb_1.fragemnt;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.example.smartcityb_1.R;
import com.example.smartcityb_1.activity.AppHomeActivity;
import com.example.smartcityb_1.activity.HospitalDetails;
import com.example.smartcityb_1.adapter.HospitalAdapter;
import com.example.smartcityb_1.bean.Hospitallist;
import com.example.smartcityb_1.net.VolleyLo;
import com.example.smartcityb_1.net.VolleyTo;
import com.example.smartcityb_1.util.Utils;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

/**
 * @Login Name win10
 * @Create by 张瀛煜 on 2020/10/6 at 9:47
 */
public class MzFragment extends Fragment {
    private AppHomeActivity appHomeActivity;
    private TextView itemChange;
    private EditText etSearch;
    private GridView girdView;

    public MzFragment(AppHomeActivity appHomeActivity) {
        this.appHomeActivity = appHomeActivity;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.mz_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
        itemChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                appHomeActivity.replace(new HomeFragment(appHomeActivity), true);
            }
        });
        setVolley();
        etSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (null != event && KeyEvent.KEYCODE_ENTER == event.getKeyCode()) {
                    switch (event.getAction()) {
                        case KeyEvent.ACTION_UP:
                            String msg = etSearch.getText().toString();
                            if (msg.equals("")) {
                                girdView.setAdapter(new HospitalAdapter(appHomeActivity, hospitallists));

                            } else {
                                List<Hospitallist> hospitallistList = new ArrayList<>();
                                for (int i = 0; i < hospitallists.size(); i++) {
                                    Hospitallist hospitallist = hospitallists.get(i);
                                    if (hospitallist.getHospitalName().contains(msg)) {
                                        hospitallistList.add(hospitallist);
                                    }
                                }
                                girdView.setAdapter(new HospitalAdapter(appHomeActivity, hospitallistList));
                            }
                            return true;
                        default:
                            return true;
                    }
                }
                return false;
            }
        });
        girdView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(appHomeActivity, HospitalDetails.class);
                intent.putExtra("index", hospitallists.get(position));
                startActivity(intent);
            }
        });
    }

    List<Hospitallist> hospitallists;

    private void setVolley() {
        VolleyTo volleyTo = new VolleyTo();
        volleyTo.setUrl("hospitalList")
                .setVolleyLo(new VolleyLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        hospitallists = new Gson().fromJson(jsonObject.optJSONArray(Utils.Rows).toString()
                                , new TypeToken<List<Hospitallist>>() {
                                }.getType());
                        girdView.setAdapter(new HospitalAdapter(appHomeActivity, hospitallists));
                    }

                    @Override
                    public void onErrorResponse(VolleyError volleyError) {

                    }
                }).start();
    }

    private void initView() {
        itemChange = getView().findViewById(R.id.item_change);
        etSearch = getView().findViewById(R.id.et_search);
        girdView = getView().findViewById(R.id.gird_view);
    }
}
