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
import com.lanou.tong.fun.activity.NewsContentActivity;
import com.lanou.tong.fun.bean.NewsBean;

import java.util.ArrayList;

/**
 * Created by zt on 16/3/7.
 */
public class NewsReplaceAdapter extends BaseAdapter {
    // 存储新闻的集合
    private ArrayList<NewsBean> data;
    private Context context;

    public NewsReplaceAdapter(ArrayList<NewsBean> data, Context context) {
        this.data = data;
        this.context = context;
        notifyDataSetChanged();
    }

    public void addData(ArrayList<NewsBean> datas) {
        data.addAll(datas);
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
    public View getView(int position, View convertView, ViewGroup parent) {
        NewsHolder holder = null;
        if (convertView == null) {
            holder = new NewsHolder();
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_news, null);
            holder.title = (TextView) convertView.findViewById(R.id.news_item_title);
            holder.body = (TextView) convertView.findViewById(R.id.news_item_body);
            holder.img = (SimpleDraweeView) convertView.findViewById(R.id.news_item_photo);
            holder.linearLayout = (LinearLayout) convertView.findViewById(R.id.news_item_linearlayout);
            convertView.setTag(holder);
        } else {
            holder = (NewsHolder) convertView.getTag();
        }

        final NewsBean newsBean = (NewsBean) getItem(position);
        if (newsBean != null) {
            holder.title.setText(newsBean.getTitle());
            holder.body.setText(newsBean.getDigest());
            holder.img.setImageURI(Uri.parse(newsBean.getImgsrc()));
        }
        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, NewsContentActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("docid", newsBean.getDocid());
                intent.putExtra("picUrl", newsBean.getImgsrc());
                context.startActivity(intent);
            }
        });
        return convertView;
    }

    public class NewsHolder {
        TextView title, body;
        SimpleDraweeView img;
        LinearLayout linearLayout;
    }
}
