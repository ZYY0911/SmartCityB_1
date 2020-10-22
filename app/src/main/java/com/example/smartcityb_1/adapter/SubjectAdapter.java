package com.example.smartcityb_1.adapter;

import android.content.Context;
import android.util.Log;
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
 * @Create by 张瀛煜 on 2020/10/1 at 20:47
 */
public class SubjectAdapter extends ArrayAdapter<String> {
    static class ViewHolder {

        private TextView itemTitle;
        private TextView itemName;
    }

    public SubjectAdapter(@NonNull Context context, @NonNull List<String> objects) {
        super(context, 0, objects);
    }

    @Override
    public int getCount() {
        return 4;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.subject_item, parent, false);
            holder = new ViewHolder();
            holder.itemTitle = convertView.findViewById(R.id.item_title);
            holder.itemName = convertView.findViewById(R.id.item_name);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        Log.i("aaa", "getView: "+getItem(position).substring(0, 1)+getItem(position));
        holder.itemTitle.setText(getItem(position).substring(0, 1));
        holder.itemName.setText(getItem(position));
        return convertView;
    }

    private void initView() {
    }
}
