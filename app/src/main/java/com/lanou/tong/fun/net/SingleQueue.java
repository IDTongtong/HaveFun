package com.lanou.tong.fun.net;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.lanou.tong.fun.base.BaseApplication;

/**
 * Created by zt on 16/3/5.
 */
public class SingleQueue {
    private static SingleQueue singleQueue;
    private RequestQueue requestQueue;

    private SingleQueue() {
        super();
        requestQueue = Volley.newRequestQueue(BaseApplication.getContext());
    }

    public RequestQueue getRequestQueue() {
        return requestQueue;
    }

    public static SingleQueue getSingleQueue() {
        if (singleQueue == null) {
            synchronized (SingleQueue.class) {
                if (singleQueue == null) {
                    singleQueue = new SingleQueue();
                }
            }
        }
        return singleQueue;
    }

}
