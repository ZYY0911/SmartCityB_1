package com.example.smartcityb_1.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.smartcityb_1.R;
import com.example.smartcityb_1.bean.OrderTitle;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * @Login Name win10
 * @Create by 张瀛煜 on 2020/10/5 at 20:50
 */
public class OrderAdapter extends ArrayAdapter<OrderTitle> {

    public OrderAdapter(@NonNull Context context, @NonNull List<OrderTitle> objects) {
        super(context, 0, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.order_item, parent, false);
            holder = new ViewHolder();
            holder.itemDdh = convertView.findViewById(R.id.item_ddh);
            holder.itemLx = convertView.findViewById(R.id.item_lx);
            holder.liteRq = convertView.findViewById(R.id.lite_rq);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        OrderTitle orderTitle = getItem(position);
        holder.itemLx.setText(orderTitle.getType());
        holder.itemDdh.setText(orderTitle.getId() + "");
        holder.liteRq.setText(orderTitle.getDate().replace(".0",""));
        return convertView;
    }

    static class ViewHolder {

        private TextView itemDdh;
        private TextView itemLx;
        private TextView liteRq;
    }

    private void initView() {
    }
}
