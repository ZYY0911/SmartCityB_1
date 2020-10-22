package com.example.smartcityb_1.fragemnt;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.example.smartcityb_1.R;
import com.example.smartcityb_1.activity.AppHomeActivity;
import com.example.smartcityb_1.adapter.DfADpter;
import com.example.smartcityb_1.bean.SF;
import com.example.smartcityb_1.net.VolleyLo;
import com.example.smartcityb_1.net.VolleyTo;
import com.example.smartcityb_1.util.Utils;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONObject;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

/**
 * @Login Name win10
 * @Create by 张瀛煜 on 2020/10/8 at 20:02
 */
public class DFFragement extends Fragment {

    private ImageView itemChange;
    private TextView title;
    private TextView title1;
    private ListView dfList;

    public DFFragement() {
    }

    public DFFragement(AppHomeActivity appHomeActivity) {
        this.appHomeActivity = appHomeActivity;
    }

    private AppHomeActivity appHomeActivity;

    public static DFFragement newInstance(AppHomeActivity appHomeActivity) {
        return new DFFragement(appHomeActivity);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.df_fragment, container, false);

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
        title.setText("电费");
        itemChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                appHomeActivity.switchFragemnt(appHomeActivity.fragments.get(5)).commit();
            }
        });
        setVolley();
    }

    List<SF> sfs;


    private void setVolley() {
        VolleyTo volleyTo = new VolleyTo();
        volleyTo.setUrl("chargeList")
                .setJsonObject("userid", 1)
                .setJsonObject("type", 2)
                .setVolleyLo(new VolleyLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        sfs = new Gson().fromJson(jsonObject.optJSONArray(Utils.Row).toString()
                                , new TypeToken<List<SF>>() {
                                }.getType());
                        setDfAdapter();
                    }

                    @Override
                    public void onErrorResponse(VolleyError volleyError) {

                    }
                }).start();
    }

    private void setDfAdapter() {
        dfList.setAdapter(new DfADpter(getActivity(), sfs));

    }


    private void initView() {
        itemChange = getView().findViewById(R.id.item_change);
        title = getView().findViewById(R.id.title);
        title1 = getView().findViewById(R.id.title1);
        dfList = getView().findViewById(R.id.df_list);
    }
}
