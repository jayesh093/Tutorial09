package com.example.student.tutorial09;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;

class CustomAdpter extends BaseAdapter{
    Context  context;
    JSONArray  jsonArray;

    public CustomAdpter(MainActivity mainActivity, JSONArray jsonArray) {
    this.context=mainActivity;
    this.jsonArray=jsonArray;
    }

    @Override
    public int getCount() {
        return jsonArray.length();
    }

    @Override
    public Object getItem(int i) {
        try {
            return jsonArray.getJSONObject(i);
        } catch (JSONException e) {
            e.printStackTrace();
            return  null;
        }
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        if(view==null){
            LayoutInflater layoutInflater=LayoutInflater.from(context);
            view=layoutInflater.inflate(R.layout.listitem,null);
            viewHolder=new ViewHolder();
            viewHolder.textView=view.findViewById(R.id.textView3);
            viewHolder.textView1=view.findViewById(R.id.textView4);

            view.setTag(viewHolder);
        }else
        {
            viewHolder=(ViewHolder)view.getTag();
        }
        try {
            viewHolder.textView.setText(jsonArray.getJSONObject(i).getString("formula"));
            viewHolder.textView1.setText(jsonArray.getJSONObject(i).getString("url"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return view;
    }
    public class ViewHolder{
        TextView textView,textView1;
    }
}
