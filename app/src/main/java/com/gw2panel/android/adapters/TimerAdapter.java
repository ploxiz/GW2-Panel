package com.gw2panel.android.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.gw2panel.android.R;
import com.gw2panel.android.adapters.objects.TimerObject;

import java.util.ArrayList;

public class TimerAdapter extends BaseAdapter {

    private LayoutInflater inflater;
    private ArrayList<TimerObject> objects;

    private class ViewHolder {
        TextView name;
        TextView status;
        TextView startTimer;
        TextView countTimer;
    }

    public TimerAdapter(Context context, ArrayList<TimerObject> objects) {
        inflater = LayoutInflater.from(context);
        this.objects = objects;
    }

    public int getCount() {
        return objects.size();
    }

    public TimerObject getItem(int position) {
        return objects.get(position);
    }

    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = inflater.inflate(R.layout.timer_listview_layout, null);
            holder.name = (TextView) convertView.findViewById(R.id.timer_textView_name);
            holder.status = (TextView) convertView.findViewById(R.id.timer_textView_status);
            holder.startTimer = (TextView) convertView.findViewById(R.id.timer_textView_startTimer);
            holder.countTimer = (TextView) convertView.findViewById(R.id.timer_textView_countTimer);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.name.setText(objects.get(position).getProp1());
        holder.status.setText(objects.get(position).getProp2());
        holder.startTimer.setText(objects.get(position).getProp3());
        holder.countTimer.setText(objects.get(position).getProp3());
        return convertView;
    }

}
