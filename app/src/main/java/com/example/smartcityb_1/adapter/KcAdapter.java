package com.example.smartcityb_1.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;


import com.example.smartcityb_1.R;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * @Login Name win10
 * @Create by 张瀛煜 on 2020/10/3 at 10:59
 */
public class KcAdapter extends ArrayAdapter<String> {

    public KcAdapter(@NonNull Context context, @NonNull List<String> objects) {
        super(context, 0, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        TextView itemName;
        convertView = LayoutInflater.from(getContext()).inflate(R.layout.kc_item, parent, false);
        itemName = convertView.findViewById(R.id.item_name);
        itemName.setText(getItem(position));
        return convertView;
    }

    private void initView() {
    }
}
