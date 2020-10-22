package com.example.smartcityb_1.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;


import com.example.smartcityb_1.R;
import com.example.smartcityb_1.bean.SubwayStation;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * @Login Name win10
 * @Create by 张瀛煜 on 2020/10/2 at 9:11
 */
public class SubwayStationAdapter extends ArrayAdapter<SubwayStation> {




    public SubwayStationAdapter(@NonNull Context context, @NonNull List<SubwayStation> objects) {
        super(context, 0, objects);
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.traffic_item, parent, false);
            holder = new ViewHolder();
            holder.itemStation = convertView.findViewById(R.id.item_station);
            holder.itemLineName = convertView.findViewById(R.id.item_line_name);
            holder.itemNextStation = convertView.findViewById(R.id.item_next_station);
            holder.itemTime = convertView.findViewById(R.id.item_time);
            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }
        SubwayStation subwayStation = getItem(position);
        holder.itemTime.setText(subwayStation.getTime()+"分钟");
        holder.itemLineName.setText(subwayStation.getName());
        holder.itemNextStation.setText(subwayStation.getNextname());
        holder.itemStation.setText("建国门站");

        return convertView;

    }


    static class  ViewHolder{
        private TextView itemStation;
        private TextView itemLineName;
        private TextView itemNextStation;
        private TextView itemTime;
    }

    private void initView() {

    }
}
