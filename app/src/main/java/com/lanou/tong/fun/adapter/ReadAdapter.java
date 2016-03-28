package com.lanou.tong.fun.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.lanou.tong.fun.R;
import com.lanou.tong.fun.activity.ReadContentActivity;
import com.lanou.tong.fun.bean.ReadBean;


import java.util.ArrayList;

/**
 * Created by zt on 16/3/10.
 */
public class ReadAdapter extends BaseAdapter {
    private ArrayList<ReadBean.recommendedEntity> data;
    private Context context;
    private ReadBean bean;

    public ReadAdapter(ReadBean bean, Context context) {
        data = new ArrayList<>();
        data.addAll(bean.getRecommended());
        this.bean = bean;
        this.context = context;
    }

    public void addData(ReadBean bean) {
        data.addAll(bean.getRecommended());
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return data != null && data.size() > 0 ? data.size() : 0;
    }

    @Override
    public Object getItem(int position) {
        return data != null && data.size() > 0 ? data.get(position) : null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ReadHolder holder = null;
        if (convertView == null) {
            holder = new ReadHolder();
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_read, null);
            holder.img = (SimpleDraweeView) convertView.findViewById(R.id.read_list_img);
            holder.title = (TextView) convertView.findViewById(R.id.read_list_title);
            holder.source = (TextView) convertView.findViewById(R.id.read_list_source);
            holder.linearLayout = (LinearLayout) convertView.findViewById(R.id.read_list_linearlayout);
            convertView.setTag(holder);
        } else {
            holder = (ReadHolder) convertView.getTag();
        }

        holder.title.setText(data.get(position).getTitle());
        holder.source.setText(data.get(position).getSource());
        holder.img.setVisibility(View.VISIBLE);
        if (data.get(position).getImgsrc() != null) {
            holder.img.setImageURI(Uri.parse(data.get(position).getImgsrc()));
        } else {
            holder.img.setVisibility(View.GONE);
        }
        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ReadContentActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("id", data.get(position).getId());
                context.startActivity(intent);
            }
        });
        return convertView;
    }

    public class ReadHolder {
        SimpleDraweeView img;
        TextView title, source;
        private LinearLayout linearLayout;
    }
}
