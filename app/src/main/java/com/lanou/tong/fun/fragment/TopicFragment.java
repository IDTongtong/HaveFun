package com.lanou.tong.fun.fragment;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;


import com.lanou.tong.fun.R;
import com.lanou.tong.fun.adapter.TopicAdapter;
import com.lanou.tong.fun.base.BaseApplication;
import com.lanou.tong.fun.bean.TopicBean;
import com.lanou.tong.fun.net.NetHelper;
import com.lanou.tong.fun.net.NetInterface;
import com.lanou.tong.fun.tool.MyListView;

import java.util.ArrayList;

/**
 * Created by zt on 16/3/11.
 */
public class TopicFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener, NetInterface<TopicBean> {
    private MyListView listView;
    private TopicAdapter adapter;
    private ArrayList<TopicBean.DataEntity.ExpertListEntity> data;
    private static Context context;
    private SwipeRefreshLayout swipeRefreshLayout;
    private boolean isBottom = false;
    private int itemId = 0;

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 0x102:
                    adapter.notifyDataSetChanged();
                    swipeRefreshLayout.setRefreshing(false);
                    break;
            }
        }
    };

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_topic, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        listView = (MyListView) getView().findViewById(R.id.topic_list);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();
        setRefreshComplete();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }

    private void setRefreshComplete() {
        swipeRefreshLayout = (SwipeRefreshLayout) getView().findViewById(R.id.topic_swipe);
        swipeRefreshLayout.setOnRefreshListener(this);
    }

    private void initData() {
        NetHelper netHelper = new NetHelper();
        netHelper.getInfo("http://c.m.163.com/newstopic/list/expert/0-10.html", this, TopicBean.class);

        listView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                if (listView.getLastVisiblePosition() == data.size() - 1 && isBottom) {
                    itemId += 10;
                    NetHelper helper = new NetHelper();
                    helper.getInfo("http://c.m.163.com/newstopic/list/expert/" + itemId + "-10.html", new NetInterface<TopicBean>() {
                        @Override
                        public void getSucceed(TopicBean topicBean) {
                            adapter.addData(topicBean);
                            data.addAll(topicBean.getData().getExpertList());
                            listView.setSelection(itemId - 1);
                        }

                        @Override
                        public void getFailes(String s) {

                        }

                        @Override
                        public void getSucceedForList(ArrayList<TopicBean> data) {

                        }
                    }, TopicBean.class);
                    isBottom = false;
                } else if (listView.getLastVisiblePosition() != data.size() - 1) {
                    isBottom = true;
                }
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

            }
        });
    }

    @Override
    public void getSucceed(TopicBean topicBean) {
        data = (ArrayList<TopicBean.DataEntity.ExpertListEntity>) topicBean.getData().getExpertList();
        adapter = new TopicAdapter(topicBean, BaseApplication.getContext());
        listView.setAdapter(adapter);
    }

    @Override
    public void getFailes(String s) {

    }

    @Override
    public void onRefresh() {
        mHandler.sendEmptyMessageDelayed(0x102, 2000);
        initData();
    }

    @Override
    public void getSucceedForList(ArrayList<TopicBean> data) {

    }
}
