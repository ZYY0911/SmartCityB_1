package com.example.smartcityb_1.dialog;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

import com.android.volley.VolleyError;
import com.example.smartcityb_1.R;
import com.example.smartcityb_1.net.VolleyLo;
import com.example.smartcityb_1.net.VolleyTo;
import com.example.smartcityb_1.util.OnUpDate;
import com.example.smartcityb_1.util.Utils;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import org.json.JSONObject;

import java.util.Date;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * @Login Name win10
 * @Create by 张瀛煜 on 2020/10/6 at 8:08
 */
public class CommitFragemnt extends BottomSheetDialogFragment {
    private ImageView ivClose;
    private ImageView ivSned;
    private EditText etSearch;
    private String id, userid;

    public CommitFragemnt(String id, String userid) {
        this.id = id;
        this.userid = userid;
    }

    private OnUpDate onUpDate;

    public void setOnUpDate(OnUpDate onUpDate) {
        this.onUpDate = onUpDate;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.commit_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
        ivClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        ivSned.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //{"id":"2","userid":"4","commitTime":"2020-10-3 10:00:00","commitContent":"666"
                VolleyTo volleyTo = new VolleyTo();
                volleyTo.setUrl("publicActionCommit")
                        .setJsonObject("id", id)
                        .setJsonObject("userid", userid)
                        .setJsonObject("commitTime", Utils.simpleDate("yyyy-MM-dd HH:mm:ss", new Date()))
                        .setJsonObject("commitContent", etSearch.getText().toString())
                        .setVolleyLo(new VolleyLo() {
                            @Override
                            public void onResponse(JSONObject jsonObject) {
                                Log.i("aaa", "onResponse: "+jsonObject.optString("RESULT"));
                                if (jsonObject.optString("RESULT").equals("S")) {
                                    onUpDate.upDate("S");
                                } else {
                                    onUpDate.upDate("F");
                                }
                                Log.i("aaa", "onResponse: "+jsonObject.optString("RESULT").equals("S"));
                                dismiss();
                            }

                            @Override
                            public void onErrorResponse(VolleyError volleyError) {
                                onUpDate.upDate("F");
                                Log.i("aaa", "onErrorResponse: "+volleyError.getMessage());
                                dismiss();
                            }
                        }).start();


            }
        });
    }

    private void initView() {
        ivClose = getView().findViewById(R.id.iv_close);
        ivSned = getView().findViewById(R.id.iv_sned);
        etSearch = getView().findViewById(R.id.et_search);
    }
}
