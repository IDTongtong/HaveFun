package com.lanou.tong.fun.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.lanou.tong.fun.R;
import com.lanou.tong.fun.activity.MainActivity;
import com.lanou.tong.fun.bean.UserBean;

import java.util.HashMap;

import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.sina.weibo.SinaWeibo;
import cn.sharesdk.tencent.qzone.QZone;
import de.greenrobot.event.EventBus;

//用户
public class ProfileFragment extends Fragment implements View.OnClickListener {
    private LinearLayout qqLogin, sinaLogin;
    private Platform qqPlatform, sinaPlatform;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        qqLogin = (LinearLayout) getView().findViewById(R.id.login_qq);
        sinaLogin = (LinearLayout) getView().findViewById(R.id.login_sina);

        qqLogin.setOnClickListener(this);
        sinaLogin.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.login_qq:
                ShareSDK.initSDK(getContext());
                qqPlatform = ShareSDK.getPlatform(QZone.NAME);
                if (qqPlatform.isAuthValid()) {
                    qqPlatform.removeAccount();
                }
                qqPlatform.setPlatformActionListener(new PlatformActionListener() {
                    @Override
                    public void onComplete(Platform platform, int i, HashMap<String, Object> hashMap) {
                        new QQThread().start();
                        Toast.makeText(getActivity(), "登陆成功", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onError(Platform platform, int i, Throwable throwable) {
                        Toast.makeText(getActivity(), "登录失败", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onCancel(Platform platform, int i) {
                        Toast.makeText(getActivity(), "取消登陆", Toast.LENGTH_SHORT).show();
                    }
                });
                qqPlatform.SSOSetting(false);
                qqPlatform.showUser(null);
                break;
            case R.id.login_sina:
                ShareSDK.initSDK(getContext());
                sinaPlatform = ShareSDK.getPlatform(SinaWeibo.NAME);
                if (sinaPlatform.isAuthValid()) {
                    sinaPlatform.removeAccount();
                }
                sinaPlatform.setPlatformActionListener(new PlatformActionListener() {
                    @Override
                    public void onComplete(Platform platform, int i, HashMap<String, Object> hashMap) {
                        new SinaThread().start();
                        Toast.makeText(getActivity(), "登陆成功", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onError(Platform platform, int i, Throwable throwable) {
                        Toast.makeText(getActivity(), "登录失败", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onCancel(Platform platform, int i) {
                        Toast.makeText(getActivity(), "取消登陆", Toast.LENGTH_SHORT).show();
                    }
                });
                sinaPlatform.SSOSetting(false);
                sinaPlatform.showUser(null);
                break;
        }
    }

    public class QQThread extends Thread {
        @Override
        public void run() {
            super.run();
            UserBean userBean = new UserBean();

            userBean.setUserName(qqPlatform.getDb().getUserName());
            userBean.setUserIcon(qqPlatform.getDb().getUserIcon());
//            if (qqPlatform.getDb().getUserGender().equals("f")) {
//                userBean.setUserSex("女");
//            } else if (qqPlatform.getDb().getUserGender().equals("m")) {
//                userBean.setUserSex("男");
//            } else {
//                userBean.setUserSex(" ");
//            }
            // 发送Event的对象
            EventBus.getDefault().post(userBean);
//            Intent intent = new Intent(getActivity(), MainActivity.class);
//            startActivity(intent);
        }
    }

    public class SinaThread extends Thread {
        @Override
        public void run() {
            super.run();
            UserBean userBean = new UserBean();

            userBean.setUserName(sinaPlatform.getDb().getUserName());
            userBean.setUserIcon(sinaPlatform.getDb().getUserIcon());
            userBean.setUserSex(sinaPlatform.getDb().getUserGender());
            EventBus.getDefault().post(userBean);
        }
    }
}
