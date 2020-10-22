package com.example.smartcityb_1.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.smartcityb_1.R;
import com.example.smartcityb_1.bean.NewsList;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * @Login Name win10
 * @Create by 张瀛煜 on 2020/10/3 at 10:46
 */
public class MyStudyAdapter extends ArrayAdapter<NewsList> {


    public MyStudyAdapter(@NonNull Context context,  @NonNull List<NewsList> objects) {
        super(context, 0, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        ImageView itemImage;
        TextView itemTitle;
        TextView itemContext;
        TextView itemMsg;
        convertView = LayoutInflater.from(getContext()).inflate(R.layout.my_study_item, parent, false);
        itemImage = convertView.findViewById(R.id.item_image);
        itemTitle = convertView.findViewById(R.id.item_title);
        itemContext = convertView.findViewById(R.id.item_context);
        itemMsg = convertView.findViewById(R.id.item_msg);
        NewsList newsList = getItem(position);
//        itemImage.setImageResource(newsList.getFlag());
//        itemTitle.setText(newsList.getTitle());
//        itemContext.setText(newsList.getContent());
//        itemMsg.setText(newsList.getPublicTime());

        return convertView;
    }

    private void initView() {

    }
}
