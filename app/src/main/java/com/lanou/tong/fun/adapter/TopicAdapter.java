package com.lanou.tong.fun.adapter;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.facebook.drawee.generic.GenericDraweeHierarchy;
import com.facebook.drawee.generic.GenericDraweeHierarchyBuilder;
import com.facebook.drawee.generic.RoundingParams;
import com.facebook.drawee.view.SimpleDraweeView;
import com.lanou.tong.fun.R;
import com.lanou.tong.fun.activity.TopicContentActivity;
import com.lanou.tong.fun.bean.TopicBean;


import java.util.ArrayList;

/**
 * Created by zt on 16/3/11.
 */
public class TopicAdapter extends BaseAdapter {
    private ArrayList<TopicBean.DataEntity.ExpertListEntity> data;
    private Context context;
    private TopicBean bean;

    public TopicAdapter(TopicBean bean, Context context) {
        data = new ArrayList<>();
        data.addAll(bean.getData().getExpertList());
        this.bean = bean;
        this.context = context;
        notifyDataSetChanged();
    }

    public void addData(TopicBean bean) {
        data.addAll(bean.getData().getExpertList());
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
        TopicHolder holder = null;
        if (convertView == null) {
            holder = new TopicHolder();
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_topic, null);
            holder.headpicurl = (SimpleDraweeView) convertView.findViewById(R.id.topic_headpicurl);
            holder.picurl = (SimpleDraweeView) convertView.findViewById(R.id.topic_picurl);
            holder.alias = (TextView) convertView.findViewById(R.id.topic_alias);
            holder.classification = (TextView) convertView.findViewById(R.id.topic_classification);
            holder.namtitle = (TextView) convertView.findViewById(R.id.topic_name_title);
            holder.linearLayout = (LinearLayout) convertView.findViewById(R.id.topic_item_linearlayout);
            convertView.setTag(holder);
        } else {
            holder = (TopicHolder) convertView.getTag();
        }

        if (data != null) {
            // 设置圆形图片
            GenericDraweeHierarchy hierarchy
                    = GenericDraweeHierarchyBuilder
                    .newInstance(Resources.getSystem())
                    .setRoundingParams(RoundingParams.asCircle()).build();
            holder.headpicurl.setHierarchy(hierarchy);
            holder.headpicurl.setImageURI(Uri.parse(data.get(position).getHeadpicurl()));

            holder.picurl.setImageURI(Uri.parse(data.get(position).getPicurl()));
            holder.namtitle.setText(data.get(position).getName() + " / " + data.get(position).getTitle());
            holder.alias.setText(data.get(position).getAlias());
            holder.classification.setText(data.get(position).getClassification());
            holder.linearLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, TopicContentActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent.putExtra("expertId", data.get(position).getExpertId());
                    context.startActivity(intent);
                }
            });
        }
        return convertView;
    }

    public class TopicHolder {
        SimpleDraweeView headpicurl, picurl;
        TextView namtitle, alias, classification;
        private LinearLayout linearLayout;
    }
}
