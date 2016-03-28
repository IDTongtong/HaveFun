package com.lanou.tong.fun.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.lanou.tong.fun.fragment.NewsReplaceFragment;

import java.util.ArrayList;

/**
 * Created by zt on 16/3/5.
 */
public class NewsAdapter extends FragmentPagerAdapter {
    private ArrayList<Fragment> fragments;   // 定义fragment的集合
    private Context context;
    private ArrayList<String> titles;        // 定义标签的集合
    private ArrayList<String> tid;

    public NewsAdapter(FragmentManager fm, ArrayList<String> tid, ArrayList<Fragment> fragments,
                       Context context, ArrayList<String> titles) {
        super(fm);
        this.titles = titles;
        this.fragments = fragments;
        this.context = context;
        this.tid = tid;
    }

    // 获取某位置的fragment
    @Override
    public Fragment getItem(int position) {
        NewsReplaceFragment fragment = (NewsReplaceFragment) fragments.get(position);
        fragment.setTid(tid.get(position));
        return fragment;
    }

    // 获取fragment的集合的大小
    @Override
    public int getCount() {
        return fragments.size() - 20;
    }

    // 获取具体位置的fragment的标题
    @Override
    public CharSequence getPageTitle(int position) {
        return titles.get(position);
    }
}
