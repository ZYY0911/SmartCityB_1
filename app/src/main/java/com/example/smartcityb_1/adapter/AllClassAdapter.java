package com.example.smartcityb_1.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.smartcityb_1.R;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * @Login Name win10
 * @Create by 张瀛煜 on 2020/10/3 at 10:34
 */
public class AllClassAdapter extends ArrayAdapter<String> {
    public AllClassAdapter(@NonNull Context context,@NonNull List<String> objects) {
        super(context, 0, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.all_class_item, parent, false);
            holder = new ViewHolder();
            holder.itemImage = convertView.findViewById(R.id.item_image);
            holder.itemTitle = convertView.findViewById(R.id.item_title);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.itemImage.setImageResource(position % 2 == 0 ? R.mipmap.tuxiang1 : R.mipmap.tuxiang2);
        holder.itemTitle.setText(getItem(position));
        return convertView;
    }

    static class ViewHolder {

        private ImageView itemImage;
        private TextView itemTitle;

    }

    private void initView() {
    }
}
