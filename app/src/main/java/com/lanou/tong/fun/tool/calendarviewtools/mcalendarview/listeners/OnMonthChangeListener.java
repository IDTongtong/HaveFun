package com.lanou.tong.fun.tool.calendarviewtools.mcalendarview.listeners;

import android.support.v4.view.ViewPager;

import com.lanou.tong.fun.tool.calendarviewtools.mcalendarview.utils.CalendarUtil;


/**
 * Created by bob.sun on 15/8/28.
 */
public abstract class OnMonthChangeListener implements ViewPager.OnPageChangeListener {
    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
//        Log.wtf("OnPageScrolled:", ""+position);
    }

    @Override
    public void onPageSelected(int position) {
//        Log.wtf("OnPageSelected:", ""+position);
        onMonthChange(CalendarUtil.position2Year(position), CalendarUtil.position2Month(position));
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    public abstract void onMonthChange(int year, int month);
}
