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
import com.lanou.tong.fun.greendao.Collection;
import com.lanou.tong.fun.greendao.CollectionDao;
import com.lanou.tong.fun.greendao.DaoSingleton;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nan on 16/3/22.
 */
public class CollectionAdapter extends BaseAdapter {
    private DaoSingleton daoSingleton = DaoSingleton.getInstance();
    private CollectionDao collectionDao = daoSingleton.getCollectionDao();
    private List<Collection> data = new ArrayList<>();
    private Context context;

    public CollectionAdapter(Context context) {
        this.context = context;
        // 查询 所有的实体以list形式返回
        data = collectionDao.queryBuilder().list();
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
            convertView = LayoutInflater.from(context).inflate(R.layout.item_collection, parent, false);
            myViewHolder = new MyViewHolder();
            myViewHolder.textView_title = (TextView) convertView.findViewById(R.id.collect_item_title);
            myViewHolder.textView_digSet = (TextView) convertView.findViewById(R.id.collect_item_digSet);
            myViewHolder.imageView_cancel = (ImageView) convertView.findViewById(R.id.collcet_item_cancel);
            // 取消收藏
            myViewHolder.imageView_cancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    DaoSingleton daoSingleton = DaoSingleton.getInstance();
                    CollectionDao collectionDao = daoSingleton.getCollectionDao();
                    collectionDao.deleteByKey(data.get(position).getId());
                    myViewHolder.imageView_cancel.setSelected(true);
                    data = collectionDao.queryBuilder().list();
                    notifyDataSetChanged();
                    Toast.makeText(context, "取消成功", Toast.LENGTH_SHORT).show();
                }
            });

            // 跳转新闻内容
            myViewHolder.collect_linearLayout = (LinearLayout) convertView.findViewById(R.id.collect_item_linearLayout);
            myViewHolder.collect_linearLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, NewsContentActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent.putExtra("docid", data.get(position).getDocId());
                    intent.putExtra("picUrl", data.get(position).getSrc());
                    context.startActivity(intent);
                }
            });
            convertView.setTag(myViewHolder);
        } else {
            myViewHolder = (MyViewHolder) convertView.getTag();
        }
        myViewHolder.textView_title.setText(data.get(position).getTitle());
        myViewHolder.textView_digSet.setText("    " + data.get(position).getContent());
        return convertView;
    }

    //缓存类
    class MyViewHolder {
        private TextView textView_title, textView_digSet;
        private LinearLayout collect_linearLayout;
        private ImageView imageView_cancel;
    }
}
