package com.example.smartcityb_1.fragemnt;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.example.smartcityb_1.R;
import com.example.smartcityb_1.activity.AppHomeActivity;
import com.example.smartcityb_1.activity.SFActivity;
import com.example.smartcityb_1.adapter.SFAdapter1;
import com.example.smartcityb_1.bean.HoDetails;
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
 * @Create by 张瀛煜 on 2020/10/9 at 7:43
 */
public class SFDragment1 extends Fragment {
    private ImageView itemChange;
    private TextView title;
    private TextView title1;
    private ListView sfList;

    public SFDragment1() {

    }

    private AppHomeActivity appHomeActivity;

    public SFDragment1(AppHomeActivity appHomeActivity) {
        this.appHomeActivity = appHomeActivity;
    }

    public static SFDragment1 newInstance(AppHomeActivity appHomeActivity) {
        return new SFDragment1(appHomeActivity);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.sf_fragment1, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
        title.setText("水费");
        itemChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                appHomeActivity.switchFragemnt(appHomeActivity.fragments.get(5)).commit();
            }
        });
        setVoley_DetaLi();
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
                        for (int i = hoDetails.size() - 1; i >= 0; i--) {
                            HoDetails hoDetail = hoDetails.get(i);
                            if (hoDetail.getType().equals("电费")) {
                                hoDetails.remove(i);
                            }
                        }
                        sfList.setAdapter(new SFAdapter1(getActivity(), hoDetails));
                        sfList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                Intent intent = new Intent(getActivity(), SFActivity.class);
                                intent.putExtra("date", hoDetails.get(position));
                                startActivity(intent);
                                //appHomeActivity.switchFragemnt(appHomeActivity.fragments.get(15)).commit();
                            }
                        });
                    }

                    @Override
                    public void onErrorResponse(VolleyError volleyError) {

                    }
                }).start();

    }

    private void initView() {
        itemChange = getView().findViewById(R.id.item_change);
        title = getView().findViewById(R.id.title);
        title1 = getView().findViewById(R.id.title1);
        sfList = getView().findViewById(R.id.sf_list);
    }
}
