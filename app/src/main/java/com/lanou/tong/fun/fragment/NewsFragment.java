package com.lanou.tong.fun.fragment;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.facebook.drawee.generic.GenericDraweeHierarchy;
import com.facebook.drawee.generic.GenericDraweeHierarchyBuilder;
import com.facebook.drawee.generic.RoundingParams;
import com.facebook.drawee.view.SimpleDraweeView;
import com.lanou.tong.fun.R;
import com.lanou.tong.fun.adapter.NewsAdapter;
import com.lanou.tong.fun.bean.NewsTitleBean;
import com.lanou.tong.fun.bean.UserBean;
import com.lanou.tong.fun.net.NetHelper;
import com.lanou.tong.fun.net.NetInterface;
import com.lanou.tong.fun.tool.CircleImageDrawable;
import com.nineoldandroids.view.ViewHelper;

import java.util.ArrayList;

import de.greenrobot.event.EventBus;

/**
 * Created by zt on 16/3/3.
 */
public class NewsFragment extends Fragment implements NetInterface<NewsTitleBean> {
    private ViewPager viewPager;
    private TabLayout tabLayout;
    private NewsAdapter adapter;
    private ArrayList<Fragment> fragments;
    private ArrayList<String> titles;
    private ArrayList<String> tid;
    private ArrayList<NewsTitleBean.TListEntity> newsData;
    private SimpleDraweeView loginImg;

    // 创建视图
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_news, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewPager = (ViewPager) view.findViewById(R.id.news_vp);
        tabLayout = (TabLayout) view.findViewById(R.id.news_tablayout);


    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        titles = new ArrayList<>();
        fragments = new ArrayList<>();
        tid = new ArrayList<>();
        adapter = new NewsAdapter(getActivity().getSupportFragmentManager(), tid,
                fragments, getActivity(), titles);
        NetHelper netHelper = new NetHelper();
        netHelper.getInfo("http://c.3g.163.com/nc/topicset/android/subscribe/manage/listspecial.html",
                this, NewsTitleBean.class);
    }

    @Override
    public void getSucceed(NewsTitleBean newsTitleBean) {
        newsData = (ArrayList<NewsTitleBean.TListEntity>) newsTitleBean.getTList();
        for (int i = 0; i < newsData.size(); i++) {
            fragments.add(new NewsReplaceFragment());
            titles.add(newsData.get(i).getTname());
            tid.add(newsData.get(i).getTid());
            viewPager.setAdapter(adapter);
            tabLayout.setupWithViewPager(viewPager);
            tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        }
    }

    @Override
    public void getFailes(String s) {

    }

    @Override
    public void getSucceedForList(ArrayList<NewsTitleBean> data) {

    }



    @Override
    public void onDestroy() {
        super.onDestroy();

    }
}
