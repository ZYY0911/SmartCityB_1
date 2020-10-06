package com.example.smartcityb_1.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.smartcityb_1.R;
import com.example.smartcityb_1.bean.DepList;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * @Login Name win10
 * @Create by 张瀛煜 on 2020/10/6 at 15:27
 */
public class KsChooseAdapter extends ArrayAdapter<DepList> {

    public KsChooseAdapter(@NonNull Context context, @NonNull List<DepList> objects) {
        super(context, 0, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        convertView = LayoutInflater.from(getContext()).inflate(R.layout.ks_choose_item, parent, false);
        TextView itemKs = convertView.findViewById(R.id.item_ks);
        DepList depList = getItem(position);
        itemKs.setText(depList.getDeptName() + "\r\n     " + depList.getDesc());
        return convertView;
    }


}
