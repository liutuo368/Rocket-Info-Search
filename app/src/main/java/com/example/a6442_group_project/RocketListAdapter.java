package com.example.a6442_group_project;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;
import java.util.Map;

public class RocketListAdapter extends BaseAdapter {
    private List<Map<String, Object>> data;
    private LayoutInflater layoutInflater;
    private Context context;

    public RocketListAdapter(Context context, List<Map<String, Object>> data){
        this.context=context;
        this.data=data;
        this.layoutInflater= LayoutInflater.from(context);
    }

    public final class Rocket {
        public TextView location;
        public TextView detail;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Rocket rocket = null;
        if(convertView == null) {
            rocket = new Rocket();
            convertView = layoutInflater.inflate(R.layout.rocket_list, null);
            rocket.location = (TextView) convertView.findViewById(R.id.rocket_location);
            rocket.detail = (TextView) convertView.findViewById(R.id.rocket_detail);
            convertView.setTag(rocket);
        } else {
            rocket = (Rocket) convertView.getTag();
        }

        rocket.location.setText((String)data.get(position).get("company"));
        rocket.detail.setText((String) data.get(position).get("detail"));
        return convertView;

    }

}
