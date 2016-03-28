package com.lanou.tong.fun.activity;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.google.gson.Gson;
import com.lanou.tong.fun.R;
import com.lanou.tong.fun.adapter.ReadContentAdapter;
import com.lanou.tong.fun.base.BaseActivity;
import com.lanou.tong.fun.bean.ReadContentBean;
import com.lanou.tong.fun.bean.ReadContentCustomBean;
import com.lanou.tong.fun.net.NetHelper;
import com.lanou.tong.fun.net.NetInterface;


import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by zt on 16/3/18.
 */
public class ReadContentActivity extends BaseActivity implements NetInterface<JSONObject>, View.OnClickListener {
    private ReadContentBean bean;
    private String id;
    private ImageView returnIv;
    private RequestQueue requestQueue;
    private ArrayList<ReadContentCustomBean> datas;
    private RecyclerView recyclerView;
    private ReadContentAdapter adapter;

    @Override
    protected int setContent() {
        return R.layout.activity_read_content;
    }

    @Override
    protected void initData() {
        Intent intent = getIntent();
        id = intent.getStringExtra("id");
        Log.d("ReadContentActivity", id+"---------------------");
        NetHelper helper = new NetHelper();
        helper.getSingleData("http://c.m.163.com/nc/article/", id, "/full.html", this);
        datas = new ArrayList<>();
    }

    @Override
    protected void initView() {
        returnIv = (ImageView) findViewById(R.id.read_content_return);
        recyclerView = (RecyclerView) findViewById(R.id.read_content_recycler);
        returnIv = (ImageView) findViewById(R.id.read_content_return);
        returnIv.setOnClickListener(this);
    }

    @Override
    public void getSucceed(JSONObject jsonObject) {
        try {
            JSONObject object = jsonObject.getJSONObject(id);
            Gson gson = new Gson();
            bean = gson.fromJson(object.toString(), ReadContentBean.class);
            String body = bean.getBody();
            if (body == null) {
                Toast.makeText(ReadContentActivity.this, "正在维护，加载失败！！", Toast.LENGTH_SHORT).show();
                ReadContentActivity.this.finish();
            }

            String[] content = body.split("<\\/p>");
            int j = 0;
            for (int i = 0; i < content.length; i++) {
                ReadContentCustomBean myData = new ReadContentCustomBean();
                if (content[i].contains("IMG#")) {
                    myData.setImgUrl(bean.getImg().get(j).getSrc());
                    j++;
                }
                myData.setContent(content[i]);
                datas.add(myData);
                adapter = new ReadContentAdapter(datas);
                adapter.setTime(bean.getPtime());
                adapter.setTitle(bean.getTitle());
                recyclerView.setLayoutManager(new LinearLayoutManager(ReadContentActivity.this));
                recyclerView.setAdapter(adapter);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void getFailes(String s) {

    }

    @Override
    public void getSucceedForList(ArrayList data) {

    }

    @Override
    public void onClick(View v) {
        finish();
    }
}
