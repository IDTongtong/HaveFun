package com.lanou.tong.fun.adapter;

import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.lanou.tong.fun.R;
import com.lanou.tong.fun.bean.ReadContentCustomBean;
import com.lanou.tong.fun.tool.ImageLoaderCache;

import java.util.ArrayList;

/**
 * Created by zt on 16/3/18.
 */
public class ReadContentAdapter extends RecyclerView.Adapter {
    private ArrayList<ReadContentCustomBean> customBeans;
    private String title;
    private String time;
    private static final int HEAD_MODE = 1;
    private static final int CONTENT_MODE = 2;

    public void setTitle(String title) {
        this.title = title;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public ReadContentAdapter(ArrayList<ReadContentCustomBean> customBeans) {
        this.customBeans = customBeans;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return HEAD_MODE;
        } else {
            return CONTENT_MODE;
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == HEAD_MODE) {
            return new HeadViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.read_content_item_head, null));
        } else {
            return new BodyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.read_content_item_body, null));
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (getItemViewType(position) == HEAD_MODE) {
            HeadViewHolder headHolder = (HeadViewHolder) holder;
            headHolder.time.setText(time);
            headHolder.title.setText(title);
        } else {
            BodyViewHolder bodyHolder = (BodyViewHolder) holder;
            String content = customBeans.get(position - 1).getContent();
            bodyHolder.contentTv.setText(" " + Html.fromHtml(content));
            bodyHolder.img.setVisibility(View.VISIBLE);
            if (customBeans.get(position - 1).getImgUrl() != null) {
                new ImageLoaderCache().getImageLoader(customBeans.get(position - 1).getImgUrl(), bodyHolder.img);
            } else bodyHolder.img.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return customBeans.size() + 1;
    }

    // 自定义缓存类
    public class BodyViewHolder extends RecyclerView.ViewHolder{
        TextView contentTv;
        SimpleDraweeView img;

        public BodyViewHolder(View itemView) {
            super(itemView);
            img = (SimpleDraweeView) itemView.findViewById(R.id.read_content_body_img);
            contentTv = (TextView) itemView.findViewById(R.id.read_content_body_content);
        }
    }

    public class HeadViewHolder extends RecyclerView.ViewHolder {
        TextView title, time;

        public HeadViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.read_content_head_title);
            time = (TextView) itemView.findViewById(R.id.read_content_head_time);
        }
    }
}
