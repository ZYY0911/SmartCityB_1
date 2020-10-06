package com.example.smartcityb_1.fragemnt;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.example.smartcityb_1.R;
import com.example.smartcityb_1.activity.AppHomeActivity;
import com.example.smartcityb_1.bean.CarType;
import com.example.smartcityb_1.net.VolleyLo;
import com.example.smartcityb_1.net.VolleyTo;
import com.example.smartcityb_1.util.Utils;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

/**
 * @Login Name win10
 * @Create by 张瀛煜 on 2020/10/6 at 8:35
 */
public class CarFragment extends Fragment {
    private TextView itemChange;
    private TextView title;
    private TextView title1;
    private Spinner carType;
    private Spinner carNum;
    private EditText etNum;

    private AppHomeActivity appHomeActivity;
    private Button btQuery;

    public CarFragment(AppHomeActivity appHomeActivity) {
        this.appHomeActivity = appHomeActivity;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.wz_layout, container, false);
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
        setVolley_CarType();
        title.setText("违章查询");
        itemChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                appHomeActivity.replace(new HomeFragment(appHomeActivity),true);
            }
        });
    }

    List<CarType> carTypes;

    private void setVolley_CarType() {
        VolleyTo volleyTo = new VolleyTo();
        volleyTo.setUrl("getAllCarType")
                .setVolleyLo(new VolleyLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        carTypes = new Gson().fromJson(jsonObject.optJSONArray(Utils.Rows).toString()
                                , new TypeToken<List<CarType>>() {
                                }.getType());
                        List<String> strings = new ArrayList<>();
                        for (int i = 0; i < carTypes.size(); i++) {
                            strings.add(carTypes.get(i).getName());
                        }
                        carType.setAdapter(new ArrayAdapter<String>(appHomeActivity, android.R.layout.simple_list_item_1, strings));
                    }

                    @Override
                    public void onErrorResponse(VolleyError volleyError) {

                    }
                }).start();
        btQuery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String cp = carNum.getSelectedItem().toString() + etNum.getText().toString().toUpperCase();
                if (cp.length() == 7) {
//                    VolleyTo volleyTo1 = new VolleyTo();
//                    volleyTo1.setUrl("getViolationsByCarId")
//                            //{carid:"鲁A10001"}
//                            .setJsonObject("carid",cp)
//                            .setVolleyLo(new VolleyLo() {
//                                @Override
//                                public void onResponse(JSONObject jsonObject) {
//
//
//                                }
//
//                                @Override
//                                public void onErrorResponse(VolleyError volleyError) {
//
//                                }
//                            }).start();
                    appHomeActivity.replace(new WzcxFragment(appHomeActivity,cp),false);
                } else {
                    Utils.showDialog("车牌号不正确", appHomeActivity);
                    return;
                }
            }
        });
    }

    private void initView() {
        itemChange = getView().findViewById(R.id.item_change);
        title = getView().findViewById(R.id.title);
        title1 = getView().findViewById(R.id.title1);
        carType = getView().findViewById(R.id.car_type);
        carNum = getView().findViewById(R.id.car_num);
        etNum = getView().findViewById(R.id.et_num);
        btQuery = getView().findViewById(R.id.bt_query);
    }
}
