package com.example.smartcityb_1.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.example.smartcityb_1.R;
import com.example.smartcityb_1.util.OnClickItem;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * @Login Name win10
 * @Create by 张瀛煜 on 2020/10/8 at 16:25
 */
public class LieeAdapter extends ArrayAdapter<String> {

    int images[] = {R.mipmap.water_icon, R.mipmap.electricity_icon, R.mipmap.home_manager, R.mipmap.about_news};

    public LieeAdapter(@NonNull Context context, @NonNull List<String> objects) {
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
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.service_item, parent, false);
            holder = new ViewHolder();
            holder.itemLayout = convertView.findViewById(R.id.item_layout);
            holder.itemPhoto = convertView.findViewById(R.id.item_photo);
            holder.itemName = convertView.findViewById(R.id.item_name);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.itemLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickItem.onClick(position, getItem(position));
            }
        });
        holder.itemName.setText(getItem(position));
        holder.itemPhoto.setImageResource(images[position]);
        return convertView;
    }

    static class ViewHolder {

        private LinearLayout itemLayout;
        private ImageView itemPhoto;
        private TextView itemName;
    }
}
