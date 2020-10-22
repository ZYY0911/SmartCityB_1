package com.example.smartcityb_1.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;


import com.example.smartcityb_1.R;

import java.util.List;
import java.util.Map;

/**
 * @Login Name win10
 * @Create by 张瀛煜 on 2020/10/8 at 17:49
 */
public class FjHsjAdatper extends BaseExpandableListAdapter {
    private Map<String, List<String>> map;
    private String a[] = {"一号回收机器", "二号回收机器"};

    public FjHsjAdatper(Map<String, List<String>> map) {
        this.map = map;
    }

    @Override
    public int getGroupCount() {
        return map.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return map.get(a[groupPosition]).size();
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

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.li_item, parent, false);
        TextView itemName = convertView.findViewById(R.id.item_name);
        itemName.setText(a[groupPosition]);
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.li_item_zi, parent, false);
        TextView itemName = convertView.findViewById(R.id.item_name);
        itemName.setText(map.get(a[groupPosition]).get(childPosition));
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
