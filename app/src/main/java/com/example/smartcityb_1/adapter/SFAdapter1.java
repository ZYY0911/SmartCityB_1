package com.example.smartcityb_1.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;


import com.example.smartcityb_1.R;
import com.example.smartcityb_1.bean.HoDetails;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * @Login Name win10
 * @Create by 张瀛煜 on 2020/10/9 at 7:48
 */
public class SFAdapter1 extends ArrayAdapter<HoDetails> {
    public SFAdapter1(@NonNull Context context, @NonNull List<HoDetails> objects) {
        super(context, 0, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder1 holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.ho_item_zi, parent, false);
            holder = new ViewHolder1();
            holder.itemType = convertView.findViewById(R.id.item_type);
            holder.itemNumZi = convertView.findViewById(R.id.item_num_zi);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder1) convertView.getTag();
        }
        HoDetails details = getItem(position);
        holder.itemNumZi.setText(details.getAccountId() + "|" + details.getAccountAddress());
        holder.itemType.setText(details.getType());
        return convertView;
    }

    static class ViewHolder1 {

        private TextView itemType;
        private TextView itemNumZi;
    }
}
