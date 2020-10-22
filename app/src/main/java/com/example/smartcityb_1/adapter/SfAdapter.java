package com.example.smartcityb_1.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;


import com.example.smartcityb_1.R;
import com.example.smartcityb_1.bean.SF;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * @Login Name win10
 * @Create by 张瀛煜 on 2020/10/8 at 19:58
 */
public class SfAdapter extends ArrayAdapter<SF> {
    public SfAdapter(@NonNull Context context, @NonNull List<SF> objects) {
        super(context, 0, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.sf_item, parent, false);
            holder = new ViewHolder();
            holder.itemDw = convertView.findViewById(R.id.item_dw);
            holder.itemHh = convertView.findViewById(R.id.item_hh);
            holder.itemYe = convertView.findViewById(R.id.item_ye);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        SF sf = getItem(position);
        holder.itemDw.setText(sf.getUnit());
        holder.itemHh.setText(sf.getAccountId());
        holder.itemYe.setText(sf.getBanlance() + "");
        return convertView;
    }

    static class ViewHolder {

        private TextView itemDw;
        private TextView itemHh;
        private TextView itemYe;

    }

    private void initView() {
    }
}
