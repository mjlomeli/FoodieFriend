package com.interview.androidlib;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.List;

class ListAdapter extends ArrayAdapter<ImageAPI> {
    Context context;

    public ListAdapter(@NonNull Context context, int resource, @NonNull List<ImageAPI> objects) {
        super(context, resource, objects);
        this.context = context;
    }

    private class ViewHolder{
        ImageView imageView;
        TextView textView;
        TextView textDesc;
    }
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        ImageAPI imageAPI = getItem(position);
        LayoutInflater mInflater = (LayoutInflater) context
                .getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.item_swipe, null);
            holder = new ViewHolder();
            holder.textView = (TextView) convertView.findViewById(R.id.textView_card);
            holder.textView = (TextView) convertView.findViewById(R.id.textView_card);
            holder.imageView = (ImageView) convertView.findViewById(R.id.imageView_itemImage);

            convertView.setTag(holder);
        } else
            holder = (ViewHolder) convertView.getTag();

        holder.textView.setText(imageAPI.getName());
        holder.textDesc.setText(imageAPI.getName());
        holder.imageView.setImageResource(imageAPI.getImageResource());
        //holder.imageView.setImageURI(Uri.parse("https://logo.clearbit.com/mcdonalds.com"));
        //new DownloadImage(holder.imageView).execute("https://logo.clearbit.com/mcdonalds.com");

        return convertView;
    }
}