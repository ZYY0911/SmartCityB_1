package com.example.smartcityb_1.fragemnt;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.example.smartcityb_1.AppClient;
import com.example.smartcityb_1.R;
import com.example.smartcityb_1.activity.MainActivity;
import com.example.smartcityb_1.activity.MotifPwdActivity;
import com.example.smartcityb_1.activity.MyInfosActivity;
import com.example.smartcityb_1.activity.OrderActivity;
import com.example.smartcityb_1.activity.YjfkActivity;
import com.example.smartcityb_1.bean.UserInfos;
import com.example.smartcityb_1.bean.UserNum;
import com.example.smartcityb_1.net.VolleyImage;
import com.example.smartcityb_1.net.VolleyLo;
import com.example.smartcityb_1.net.VolleyLoImage;
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
 * @Create by 张瀛煜 on 2020/10/5 at 20:07
 */
public class MyCenterFragment extends Fragment {
    private ImageView ivImage;
    private TextView tvName;
    private TextView tvTel;
    private LinearLayout lauyoutInfo;
    private LinearLayout layoutOrder;
    private LinearLayout layoutPwd;
    private LinearLayout layoutSubmit;
    private TextView tvExit;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.my_center_framgent, container, false);

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
        lauyoutInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), MyInfosActivity.class);
                intent.putExtra("date", userInfos);
                startActivity(intent);
            }
        });
        layoutPwd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), MotifPwdActivity.class);
                intent.putExtra("date", userInfos);
                startActivity(intent);
            }
        });
        layoutOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), OrderActivity.class);
                intent.putExtra("date", userInfos);
                startActivity(intent);

            }
        });
        layoutSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), YjfkActivity.class);
                intent.putExtra("date", userInfos);
                startActivity(intent);
            }
        });
        tvExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), MainActivity.class));
                AppClient.finallAll();
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        setVolley();
    }

    List<UserNum> userNums;

    private void setVolley() {
        VolleyTo volleyTo = new VolleyTo();
        volleyTo.setUrl("getUsers")
                .setVolleyLo(new VolleyLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        userNums = new Gson().fromJson(jsonObject.optJSONArray(Utils.Rows).toString()
                                , new TypeToken<List<UserNum>>() {
                                }.getType());
                        setVolleyInfos();
                    }

                    @Override
                    public void onErrorResponse(VolleyError volleyError) {

                    }
                }).start();
    }

    UserInfos userInfos;

    private void setVolleyInfos() {
        VolleyTo volleyTo = new VolleyTo();
        volleyTo.setUrl("getUserInfo")
                .setJsonObject("userid", getUserName(AppClient.username))
                .setVolleyLo(new VolleyLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        userInfos = new Gson().fromJson(jsonObject.optJSONArray(Utils.Rows).optJSONObject(0).toString(), UserInfos.class);
                        tvName.setText(userInfos.getName());
                        tvTel.setText(userInfos.getPhone());
                        userInfos.setUserid(getUserName(AppClient.username));
                        VolleyImage volleyImage = new VolleyImage();
                        volleyImage.setUrl(userInfos.getAvatar())
                                .setVolleyLoImage(new VolleyLoImage() {
                                    @Override
                                    public void onResponse(Bitmap bitmap) {
                                        ivImage.setImageBitmap(bitmap);
                                    }

                                    @Override
                                    public void onErrorResponse(VolleyError volleyError) {

                                    }
                                }).start();
                    }

                    @Override
                    public void onErrorResponse(VolleyError volleyError) {

                    }
                }).start();
    }


    private String getUserName(String username) {
        for (int i = 0; i < userNums.size(); i++) {
            UserNum userNum = userNums.get(i);
            if (userNum.getUsername().equals(username)) {
                return userNum.getUserid();
            }
        }
        return "1";
    }

    private void initView() {
        ivImage = getView().findViewById(R.id.iv_image);
        tvName = getView().findViewById(R.id.tv_name);
        tvTel = getView().findViewById(R.id.tv_tel);
        lauyoutInfo = getView().findViewById(R.id.lauyout_info);
        layoutOrder = getView().findViewById(R.id.layout_order);
        layoutPwd = getView().findViewById(R.id.layout_pwd);
        layoutSubmit = getView().findViewById(R.id.layout_submit);
        tvExit = getView().findViewById(R.id.tv_exit);
    }
}
