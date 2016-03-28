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
import com.lanou.tong.fun.adapter.ReadAdapter;
import com.lanou.tong.fun.bean.ReadBean;
import com.lanou.tong.fun.net.NetHelper;
import com.lanou.tong.fun.net.NetInterface;
import com.lanou.tong.fun.tool.MyListView;

import java.util.ArrayList;

/**
 * Created by zt on 16/3/23.
 */
public class ReadFragment extends Fragment implements NetInterface<ReadBean>, SwipeRefreshLayout.OnRefreshListener{

    private MyListView listView;
    private ReadAdapter adapter;
    private ArrayList<ReadBean.recommendedEntity> data;
    private Context context;
    private SwipeRefreshLayout swipeRefreshLayout;
    private boolean isBottom = false;
    private int itemId = 0;

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 0x101:
                    adapter.notifyDataSetChanged();
                    swipeRefreshLayout.setRefreshing(false);
                    break;
            }
        }
    };
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_read, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        listView = (MyListView) getView().findViewById(R.id.read_list);
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
        swipeRefreshLayout = (SwipeRefreshLayout) getView().findViewById(R.id.read_swipe);
        swipeRefreshLayout.setOnRefreshListener(this);
    }

    private void initData() {
        NetHelper netHelper = new NetHelper();
        netHelper.getNewsFromNet("http://c.3g.163.com/recommend/getSubDocPic?from=yuedu&size=20&passport=&devId=Ylm2OJYEEt2B4wmQGhKdww%3D%3D&lat=LfX0R2vEJtJK9FYmxWfY0A%3D%3D&lon=g8qKREPDlis6HwgTiQsMow%3D%3D&version=5.4.9&net=wifi" +
                "&ts=1456904563&sign=93Je4yRKwCeL22cYotD%2BGxprYxjW6VdOPuWuwVcOpt948ErR02zJ6%2FKXOnxX046I&encryption=1&canal=360_news", ReadBean.class, this);

        listView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                if (listView.getLastVisiblePosition() == data.size() - 1 && isBottom) {
                    NetHelper helper = new NetHelper();
                    helper.getNewsFromNet("http://c.3g.163.com/recommend/getSubDocPic?from=yuedu&size=20&passport=&devId=Ylm2OJYEEt2B4wmQGhKdww%3D%3D&lat=LfX0R2vEJtJK9FYmxWfY0A%3D%3D&lon=g8qKREPDlis6HwgTiQsMow%3D%3D&version=5.4.9&net=wifi" +
                                    "&ts=1456904563&sign=93Je4yRKwCeL22cYotD%2BGxprYxjW6VdOPuWuwVcOpt948ErR02zJ6%2FKXOnxX046I&encryption=1&canal=360_news",
                            ReadBean.class, new NetInterface<ReadBean>() {
                                @Override
                                public void getSucceed(ReadBean readBean) {
                                    adapter.addData(readBean);
                                    data.addAll(readBean.getRecommended());
                                    listView.setSelection(itemId - 1);
                                }

                                @Override
                                public void getFailes(String s) {

                                }

                                @Override
                                public void getSucceedForList(ArrayList data) {

                                }
                            });
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
    public void getSucceed(ReadBean readBean) {
        data = (ArrayList<ReadBean.recommendedEntity>) readBean.getRecommended();
        adapter = new ReadAdapter(readBean, context);
        listView.setAdapter(adapter);
    }

    @Override
    public void getFailes(String s) {

    }

    @Override
    public void getSucceedForList(ArrayList<ReadBean> data) {

    }

    @Override
    public void onRefresh() {
        mHandler.sendEmptyMessageDelayed(0x101, 2000);
        initData();
    }
}
