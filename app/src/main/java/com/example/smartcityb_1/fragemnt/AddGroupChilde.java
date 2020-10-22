package com.example.smartcityb_1.fragemnt;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.example.smartcityb_1.R;
import com.example.smartcityb_1.activity.AppHomeActivity;
import com.example.smartcityb_1.net.VolleyLo;
import com.example.smartcityb_1.net.VolleyTo;

import org.json.JSONObject;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

/**
 * @Login Name win10
 * @Create by 张瀛煜 on 2020/10/8 at 21:13
 */
public class AddGroupChilde extends Fragment {
    private ImageView itemChange;
    private TextView title;
    private TextView title1;
    private EditText etName;
    private Spinner etType;
    private TextView tvNext;

    public AddGroupChilde() {

    }

    private AppHomeActivity appHomeActivity;

    public AddGroupChilde(AppHomeActivity appHomeActivity) {
        this.appHomeActivity = appHomeActivity;
    }


    public static AddGroupChilde newInstance(AppHomeActivity appHomeActivity) {
        return new AddGroupChilde(appHomeActivity);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.add_chile, container, false);

    }


    @Override
    public void onHiddenChanged(boolean hidden) {

        if (hidden){

        }else {

        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
        title.setText("新增缴费");
        itemChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                appHomeActivity.switchFragemnt(appHomeActivity.fragments.get(16)).commit();
            }
        });
        tvNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                VolleyTo volleyTo = new VolleyTo();
                volleyTo.setUrl("charge")
                        //{"userid":"1","type":2,accountId:"84688468"}
                        .setJsonObject("userid", 1)
                        .setJsonObject("type", etType.getSelectedItemId()+1)
                        .setJsonObject("accountId", etName.getText().toString())
                        .setVolleyLo(new VolleyLo() {
                            @Override
                            public void onResponse(JSONObject jsonObject) {
                                if (jsonObject.optString("RESULT").equals("S")) {
                                    Toast.makeText(getActivity(), "添加成功", Toast.LENGTH_SHORT).show();
                                    HoManager hoManager = (HoManager) appHomeActivity.fragments.get(16);
                                    hoManager.setVolley();
                                    appHomeActivity.switchFragemnt(appHomeActivity.fragments.get(16)).commit();
                                } else {
                                    Toast.makeText(getActivity(), "添加失败", Toast.LENGTH_SHORT).show();
                                }
                            }

                            @Override
                            public void onErrorResponse(VolleyError volleyError) {
                                Toast.makeText(getActivity(), "添加失败", Toast.LENGTH_SHORT).show();

                            }
                        }).start();
            }
        });

    }

    private void initView() {
        itemChange = getView().findViewById(R.id.item_change);
        title = getView().findViewById(R.id.title);
        title1 = getView().findViewById(R.id.title1);
        etName = getView().findViewById(R.id.et_name);
        etType = getView().findViewById(R.id.et_type);
        tvNext = getView().findViewById(R.id.tv_next);
    }
}
