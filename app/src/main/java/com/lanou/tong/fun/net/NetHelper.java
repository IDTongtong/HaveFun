package com.lanou.tong.fun.net;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by zt on 16/3/5.
 */
public class NetHelper<T> {
    private RequestQueue requestQueue;    // 请求队列
    private T t;

    public NetHelper() {
        super();
        // 请求队列初始化
        SingleQueue singleQueue = SingleQueue.getSingleQueue();
        requestQueue = singleQueue.getRequestQueue();
    }

    public <T> void getInfo(String url, final NetInterface<T> netInterface,
                            final Class<T> cls) {
        String urlId = url;
        getDataFromNet(urlId, netInterface, cls);
    }

    private <T> void getDataFromNet(String urlId, final NetInterface<T> netInterface, final Class<T> cls) {
        JsonObjectRequest request = new JsonObjectRequest(urlId, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Gson gson = new Gson();
                T t = gson.fromJson(response.toString(), cls);
                netInterface.getSucceed(t);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                netInterface.getFailes("请求失败");
            }
        });
        requestQueue.add(request);
    }

    public <T> void getInfo(String head, final String id, String leg, String bottom, final Class<T> cls, final NetInterface listener) {
        String url = head + id + leg + bottom;
        final ArrayList<T> list = new ArrayList<>();
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray array = response.getJSONArray(id);
                            for (int i = 0; i < array.length(); i++) {
                                T t = new Gson().fromJson(array.getJSONObject(i).toString(), cls);
                                list.add(t);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        listener.getSucceedForList(list);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueue.add(jsonObjectRequest);
    }

    public <T> void getNewsFromNet(String url, final Class<T> clazz, final NetInterface listener) {
        StringRequest jsonObjectRequest = new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                T t = new Gson().fromJson(response.toString(), clazz);
                listener.getSucceed(t);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                listener.getFailes("加载失败");
            }
        });
        requestQueue.add(jsonObjectRequest);
    }

    public void getSingleContent(String url, final NetInterface listener) {
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                listener.getSucceed(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueue.add(jsonObjectRequest);
    }

    public void getData(String head, final String tid, String mid, String bottom, final Class<T> cls, final NetInterface listener) {
        String url = head + tid + mid + bottom;

        if (!tid.equals("")) {
            final ArrayList<T> list = new ArrayList<>();
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(url, null, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {

                    try {
                        JSONArray array = response.getJSONArray(tid);
                        for (int i = 0; i < array.length(); i++) {
                            t = new Gson().fromJson(array.getJSONObject(i).toString(), cls);
                            list.add(t);
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    listener.getSucceedForList(list);
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    listener.getFailes("网络不好,请稍等");
                }
            });
            requestQueue.add(jsonObjectRequest);
        } else getNewsFromNet(url, cls, listener);
    }

    public void getSingleData(String url, String midUrl, String lastUrl, NetInterface netListener) {
        String Url = url + midUrl + lastUrl;
        getSingleContent(Url, netListener);
    }

}
