package com.lanou.tong.fun.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.lanou.tong.fun.R;
import com.lanou.tong.fun.adapter.TopicAdapter;
import com.lanou.tong.fun.adapter.TopicContentAdapter;
import com.lanou.tong.fun.base.BaseActivity;
import com.lanou.tong.fun.bean.TopicContentBean;
import com.lanou.tong.fun.net.NetHelper;
import com.lanou.tong.fun.net.NetInterface;

import java.util.ArrayList;

/**
 * Created by zt on 16/3/15.
 */
public class TopicContentActivity extends BaseActivity implements NetInterface<TopicContentBean>, View.OnClickListener {
    private TopicContentAdapter adapter;
    private TopicContentBean datas;
    private SimpleDraweeView iv, expertIv;
    private static TopicAdapter topicAdapter;
    private CollapsingToolbarLayout collapsingToolbarLayout;
    private static String expertId;
    private TextView descriptionTv, attentTv;
    private Context context;
    private Toolbar toolbar;
    private RecyclerView recyclerView;
    private ImageView returnIv;

    @Override
    protected int setContent() {
        return R.layout.activity_topic_content;
    }

    @Override
    protected void initData() {
        NetHelper helper = new NetHelper();
        Intent intent = getIntent();
        expertId = intent.getStringExtra("expertId");
        helper.getData("http://c.m.163.com/newstopic/qa/","" , expertId, ".html", TopicContentBean.class, this);
    }

    @Override
    protected void initView() {
        toolbar = (Toolbar) findViewById(R.id.topic_activity_toolbar);
        recyclerView = (RecyclerView) findViewById(R.id.topic_activity_recyclerview);
        descriptionTv = (TextView) findViewById(R.id.topic_activity_toolbar_tv);
        attentTv = (TextView) findViewById(R.id.topic_activity_count);
        iv = (SimpleDraweeView) findViewById(R.id.topic_activity_headpic);
        returnIv = (ImageView) findViewById(R.id.topic_activity_return);
        returnIv.setOnClickListener(this);

        collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.topic_activity_collapsing);
        collapsingToolbarLayout.setCollapsedTitleTextColor(Color.WHITE);
        collapsingToolbarLayout.setExpandedTitleColor(Color.TRANSPARENT);
    }

    @Override
    public void getSucceed(TopicContentBean topicContentBean) {
        adapter = new TopicContentAdapter(topicContentBean, this);
        recyclerView.setAdapter(adapter);
        GridLayoutManager manager = new GridLayoutManager(this, 1);
        recyclerView.setLayoutManager(manager);

        descriptionTv.setText("    " + topicContentBean.getData().getExpert().getAlias());
        attentTv.setText(topicContentBean.getData().getExpert().getConcernCount() + "关注");
        iv.setImageURI(Uri.parse(topicContentBean.getData().getExpert().getPicurl()));
        collapsingToolbarLayout.setTitle("     " + topicContentBean.getData().getExpert().getAlias());
    }

    @Override
    public void getFailes(String s) {

    }

    @Override
    public void getSucceedForList(ArrayList data) {

    }

    @Override
    public void onClick(View v) {
        finish();
    }
}
