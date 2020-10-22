package com.example.smartcityb_1.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import com.example.smartcityb_1.R;
import com.example.smartcityb_1.bean.AccoutGroup;
import com.example.smartcityb_1.bean.HoDetails;
import com.example.smartcityb_1.util.OnClickItem;

import java.util.List;
import java.util.Map;

/**
 * @Login Name win10
 * @Create by 张瀛煜 on 2020/10/8 at 19:38
 */
public class HoAdapter extends BaseExpandableListAdapter {
    private Map<AccoutGroup, List<HoDetails>> map;
    private List<AccoutGroup> accoutGroups;

    public HoAdapter(Map<AccoutGroup, List<HoDetails>> map, List<AccoutGroup> accoutGroups) {
        this.map = map;
        this.accoutGroups = accoutGroups;
    }

    @Override
    public int getGroupCount() {
        return map.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return map.get(accoutGroups.get(groupPosition)).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return null;
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return null;
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    private OnClickItem onClickItem;

    public void setOnClickItem(OnClickItem onClickItem) {
        this.onClickItem = onClickItem;
    }

    @Override
    public View getGroupView(final int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.ho_item_fu, parent, false);
            holder = new ViewHolder();
            holder.itemType = convertView.findViewById(R.id.item_type);
            holder.itemLeft = convertView.findViewById(R.id.item_left);
            holder.itemLeft.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onClickItem.onClick(groupPosition,"");
                }
            });
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        AccoutGroup accoutGroup = accoutGroups.get(groupPosition);
        holder.itemType.setText(accoutGroup.getGroupName());
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {

        ViewHolder1 holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.ho_item_zi, parent, false);
            holder = new ViewHolder1();
            holder.itemType = convertView.findViewById(R.id.item_type);
            holder.itemNumZi = convertView.findViewById(R.id.item_num_zi);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder1) convertView.getTag();
        }
        HoDetails details = map.get(accoutGroups.get(groupPosition)).get(childPosition);
        holder.itemNumZi.setText(details.getAccountId() + "|" + details.getAccountAddress());
        holder.itemType.setText(details.getType());
        return convertView;

    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    static class ViewHolder {

        private TextView itemType;
        private ImageView itemLeft;
    }

    static class ViewHolder1 {

        private TextView itemType;
        private TextView itemNumZi;
    }

    private void initView() {
    }
}
