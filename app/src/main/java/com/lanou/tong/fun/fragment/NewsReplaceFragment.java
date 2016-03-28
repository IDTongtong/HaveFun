package com.lanou.tong.fun.fragment;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.jorge.circlelibrary.ImageCycleView;
import com.lanou.tong.fun.R;
import com.lanou.tong.fun.adapter.NewsReplaceAdapter;
import com.lanou.tong.fun.base.BaseApplication;
import com.lanou.tong.fun.bean.NewsBean;
import com.lanou.tong.fun.net.ImageLoaderHelper;
import com.lanou.tong.fun.net.NetHelper;
import com.lanou.tong.fun.net.NetInterface;
import com.lanou.tong.fun.tool.MyListView;

import java.util.ArrayList;

/**
 * Created by zt on 16/3/5.
 */
public class NewsReplaceFragment extends Fragment implements NetInterface<NewsBean>, SwipeRefreshLayout.OnRefreshListener {
    private ImageCycleView imageCycleView;
    private Context context;
    private String tid;
    private MyListView listView;
    private static final int REFRESH_COMPLETE = 0x100;
    private SwipeRefreshLayout swipeRefreshLayout;
    private NewsReplaceAdapter adapter;
    private ArrayList<NewsBean> data;
    private boolean isBottom = false;
    private int itemId = 0;

    private Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case REFRESH_COMPLETE:
                    adapter.notifyDataSetChanged();
                    swipeRefreshLayout.setRefreshing(false);
                    break;
            }
        }
    };

    public void setTid(String tid) {
        this.tid = tid;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.news_replace, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        listView = (MyListView) getView().findViewById(R.id.news_replace_mylist);

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getContent();
        setHeadView();
        setFooterView();
        setRefreshComplete();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }

    private void getContent() {
        NetHelper helper = new NetHelper();
        helper.getInfo("http://c.m.163.com/nc/article/list/", tid, "/0", "-20.html", NewsBean.class, this);
    }


    private void setHeadView() {
        context = BaseApplication.getContext();
        View view = LayoutInflater.from(getContext()).inflate(R.layout.news_cycle, null);
        imageCycleView = (ImageCycleView) view.findViewById(R.id.news_replace_imagecycleview);
        listView.addHeaderView(view);
    }

    public void setRefreshComplete() {
        swipeRefreshLayout = (SwipeRefreshLayout) getView().findViewById(R.id.news_replace_swipe);
        swipeRefreshLayout.setOnRefreshListener(this);
//        swipeRefreshLayout.setColorScheme(android.R.color.holo_red_light, android.R.color.holo_orange_light,
//                android.R.color.holo_green_light, android.R.color.holo_blue_bright);
    }

    public void setFooterView() {
        context = BaseApplication.getContext();
        View v = LayoutInflater.from(getContext()).inflate(R.layout.news_footview, null);
        listView.addFooterView(v);
    }

    public static int getScreenHeight(Context context) {
        if (null == context) {
            return 0;
        }
        DisplayMetrics dm = new DisplayMetrics();
        dm = context.getApplicationContext().getResources().getDisplayMetrics();
        return dm.heightPixels;
    }

    private void initCarsueView(ArrayList<String> imageDescList, ArrayList<String> urlList) {
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                getScreenHeight(BaseApplication.getContext()) * 3 / 10);
        ImageCycleView.ImageCycleViewListener imageCycleViewListener = new ImageCycleView.ImageCycleViewListener() {
            @Override
            public void displayImage(String imageURL, ImageView imageView) {
                ImageLoaderHelper.getInstance().loadImage(imageURL, imageView);
            }

            @Override
            public void onImageClick(int position, View imageView) {

            }
        };
        imageCycleView.setImageResources(imageDescList, urlList, imageCycleViewListener);
        imageCycleView.startImageCycle();
    }

    @Override
    public void getSucceed(NewsBean newsBean) {

    }

    @Override
    public void getFailes(String s) {

    }

    @Override
    public void getSucceedForList(ArrayList<NewsBean> data) {
        this.data = data;
        ArrayList<String> imageDescList = new ArrayList<>();
        ArrayList<String> urlList = new ArrayList<>();
        try {
            if (data.get(0).getAds() != null) {
                for (int i = 0; i < data.get(0).getAds().size(); i++) {
                    urlList.add(data.get(0).getAds().get(i).getImgsrc());
                    imageDescList.add(data.get(0).getAds().get(i).getTitle());
                }
            }
        } catch (Exception e) {

        }
//       else {
//            urlList.add(data.get(0).getImgsrc());
//            imageDescList.add(data.get(0).getTitle());
//        }
        initCarsueView(imageDescList, urlList);

        adapter = new NewsReplaceAdapter(data, context);
        listView.setAdapter(adapter);
       // setPullUp();
    }

//    private void setPullUp() {
//        listView.setOnScrollChangeListener(new View.OnScrollChangeListener() {
//            @Override
//            public void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
//                if (listView.getLastVisiblePosition() == data.size() - 2 && isBottom) {
//                    NetHelper netHelper = new NetHelper();
//                    itemId += 20;
//                    netHelper.getInfo("http://c.m.163.com/nc/article/list/", tid, "/" + itemId, "-20.html", NewsBean.class, new NetInterface<NewsBean>() {
//                        @Override
//                        public void getSucceed(NewsBean newsBean) {
//
//                        }
//
//                        @Override
//                        public void getFailes(String s) {
//
//                        }
//
//                        @Override
//                        public void getSucceedForList(ArrayList<NewsBean> data) {
//                            adapter.addData(data);
//                        }
//                    });
//                    isBottom = false;
//                } else if (listView.getLastVisiblePosition() != data.size() - 1) {
//                    isBottom = true;
//                }
//            }
//        });
//    }

    @Override
    public void onRefresh() {
        handler.sendEmptyMessageDelayed(REFRESH_COMPLETE, 2000);
        getContent();
    }

}

