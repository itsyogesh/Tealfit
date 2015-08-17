package com.droidsize.realmtest;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Objects;

/**
 * Created by yogeshkumar on 17/8/15.
 */
public class MyListAdapter extends BaseAdapter {

    private ArrayList<Person> persons;
    private Context context;

    public MyListAdapter(ArrayList<Person> persons, Context context){
        this.persons = persons;
        this.context = context;
    }

    @Override
    public int getCount(){
        return persons.size();
    }

    @Override
    public long getItemId(int position){
        return position;
    }

    @Override
    public Object getItem(int position){
        return persons.get(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        Person p = (Person) this.getItem(position);
        ViewHolder holder;
        if(convertView == null){
            holder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.list_cell, parent, false);
            holder.name = (TextView) convertView.findViewById(R.id.mMainLabel);
            holder.city = (TextView) convertView.findViewById(R.id.mCityLabel);
            convertView.setTag(holder);
        }
        else
            holder = (ViewHolder) convertView.getTag();

        holder.name.setText(p.getName());
        holder.name.setText(p.getCity());

        return convertView;

    }

    private static class ViewHolder {
        TextView name;
        TextView city;
    }

}
