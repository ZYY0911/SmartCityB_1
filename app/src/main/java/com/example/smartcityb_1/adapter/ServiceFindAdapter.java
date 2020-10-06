package com.example.smartcityb_1.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.smartcityb_1.R;
import com.example.smartcityb_1.bean.YLServiceBean;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * @Login Name win10
 * @Create by 张瀛煜 on 2020/10/6 at 10:46
 */
public class ServiceFindAdapter extends ArrayAdapter<YLServiceBean> {

    public ServiceFindAdapter(@NonNull Context context, @NonNull List<YLServiceBean> objects) {
        super(context, 0, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.service_item_find, parent, false);
            holder = new ViewHolder();
            holder.item1 = convertView.findViewById(R.id.item_1);
            holder.item2 = convertView.findViewById(R.id.item_2);
            holder.item3 = convertView.findViewById(R.id.item_3);
            holder.item4 = convertView.findViewById(R.id.item_4);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        YLServiceBean serviceBean = getItem(position);
        holder.item1.setText(serviceBean.getName());
        holder.item2.setText(serviceBean.getTime());
        holder.item3.setText(serviceBean.getType());
        holder.item4.setText(serviceBean.getState());
        return convertView;
    }

    static class ViewHolder {

        private TextView item1;
        private TextView item2;
        private TextView item3;
        private TextView item4;
    }

    private void initView() {
    }
}
