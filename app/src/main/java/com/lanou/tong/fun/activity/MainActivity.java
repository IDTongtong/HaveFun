package com.lanou.tong.fun.activity;

import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.drawee.generic.GenericDraweeHierarchy;
import com.facebook.drawee.generic.GenericDraweeHierarchyBuilder;
import com.facebook.drawee.generic.RoundingParams;
import com.facebook.drawee.view.SimpleDraweeView;
import com.lanou.tong.fun.R;
import com.lanou.tong.fun.tool.ResideMenu.ResideMenu;
import com.lanou.tong.fun.tool.ResideMenu.ResideMenuItem;
import com.lanou.tong.fun.base.BaseApplication;
import com.lanou.tong.fun.bean.UserBean;
import com.lanou.tong.fun.fragment.CollectionFragment;
import com.lanou.tong.fun.fragment.HistoryFragment;
import com.lanou.tong.fun.fragment.MediaFragment;
import com.lanou.tong.fun.fragment.NewsFragment;
import com.lanou.tong.fun.fragment.ProfileFragment;
import com.lanou.tong.fun.fragment.ReadFragment;
import com.lanou.tong.fun.fragment.SettingsFragment;
import com.lanou.tong.fun.fragment.TopicFragment;

import cn.jpush.android.api.JPushInterface;
import de.greenrobot.event.EventBus;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private ResideMenu resideMenu;
    private MainActivity mContext;
    private ResideMenuItem itemNews;
    private ResideMenuItem itemProfile;
    private ResideMenuItem itemCalendar;
    private ResideMenuItem itemSettings;
    private ResideMenuItem itemRead;
    private ResideMenuItem itemMedia;
    private ResideMenuItem itemTopic;
    private ResideMenuItem itemHistory;
    private ResideMenuItem itemCollection;
    private SimpleDraweeView loginIcon;
    private TextView loginName;

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        Intent intent = new Intent(this,GuideActivity.class);
//        startActivity(intent);

        setContentView(R.layout.activity_main);
        mContext = this;
        setUpMenu();
        if (savedInstanceState == null)
            changeFragment(new NewsFragment());
    }

    private void setUpMenu() {

        // attach to current activity;
        resideMenu = new ResideMenu(this);
        resideMenu.setUse3D(true);
        resideMenu.setBackground(R.mipmap.menu_background);
        resideMenu.attachToActivity(this);
        resideMenu.setMenuListener(menuListener);
        //valid scale factor is between 0.0f and 1.0f. leftmenu'width is 150dip.
        resideMenu.setScaleValue(0.6f);

        // create menu items;
        itemNews = new ResideMenuItem(this, R.mipmap.ic_news, "新闻");
        itemProfile = new ResideMenuItem(this, R.mipmap.icon_profile, "个人中心");
        itemCalendar = new ResideMenuItem(this, R.mipmap.icon_calendar, "日历");
        itemSettings = new ResideMenuItem(this, R.mipmap.icon_settings, "设置");
        itemRead = new ResideMenuItem(this, R.mipmap.icon_read, "阅读");
        itemMedia = new ResideMenuItem(this, R.mipmap.ic_media, "视听");
        itemTopic = new ResideMenuItem(this, R.mipmap.ic_topic, "话题");
        itemCollection = new ResideMenuItem(this, R.mipmap.collection, "收藏");
        itemHistory = new ResideMenuItem(this, R.mipmap.history, "历史");

        itemNews.setOnClickListener(this);
        itemProfile.setOnClickListener(this);
        itemCalendar.setOnClickListener(this);
        itemSettings.setOnClickListener(this);
        itemRead.setOnClickListener(this);
        itemMedia.setOnClickListener(this);
        itemTopic.setOnClickListener(this);
        itemHistory.setOnClickListener(this);
        itemCollection.setOnClickListener(this);

        resideMenu.addMenuItem(itemProfile, ResideMenu.DIRECTION_LEFT);
        resideMenu.addMenuItem(itemCollection, ResideMenu.DIRECTION_LEFT);
        resideMenu.addMenuItem(itemHistory, ResideMenu.DIRECTION_LEFT);
        resideMenu.addMenuItem(itemRead, ResideMenu.DIRECTION_RIGHT);
        resideMenu.addMenuItem(itemMedia, ResideMenu.DIRECTION_RIGHT);
        resideMenu.addMenuItem(itemTopic, ResideMenu.DIRECTION_RIGHT);
        resideMenu.addMenuItem(itemNews, ResideMenu.DIRECTION_RIGHT);
        resideMenu.addMenuItem(itemCalendar, ResideMenu.DIRECTION_LEFT);
        resideMenu.addMenuItem(itemSettings, ResideMenu.DIRECTION_LEFT);


        FrameLayout ignored_view = (FrameLayout) findViewById(R.id.news_replace_imagecycleview);
        resideMenu.addIgnoredView(ignored_view);
        // You can disable a direction by setting ->
        // resideMenu.setSwipeDirectionDisable(ResideMenu.DIRECTION_RIGHT);

        findViewById(R.id.title_bar_left_menu).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resideMenu.openMenu(ResideMenu.DIRECTION_LEFT);
            }
        });
        findViewById(R.id.title_bar_right_menu).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resideMenu.openMenu(ResideMenu.DIRECTION_RIGHT);
            }
        });


        loginIcon = (SimpleDraweeView) getResideMenu().getLeftMenuView().findViewById(R.id.view_avatar_iv);
        loginName = (TextView) getResideMenu().getLeftMenuView().findViewById(R.id.view_name_tv);

        EventBus.getDefault().register(this);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        return resideMenu.dispatchTouchEvent(ev);
    }

    @Override
    public void onClick(View view) {

        if (view == itemNews) {
            changeFragment(new NewsFragment());
        } else

        if (view == itemProfile) {
            changeFragment(new ProfileFragment());
        } else if (view == itemCalendar) {
            //  changeFragment(new CalendarFragment());
            Intent intent = new Intent(MainActivity.this, CalendarActivity.class);
            startActivity(intent);
        } else if (view == itemSettings) {
            changeFragment(new SettingsFragment());
        } else if (view == itemRead) {
            changeFragment(new ReadFragment());
        } else if (view == itemMedia) {
            changeFragment(new MediaFragment());
        } else if (view == itemTopic) {
            changeFragment(new TopicFragment());
        } else if (view == itemCollection) {
            changeFragment(new CollectionFragment());
        } else if (view == itemHistory) {
            changeFragment(new HistoryFragment());
        }
        resideMenu.closeMenu();
    }

    private ResideMenu.OnMenuListener menuListener = new ResideMenu.OnMenuListener() {
        @Override
        public void openMenu() {
            Toast.makeText(mContext, "Menu is opened!", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void closeMenu() {
            Toast.makeText(mContext, "Menu is closed!", Toast.LENGTH_SHORT).show();
        }
    };

    private void changeFragment(Fragment targetFragment) {
        resideMenu.clearIgnoredViewList();
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.main_fragment, targetFragment, "fragment")
                .setTransitionStyle(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                .commit();
    }

    @Override
    protected void onResume() {
        super.onResume();
        JPushInterface.onResume(BaseApplication.getContext());
    }

    @Override
    protected void onPause() {
        super.onPause();
        JPushInterface.onPause(BaseApplication.getContext());
    }

    // What good method is to access resideMenu？
    public ResideMenu getResideMenu() {
        return resideMenu;
    }

    // 接收post的类
    // 事件在主线程-UI线程执行
    public void onEventMainThread(UserBean bean) {
        GenericDraweeHierarchy hierarchy = GenericDraweeHierarchyBuilder
                .newInstance(Resources.getSystem())
                .setRoundingParams(RoundingParams.asCircle())
                .build();
        loginIcon.setHierarchy(hierarchy);
        loginIcon.setImageURI(Uri.parse(bean.getUserIcon()));
        loginName.setText(bean.getUserName());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
