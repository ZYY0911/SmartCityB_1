package com.example.smartcityb_1.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;


import com.example.smartcityb_1.R;
import com.example.smartcityb_1.bean.MyBean;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * @Login Name win10
 * @Create by 张瀛煜 on 2020/10/2 at 10:00
 */
public class AllTrafficAdapter extends ArrayAdapter<MyBean> {

    public AllTrafficAdapter(@NonNull Context context, @NonNull List<MyBean> objects) {
        super(context, 0, objects);
    }


    int colors[] = {Color.RED, Color.BLUE, Color.GRAY, Color.GREEN, Color.BLACK, Color.CYAN};

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        convertView = LayoutInflater.from(getContext()).inflate(R.layout.text_view_item, parent, false);
        TextView itemTv = convertView.findViewById(R.id.item_tv);
        itemTv.setText(getItem(position).getName());
        itemTv.setTextColor(colors[position]);
        return convertView;
    }


    private void initView() {
    }
}
