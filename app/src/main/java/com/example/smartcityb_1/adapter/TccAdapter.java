package com.example.smartcityb_1.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;


import com.example.smartcityb_1.R;
import com.example.smartcityb_1.bean.TccInfo;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * @Login Name win10
 * @Create by 张瀛煜 on 2020/10/8 at 9:03
 */
public class TccAdapter extends ArrayAdapter<TccInfo> {

    private int count;

    public TccAdapter(@NonNull Context context, @NonNull List<TccInfo> objects, int count) {
        super(context, 0, objects);
        this.count = count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public int getCount() {
        return count;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.tcc_item, parent, false);
            holder = new ViewHolder();
            holder.itemName = convertView.findViewById(R.id.item_name);
            holder.itemKwsl = convertView.findViewById(R.id.item_kwsl);
            holder.itemDz = convertView.findViewById(R.id.item_dz);
            holder.itemSffl = convertView.findViewById(R.id.item_sffl);
            holder.itemJl = convertView.findViewById(R.id.item_jl);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        TccInfo tccInfo = getItem(position);
        //  {
        //            "parkingid": 1,
        //            "parkName": "德百停车场",
        //            "spaceNum": 500,
        //            "address": "湖滨中大道708号",
        //            "rate": "3元/小时",
        //            "distance": 500,
        //            "isOpen": "Y",
        //            "surCarPort": 200,
        //            "rateRefer": "3元/小时  一天最高40元"
        //        },
        holder.itemName.setText("停车场：" + tccInfo.getParkName());
        holder.itemKwsl.setText("空位数量：" + tccInfo.getSurCarPort());
        holder.itemDz.setText("地址：" + tccInfo.getAddress());
        holder.itemSffl.setText("收费费率：" + tccInfo.getRate());
        holder.itemJl.setText("距离：" + tccInfo.getDistance());


        return convertView;
    }

    static class ViewHolder {

        private TextView itemName;
        private TextView itemKwsl;
        private TextView itemDz;
        private TextView itemSffl;
        private TextView itemJl;
    }

    private void initView() {
    }
}
