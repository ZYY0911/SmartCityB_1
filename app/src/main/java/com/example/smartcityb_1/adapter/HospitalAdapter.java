package com.example.smartcityb_1.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.example.smartcityb_1.R;
import com.example.smartcityb_1.bean.Hospitallist;
import com.example.smartcityb_1.net.VolleyImage;
import com.example.smartcityb_1.net.VolleyLo;
import com.example.smartcityb_1.net.VolleyLoImage;
import com.example.smartcityb_1.net.VolleyTo;
import com.example.smartcityb_1.util.Utils;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * @Login Name win10
 * @Create by 张瀛煜 on 2020/10/6 at 11:32
 */
public class HospitalAdapter extends ArrayAdapter<Hospitallist> {

    public HospitalAdapter(@NonNull Context context, @NonNull List<Hospitallist> objects) {
        super(context, 0, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        final ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.hispital_item, parent, false);
            holder = new ViewHolder();
            holder.itemIamge = convertView.findViewById(R.id.item_iamge);
            holder.itemName = convertView.findViewById(R.id.item_name);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        final Hospitallist hospitallist = getItem(position);
        VolleyImage volleyImage = new VolleyImage();
        volleyImage.setUrl(hospitallist.getIcon())
                .setVolleyLoImage(new VolleyLoImage() {
                    @Override
                    public void onResponse(Bitmap bitmap) {
                        holder.itemIamge.setImageBitmap(bitmap);
                    }

                    @Override
                    public void onErrorResponse(VolleyError volleyError) {

                    }
                }).start();
        VolleyTo volleyTo = new VolleyTo();
        volleyTo.setUrl("getRankByHospitalId")
                .setJsonObject("hospitalId", hospitallist.getHospitalId())
                .setVolleyLo(new VolleyLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        JSONObject jsonArray = jsonObject.optJSONArray(Utils.Rows).optJSONObject(0);
                        holder.itemName.setText(hospitallist.getHospitalName() + "\r\n"
                                + jsonArray.optString("rank") + "星");
                    }

                    @Override
                    public void onErrorResponse(VolleyError volleyError) {

                    }
                }).start();

        return convertView;
    }

    static class ViewHolder {

        private ImageView itemIamge;
        private TextView itemName;
    }

    private void initView() {
    }
}
