package com.gw2panel.android.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.gw2panel.android.R;
import com.gw2panel.android.adapters.objects.NewsObject;

import java.util.ArrayList;

public class NewsAdapter extends BaseAdapter {

    private LayoutInflater inflater;
    private ArrayList<NewsObject> objects;

    private class ViewHolder {
        TextView title;
        TextView description;
        TextView date;
    }

    public NewsAdapter(Context context, ArrayList<NewsObject> objects) {
        inflater = LayoutInflater.from(context);
        this.objects = objects;
    }

    public int getCount() {
        return objects.size();
    }

    public NewsObject getItem(int position) {
        return objects.get(position);
    }

    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = inflater.inflate(R.layout.news_listview_layout, null);
            holder.title = (TextView) convertView.findViewById(R.id.news_textview_title);
            holder.description = (TextView) convertView.findViewById(R.id.news_textview_description);
            holder.date = (TextView) convertView.findViewById(R.id.news_textview_date);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.title.setText(objects.get(position).getProp1());
        holder.description.setText(objects.get(position).getProp2());
        holder.date.setText(objects.get(position).getProp3());
        return convertView;
    }

}
