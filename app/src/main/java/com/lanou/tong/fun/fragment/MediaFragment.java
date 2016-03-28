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
import com.lanou.tong.fun.adapter.MediaAdapter;
import com.lanou.tong.fun.bean.MediaBean;
import com.lanou.tong.fun.net.NetHelper;
import com.lanou.tong.fun.net.NetInterface;
import com.lanou.tong.fun.tool.MyListView;

import java.util.ArrayList;

/**
 * Created by zt on 16/3/14.
 */
public class MediaFragment extends Fragment implements NetInterface<MediaBean>, SwipeRefreshLayout.OnRefreshListener {
    private MyListView listView;
    private MediaAdapter adapter;
    private ArrayList<MediaBean.VideoListEntity> data;
    private static Context context;
    private SwipeRefreshLayout swipeRefreshLayout;
    private boolean isBottom = false;
    private int itemId = 0;

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 0x103:
                    adapter.notifyDataSetChanged();
                    swipeRefreshLayout.setRefreshing(false);
                    break;
            }
        }
    };

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_media, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        listView = (MyListView) view.findViewById(R.id.media_list);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getContent();
        setRefreshComplete();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }

    // 上拉加载
    private void setRefreshComplete() {
        swipeRefreshLayout = (SwipeRefreshLayout) getView().findViewById(R.id.media_swipe);
        swipeRefreshLayout.setOnRefreshListener(this);
    }

    private void getContent() {
        NetHelper netHelper = new NetHelper();
        netHelper.getInfo("http://c.3g.163.com/nc/video/home/0-10.html", this, MediaBean.class);

        listView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                if (listView.getLastVisiblePosition() == data.size() - 1 && isBottom) {
                    itemId += 10;
                    NetHelper helper = new NetHelper();
                    helper.getInfo("http://c.3g.163.com/nc/video/home/" + itemId + "-10.html", new NetInterface<MediaBean>() {
                        @Override
                        public void getSucceed(MediaBean mediaBean) {
                            adapter.addData(mediaBean);
                            data.addAll(mediaBean.getVideoList());
                            listView.setSelection(itemId - 1);
                        }

                        @Override
                        public void getFailes(String s) {

                        }

                        @Override
                        public void getSucceedForList(ArrayList<MediaBean> data) {

                        }
                    }, MediaBean.class);
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
    public void getSucceed(MediaBean mediaBean) {
        data = (ArrayList<MediaBean.VideoListEntity>) mediaBean.getVideoList();
        adapter = new MediaAdapter(mediaBean, getContext());
        listView.setAdapter(adapter);
    }

    @Override
    public void getFailes(String s) {

    }

    @Override
    public void getSucceedForList(ArrayList<MediaBean> data) {

    }

    @Override
    public void onRefresh() {
        handler.sendEmptyMessageDelayed(0x103, 1000);
        getContent();
    }
}
