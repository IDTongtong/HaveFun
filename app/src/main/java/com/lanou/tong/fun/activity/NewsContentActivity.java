package com.lanou.tong.fun.activity;

import android.content.Intent;
import android.net.Uri;
import android.text.Html;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.drawee.view.SimpleDraweeView;
import com.google.gson.Gson;
import com.lanou.tong.fun.R;
import com.lanou.tong.fun.base.BaseActivity;
import com.lanou.tong.fun.bean.NewsContentBean;
import com.lanou.tong.fun.greendao.Collection;
import com.lanou.tong.fun.greendao.CollectionDao;
import com.lanou.tong.fun.greendao.DaoSingleton;
import com.lanou.tong.fun.greendao.History;
import com.lanou.tong.fun.greendao.HistoryDao;
import com.lanou.tong.fun.net.NetHelper;
import com.lanou.tong.fun.net.NetInterface;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.onekeyshare.OnekeyShare;

/**
 * Created by zt on 16/3/15.
 */
public class NewsContentActivity extends BaseActivity implements NetInterface<JSONObject>, View.OnClickListener {
    private String docId, picUrl;
    private TextView title, time, body;
    private SimpleDraweeView simpleDraweeView;
    private NewsContentBean bean;
    private ImageView returnIv, shareIv, collectionIv;
    private DaoSingleton daoSingleton;
    private CollectionDao collectionDao;
    private Collection collection;


    @Override
    protected int setContent() {
        return R.layout.activity_news_content;
    }

    @Override
    protected void initData() {
        Intent intent = getIntent();
        docId = intent.getStringExtra("docid");
        picUrl = intent.getStringExtra("picUrl");
        simpleDraweeView.setImageURI(Uri.parse(picUrl));
        String url = "http://c.m.163.com/nc/article/" + docId + "/full.html";
        NetHelper helper = new NetHelper();
        helper.getSingleContent(url, this);

    }

    @Override
    protected void initView() {
        title = (TextView) findViewById(R.id.news_content_title);
        time = (TextView) findViewById(R.id.news_content_time);
        body = (TextView) findViewById(R.id.news_content_body);
        simpleDraweeView = (SimpleDraweeView) findViewById(R.id.news_content_drawee);
        returnIv = (ImageView) findViewById(R.id.news_content_return);
        returnIv.setOnClickListener(this);
        shareIv = (ImageView) findViewById(R.id.news_content_share);
        shareIv.setOnClickListener(this);
        collectionIv = (ImageView) findViewById(R.id.news_content_collection);
        collectionIv.setOnClickListener(this);
    }


    // Html解析
    @Override
    public void getSucceed(JSONObject jsonObject) {
        JSONObject object = jsonObject;
        try {
            JSONObject data = object.getJSONObject(docId);
            Gson gson = new Gson();
            bean = gson.fromJson(data.toString(), NewsContentBean.class);
            String content = data.getString("body");
            body.setText("  " + Html.fromHtml(content));
            title.setText(Html.fromHtml(data.getString("title")));
            time.setText(Html.fromHtml(data.getString("ptime")));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        if (isCollection()) {
            collectionIv.setSelected(true);
        } else {
            collectionIv.setSelected(false);
        }
    }

    @Override
    public void getFailes(String s) {

    }

    @Override
    public void getSucceedForList(ArrayList<JSONObject> data) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.news_content_return:
                finish();
                break;
            case R.id.news_content_share:
                ShareSDK.initSDK(this);
                OnekeyShare onekeyShare = new OnekeyShare();
                onekeyShare.disableSSOWhenAuthorize();
                onekeyShare.setTitle(getString(R.string.app_name));
                onekeyShare.setTitleUrl("http://3g.163.com/ntes/special/0034073A/wechat_article.html?docid=" + docId);
                onekeyShare.setText(bean.getTitle());
                onekeyShare.setUrl("http://3g.163.com/ntes/special/0034073A/wechat_article.html?docid=" + docId);
                onekeyShare.setComment(bean.getDigest());
                onekeyShare.setSite(getString(R.string.app_name));
                onekeyShare.setSiteUrl("http://3g.163.com/ntes/special/0034073A/wechat_article.html?docid=" + docId);
                onekeyShare.show(this);
                break;
            case R.id.news_content_collection:
                if (!collectionIv.isSelected()) {
                    collectionDao.insert(collection);
                    collectionIv.setSelected(true);
                    Toast.makeText(this, "收藏成功", Toast.LENGTH_SHORT).show();
                } else {
                    collectionDao.deleteByKey(collectionDao.getKey(collection));
                    collectionIv.setSelected(false);
                    Toast.makeText(this, "取消收藏", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    // 判断是否已经收藏
    public boolean isCollection() {
        daoSingleton = DaoSingleton.getInstance();
        collectionDao = daoSingleton.getCollectionDao();
        collection = new Collection();
        collection.setContent(bean.getDigest());
        collection.setTitle(bean.getTitle());
        collection.setDocId(bean.getDocid());
        try {
            collection.setSrc(bean.getImg().get(0).getSrc());
        } catch (Exception e) {

        }

        List<Collection> queryList = collectionDao
                .queryBuilder()
                .where(CollectionDao.Properties.Title.eq(collection.getTitle()))
                .list();
        if (queryList.size() == 0) {
            return false;
        } else {
            return true;
        }
    }

    HistoryDao historyDao = DaoSingleton.getInstance().getHistoryDao();
    History history = new History();

    // 界面关闭时, 添加到阅读历史
    @Override
    protected void onDestroy() {
        super.onDestroy();
        try {
            if (bean.getTitle() != null) {
                history.setTitle(bean.getTitle());
                history.setTime(bean.getPtime());
                history.setDocId(bean.getDocid());
                history.setPicUrl(bean.getImg().get(0).getSrc());
            }
        } catch (Exception e) {

        }
        historyDao.insert(history);
    }
}
