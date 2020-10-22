package com.example.smartcityb_1.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.example.smartcityb_1.R;
import com.example.smartcityb_1.bean.SF;
import com.example.smartcityb_1.bean.UserInfos;
import com.example.smartcityb_1.net.VolleyLo;
import com.example.smartcityb_1.net.VolleyTo;
import com.example.smartcityb_1.util.Utils;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * @Login Name win10
 * @Create by 张瀛煜 on 2020/10/8 at 20:06
 */
public class DfADpter extends ArrayAdapter<SF> {

    public DfADpter(@NonNull Context context, @NonNull List<SF> objects) {
        super(context, 0, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        final ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.df_item, parent, false);
            holder = new ViewHolder();
            holder.itemDw = convertView.findViewById(R.id.item_dw);
            holder.itemHh = convertView.findViewById(R.id.item_hh);
            holder.itemHm = convertView.findViewById(R.id.item_hm);
            holder.itemAddress = convertView.findViewById(R.id.item_address);
            holder.itemYe = convertView.findViewById(R.id.item_ye);
            holder.itemQf = convertView.findViewById(R.id.item_qf);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        SF sf = getItem(position);
        holder.itemAddress.setText(sf.getAccountAddress());
        holder.itemDw.setText(sf.getUnit());
        holder.itemYe.setText(sf.getBanlance() + "");
        holder.itemQf.setText(sf.getCost() + "");
        holder.itemHh.setText(sf.getAccountId());
        VolleyTo volleyTo = new VolleyTo();
        volleyTo.setUrl("getUserInfo")
                .setJsonObject("userid", sf.getUserid())
                .setVolleyLo(new VolleyLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        UserInfos userInfos = new Gson().fromJson(jsonObject.optJSONArray(Utils.Rows).optJSONObject(0).toString()
                                , UserInfos.class);
                        holder.itemHm.setText(userInfos.getName());
                    }

                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        Log.i("aaa", "onErrorResponse: "+volleyError.getMessage());
                    }
                }).start();
        return convertView;
    }

    static class ViewHolder {

        private TextView itemDw;
        private TextView itemHh;
        private TextView itemHm;
        private TextView itemAddress;
        private TextView itemYe;
        private TextView itemQf;
    }

    private void initView() {
    }
}
