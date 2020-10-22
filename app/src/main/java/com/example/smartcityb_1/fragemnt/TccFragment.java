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
import com.example.smartcityb_1.activity.TccDetails;
import com.example.smartcityb_1.activity.TccHistoryActivity;
import com.example.smartcityb_1.adapter.TccAdapter;
import com.example.smartcityb_1.bean.TccInfo;
import com.example.smartcityb_1.net.VolleyLo;
import com.example.smartcityb_1.net.VolleyTo;
import com.example.smartcityb_1.util.Utils;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONObject;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

/**
 * @Login Name win10
 * @Create by 张瀛煜 on 2020/10/8 at 8:58
 */
public class TccFragment extends Fragment {
    private ListView listView;
    private TextView tvMore;
    private TextView title;
    private TextView title1;
    private ImageView itemChange;
    private ListView listViewTcc;

    public TccFragment() {
    }

    public TccFragment(AppHomeActivity appHomeActivity) {
        this.appHomeActivity = appHomeActivity;
    }

    private AppHomeActivity appHomeActivity;

    public static TccFragment newInstance(AppHomeActivity appHomeActivity) {
        return new TccFragment(appHomeActivity);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return
                inflater.inflate(R.layout.tcc_fragemnt, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
        title.setText("停车场");
        title1.setText("列表");
        itemChange.setVisibility(View.INVISIBLE);

        title1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(appHomeActivity, TccHistoryActivity.class));
            }
        });
        setVolley_getInfos();
        tvMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adapter.setCount(tccInfos.size());
                adapter.notifyDataSetChanged();
            }
        });
    }

    List<TccInfo> tccInfos;

    private void setVolley_getInfos() {
        VolleyTo volleyTo = new VolleyTo();
        volleyTo.setUrl("getParkInfor")
//                .setDialog(getActivity())
                .setVolleyLo(new VolleyLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        tccInfos = new Gson().fromJson(jsonObject.optJSONArray(Utils.Rows
                                ).toString()
                                , new TypeToken<List<TccInfo>>() {
                                }.getType());
                        setListViewAdapter();
                    }

                    @Override
                    public void onErrorResponse(VolleyError volleyError) {

                    }
                }).start();
    }

    TccAdapter adapter;
    Random random = new Random();

    private void setListViewAdapter() {
        Collections.sort(tccInfos, new Comparator<TccInfo>() {
            @Override
            public int compare(TccInfo o1, TccInfo o2) {
                return o1.getDistance() - o2.getDistance();
            }
        });
        adapter = new TccAdapter(getActivity(), tccInfos, (random.nextInt(2) + 1) % 2 == 0 ? 5 : 6);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(appHomeActivity, TccDetails.class);
                intent.putExtra("info", tccInfos.get(position));
                startActivity(intent);
            }
        });
    }

    private void initView() {
        listView = getView().findViewById(R.id.list_view_tcc);
        tvMore = getView().findViewById(R.id.tv_more);
        title = getView().findViewById(R.id.title);
        title1 = getView().findViewById(R.id.title1);
        itemChange = getView().findViewById(R.id.item_change);
        listViewTcc =getView(). findViewById(R.id.list_view_tcc);
    }
}
