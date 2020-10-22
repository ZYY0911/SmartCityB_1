package com.example.smartcityb_1.fragemnt;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
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
 * @Create by 张瀛煜 on 2020/10/8 at 20:31
 */
public class AadGroype extends Fragment {
    private ImageView itemChange;
    private TextView title;
    private TextView title1;
    private RadioButton rbMy;
    private RadioButton rbFm;
    private RadioButton rbPy;
    private RadioButton rbFd;
    private RadioButton rbQt;
    private EditText etMsg;
    private TextView tvNext;

    public AadGroype() {

    }

    private AppHomeActivity appHomeActivity;

    public AadGroype(AppHomeActivity appHomeActivity) {
        this.appHomeActivity = appHomeActivity;
    }


    public static AadGroype newInstance(AppHomeActivity appHomeActivity) {
        return new AadGroype(appHomeActivity);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.add_groupe, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
        title.setText("新增分组");
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
                volleyTo.setUrl("setAccountGroup")
                        //{"userid":"1","groupName":"我家"}
                        .setJsonObject("userid", 1)
                        .setJsonObject("groupName", rbFd.isChecked() ? rbFd.getText() : rbFm.isChecked() ? rbFm.getText()
                                : rbMy.isChecked() ? rbMy.getText() : rbPy.isChecked() ? rbPy.getText() : rbQt.isChecked() ? etMsg.getText() : "")
                        .setVolleyLo(new VolleyLo() {
                            @Override
                            public void onResponse(JSONObject jsonObject) {
                                Toast.makeText(getActivity(), "添加成功", Toast.LENGTH_SHORT).show();
                                HoManager hoManager = (HoManager) appHomeActivity.fragments.get(16);
                                hoManager.setVolley();
                                appHomeActivity.switchFragemnt(appHomeActivity.fragments.get(16)).commit();

                            }

                            @Override
                            public void onErrorResponse(VolleyError volleyError) {

                            }
                        }).start();

            }
        });
    }

    private void initView() {
        itemChange = getView().findViewById(R.id.item_change);
        title = getView().findViewById(R.id.title);
        title1 = getView().findViewById(R.id.title1);
        rbMy = getView().findViewById(R.id.rb_my);
        rbFm = getView().findViewById(R.id.rb_fm);
        rbPy = getView().findViewById(R.id.rb_py);
        rbFd = getView().findViewById(R.id.rb_fd);
        rbQt = getView().findViewById(R.id.rb_qt);
        etMsg = getView().findViewById(R.id.et_msg);
        tvNext = getView().findViewById(R.id.tv_next);
    }
}
