package com.example.smartcityb_1.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.smartcityb_1.R;
import com.example.smartcityb_1.bean.Rl_Bean;
import com.example.smartcityb_1.util.OnClickItem;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * @Login Name win10
 * @Create by 张瀛煜 on 2020/10/8 at 15:00
 */
public class RlADapter extends ArrayAdapter<Rl_Bean> {

    public RlADapter(@NonNull Context context, @NonNull List<Rl_Bean> objects) {
        super(context, 0, objects);
    }

    private OnClickItem onClickItem;

    public void setOnClickItem(OnClickItem onClickItem) {
        this.onClickItem = onClickItem;
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.rl_item, parent, false);
            holder = new ViewHolder();
            holder.bgColor = convertView.findViewById(R.id.bg_color);
            holder.itemMsg = convertView.findViewById(R.id.item_msg);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        Rl_Bean rl_bean = getItem(position);
        if (rl_bean.getLx() == 1) {
            holder.bgColor.setBackgroundResource(R.drawable.bg_gray);
        } else {
            holder.itemMsg.setText(rl_bean.getDay() + "");
            if (rl_bean.getBg() == 1) {
                holder.bgColor.setBackgroundResource(R.drawable.bg_rl_1);
            } else if (rl_bean.getBg() == 2) {
                holder.bgColor.setBackgroundResource(R.drawable.bg_rl_2);
            } else {
                holder.bgColor.setBackgroundResource(R.drawable.bg_rl_3);
            }
            holder.bgColor.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onClickItem.onClick(position, "");
                }
            });
        }
        return convertView;
    }

    static class ViewHolder {

        private LinearLayout bgColor;
        private TextView itemMsg;

    }

    private void initView() {
    }
}
