package com.lanou.tong.fun.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.lanou.tong.fun.R;
import com.lanou.tong.fun.activity.MediaActivity;
import com.lanou.tong.fun.bean.MediaBean;


import java.util.ArrayList;

/**
 * Created by zt on 16/3/14.
 */
public class MediaAdapter extends BaseAdapter {
    private ArrayList<MediaBean.VideoListEntity> data;
    private Context context;
    private MediaBean bean;

    public MediaAdapter(MediaBean bean, Context context) {
        data = new ArrayList<>();
        data.addAll(bean.getVideoList());
        this.bean = bean;
        this.context = context;
        notifyDataSetChanged();
    }

    public void addData(MediaBean bean) {
        data.addAll(bean.getVideoList());
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return data != null && data.size() > 0 ? data.size() : 0;
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
    public View getView(int position, View convertView, ViewGroup parent) {
        MediaHolder holder = null;
        if (convertView == null) {
            holder = new MediaHolder();
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_media, null);
//            holder.videoView = (VideoView) convertView.findViewById(R.id.media_videoview);
            holder.title = (TextView) convertView.findViewById(R.id.media_title);
            holder.content = (TextView) convertView.findViewById(R.id.media_content);
            holder.simpleDraweeView = (SimpleDraweeView) convertView.findViewById(R.id.media_swipe);
            holder.mediaPlay = (ImageView) convertView.findViewById(R.id.media_play);
            convertView.setTag(holder);
        } else {
            holder = (MediaHolder) convertView.getTag();
        }

        final MediaBean.VideoListEntity bean = (MediaBean.VideoListEntity) getItem(position);
        if (bean != null) {
            holder.simpleDraweeView.setImageURI(Uri.parse(bean.getCover()));
//            holder.videoView.setVideoURI(Uri.parse(bean.getMp4_url()));
            holder.content.setText(bean.getDescription());
            holder.title.setText(bean.getTitle());
            final MediaHolder mediaHolder = holder;

            holder.mediaPlay.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    mediaHolder.simpleDraweeView.setVisibility(View.GONE);
//                    mediaHolder.mediaPlay.setVisibility(View.GONE);
                    Intent intent = new Intent(context, MediaActivity.class);
                    intent.putExtra("mp4Url", bean.getMp4_url());
                    context.startActivity(intent);
                }
            });
//            MediaController mediaController = new MediaController(context);
//            holder.videoView.setMediaController(mediaController);
//            holder.videoView.requestFocus();
        }
        return convertView;
    }

    public class MediaHolder {
//        VideoView videoView;
        SimpleDraweeView simpleDraweeView;
        TextView title, content;
        ImageView mediaPlay;
    }
}
