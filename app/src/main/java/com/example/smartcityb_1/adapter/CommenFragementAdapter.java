package com.example.smartcityb_1.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.example.smartcityb_1.R;
import com.example.smartcityb_1.bean.CommitList;
import com.example.smartcityb_1.bean.User;
import com.example.smartcityb_1.net.VolleyImage;
import com.example.smartcityb_1.net.VolleyLoImage;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * @Login Name win10
 * @Create by 张瀛煜 on 2020/10/3 at 15:41
 */
public class CommenFragementAdapter extends ArrayAdapter<CommitList> {

    private List<User> users;
    public CommenFragementAdapter(@NonNull Context context, @NonNull List<CommitList> objects, List<User> users) {
        super(context, 0, objects);
        this.users = users;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        final ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.commit_item, parent, false);
            holder = new ViewHolder();
            holder.itemImage = convertView.findViewById(R.id.item_image);
            holder.itemName = convertView.findViewById(R.id.item_name);
            holder.itemMag = convertView.findViewById(R.id.item_mag);
            holder.itemTime = convertView.findViewById(R.id.item_time);
            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }
        CommitList commitList = getItem(position);
        holder.itemMag.setText(commitList.getCommit());
        holder.itemTime.setText(commitList.getCommitTime());
        User user = setMyInfo(commitList.getReviewer());
        holder.itemName.setText(commitList.getReviewer());
        VolleyImage volleyImage = new VolleyImage();
        volleyImage.setUrl(user.getImage())
                .setVolleyLoImage(new VolleyLoImage() {
                    @Override
                    public void onResponse(Bitmap bitmap) {
                        holder.itemImage.setImageBitmap(bitmap);
                    }
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                    }
                }).start();
        return convertView;
    }


    private User setMyInfo(String username) {
        for (int i = 0; i < users.size(); i++) {
            User user = users.get(i);
            if (user.getUsername().equals(username)) {
                return user;
            }
        }
        return null;
    }
    static class ViewHolder{

        private ImageView itemImage;
        private TextView itemName;
        private TextView itemMag;
        private TextView itemTime;
    }
}
