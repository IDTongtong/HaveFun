package com.lanou.tong.fun.tool;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ListView;

import com.lanou.tong.fun.R;


/**
 * Created by nan on 16/3/8.
 */
public class MyListView extends ListView implements AbsListView.OnScrollListener {
    private float xDistance, yDistance, xLast, yLast;

    /**
     * 底部显示正在加载的页面
     */
    private View footerView = null;
    /**
     * 存储上下文
     */
    private Context context;
    /**
     * 上拉刷新的ListView的回调监听
     */
    private MyPullUpListViewCallBack myPullUpListViewCallBack;
    /**
     * 记录第一行Item的数值
     */
    private int firstVisibleItem;

    public MyListView(Context context) {
        super(context);
        this.context = context;
    }

    public MyListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
    }

    public MyListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public MyListView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    // 初始化ListView
    private void initListView() {
        // 为ListView设置滑动监听
        setOnScrollListener(this);
        // 去掉底部分割线
        setFooterDividersEnabled(false);
    }

    // 触摸事件的监听，只有上下滑动才会有效果
    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:                // 按下
                xDistance = yDistance = 0;
                xLast = ev.getX();
                yLast = ev.getY();
                break;
            case MotionEvent.ACTION_MOVE:                // 松开
                final float moveX = ev.getX();
                final float moveY = ev.getY();

                xDistance = Math.abs(moveX - xLast);
                yDistance = Math.abs(moveY - yLast);
                if (xDistance > yDistance) {
                    return false;
                }

        }
        return super.onInterceptTouchEvent(ev);
    }

    /**
     * 初始化话底部页面
     */
    public void initBottomView() {

        if (footerView == null) {
            footerView = LayoutInflater.from(this.context).inflate(R.layout.news_footview, null);
        }
        addFooterView(footerView);
    }


    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
        //当滑动到底部时
        if (scrollState == OnScrollListener.SCROLL_STATE_IDLE && firstVisibleItem != 0) {
            myPullUpListViewCallBack.scrollBottomState();
        }
    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        this.firstVisibleItem = firstVisibleItem;

        if (footerView != null) {
            //判断可视Item是否能在当前页面完全显示
            if (visibleItemCount == totalItemCount) {
                // removeFooterView(footerView);
                footerView.setVisibility(View.GONE);//隐藏底部布局
            } else {
                // addFooterView(footerView);
                footerView.setVisibility(View.VISIBLE);//显示底部布局
            }
        }

    }

    public void setMyPullUpListViewCallBack(MyPullUpListViewCallBack myPullUpListViewCallBack) {
        this.myPullUpListViewCallBack = myPullUpListViewCallBack;
    }

    // 上拉加载的ListView的回调监听
    public interface MyPullUpListViewCallBack {
        void scrollBottomState();
    }
}
