package com.lanou.tong.fun.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.lanou.tong.fun.R;
import com.lanou.tong.fun.activity.NewsContentActivity;
import com.lanou.tong.fun.greendao.DaoSingleton;
import com.lanou.tong.fun.greendao.History;
import com.lanou.tong.fun.greendao.HistoryDao;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nan on 16/3/22.
 */
public class HistoryAdapter extends BaseAdapter {
    private DaoSingleton daoSingleton = DaoSingleton.getInstance();
    private HistoryDao historyDao = daoSingleton.getHistoryDao();
    private List<History> data = new ArrayList<>();
    private Context context;


    public HistoryAdapter(Context context) {
        this.context = context;
        data = historyDao.queryBuilder().list();
        notifyDataSetChanged();
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

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final MyViewHolder myViewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_history, parent, false);
            myViewHolder = new MyViewHolder();
            myViewHolder.title = (TextView) convertView.findViewById(R.id.history_item_title);
            myViewHolder.time = (TextView) convertView.findViewById(R.id.history_item_time);
            myViewHolder.deleteIv = (ImageView) convertView.findViewById(R.id.history_item_delete);
            myViewHolder.deleteIv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    DaoSingleton daoSingleton = DaoSingleton.getInstance();
                    HistoryDao historyDao = daoSingleton.getHistoryDao();
                    historyDao.deleteByKey(data.get(position).getId());
                    data = historyDao.queryBuilder().list();
                    notifyDataSetChanged();
                    Toast.makeText(context, "删除成功", Toast.LENGTH_SHORT).show();
                }
            });

            myViewHolder.history_linearLayout = (LinearLayout) convertView.findViewById(R.id.history_item_linearLayout);
            myViewHolder.history_linearLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, NewsContentActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent.putExtra("docid", data.get(position).getDocId());
                    intent.putExtra("picUrl", data.get(position).getPicUrl());
                    context.startActivity(intent);
                }
            });
            convertView.setTag(myViewHolder);
        } else {
            myViewHolder = (MyViewHolder) convertView.getTag();
        }
        myViewHolder.title.setText(data.get(position).getTitle());
        myViewHolder.time.setText(data.get(position).getTime());
        return convertView;
    }

    //缓存类
    class MyViewHolder {
        private TextView title, time;
        private LinearLayout history_linearLayout;
        private ImageView deleteIv;
    }
}
