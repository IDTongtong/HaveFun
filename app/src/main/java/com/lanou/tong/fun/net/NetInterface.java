package com.lanou.tong.fun.net;

import java.util.ArrayList;

/**
 * Created by zt on 16/3/5.
 */
public interface NetInterface<T> {
    void getSucceed(T t);
    void getFailes(String s);
    void getSucceedForList(ArrayList<T> data);
}
