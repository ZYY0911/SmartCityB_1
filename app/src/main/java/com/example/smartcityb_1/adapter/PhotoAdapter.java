package com.example.smartcityb_1.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import com.example.smartcityb_1.R;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * @Login Name win10
 * @Create by 张瀛煜 on 2020/10/6 at 11:11
 */
public class PhotoAdapter extends ArrayAdapter<Integer> {


    public PhotoAdapter(@NonNull Context context, @NonNull List<Integer> objects) {
        super(context, 0, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_imgae, parent, false);
        ImageView itemImage = convertView.findViewById(R.id.item_image);
        itemImage.setImageResource(getItem(position));
        return convertView;
    }

    private void initView() {
    }
}
